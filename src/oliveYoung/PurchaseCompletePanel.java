package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PurchaseCompletePanel extends JPanel {
	ArrayList<ItemInfo1> itemList;
	boolean today;
	JLabel purchaseComplete;
	HeadBoard head_board = null;

	PurchaseCompletePanel(ArrayList<ItemInfo1> itemList) {
		setLayout(null);
		setBackground(Color.white);
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		this.itemList = itemList;
		this.today = itemList.get(0).today;
		purchaseComplete = new JLabel();
		purchaseComplete.setFont(new Font("", Font.BOLD, 30));
		purchaseComplete.setBounds(600, 300, 600, 100);
		purchaseComplete.setHorizontalAlignment(SwingConstants.CENTER);
		purchaseComplete.setOpaque(true);
		add(purchaseComplete);
		if (today) {
			reduceFromStore();
			purchaseComplete.setText("오늘 드림 주문 완료");
		} else {
			reduceFromSystem();
			purchaseComplete.setText("올리브영 온라인 주문 완료");
		}
	}

	public void reduceFromStore() {
		ArrayList<ItemInfo1> tempList = UserManager.usermanager.userList.get(UserManager.logIdx).cart.cartList;
		Store temp = StoreManager.instance.findStore(UserManager.usermanager.getCity(),
				UserManager.usermanager.getStreet(), UserManager.usermanager.getCode());
		int index = StoreManager.instance.storeIndex(temp.getCity(), temp);
		for (int i = 0; i < itemList.size(); i++) {
			StoreManager.instance.storeList.get(temp.getCity()).get(index).reduceItem(itemList.get(i));
			for (int j = 0; j < tempList.size(); j++) {
				if (tempList.get(j).itemFullName.equals(itemList.get(i).itemFullName)) {
					tempList.remove(j);
				}
			}
		}
		UserManager.usermanager.saveUser();
	}

	public void reduceFromSystem() {
		ArrayList<ItemInfo1> tempList = UserManager.usermanager.userList.get(UserManager.logIdx).cart.cartList;
		for (int i = 0; i < itemList.size(); i++) {
			ItemManager.instance.reduceItem(itemList.get(i));
			for (int j = 0; j < tempList.size(); j++) {
				if (tempList.get(j).itemFullName.equals(itemList.get(i).itemFullName)) {
					tempList.remove(j);
				}
			}
		}
		UserManager.usermanager.saveUser();

	}
}
