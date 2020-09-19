package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ItemPanel extends JPanel implements ActionListener {
	final int itemCount = 4;
	HeadBoard head_board = null;
	JLabel downPart;
	JLabel upPart;
	JLabel ctgryLabel;
	JButton[] ctgryBtn;
	JLabel[] itemArea;
	Item list[];
	JButton[] imageBtn;
	JButton[] nameBtn;
	JLabel[] priceLabel;
	JLabel emptyLabel;
	public static String pageName;
	String ctgryName;
	String categoryList[] = { "전체", "스킨케어", "메이크업", "바디케어", "헤어케어", "미용소품", "남성", "건강/위생용품", "건강식품", "일반식품",
			"반려동물", "잡화" };

	public ItemPanel(String name) {
		setLayout(null);
		ctgryName = name;
		list = new Item[itemCount];
		int check = -1;
		if (name.equals("전체")) {
			list[0] = ItemManager.instance.itemList.get("스킨케어").get(0);
			list[1] = ItemManager.instance.itemList.get("메이크업").get(2);
			list[2] = ItemManager.instance.itemList.get("바디케어").get(0);
			list[3] = ItemManager.instance.itemList.get("헤어케어").get(2);
			check = -2;

		} else {
			for (String key : ItemManager.instance.itemList.keySet()) {
				if (ctgryName.equals(key)) {
					for (int i = 0; i < itemCount; i++) {
						list[i] = ItemManager.instance.itemList.get(name).get(i);
					}
					check = 1;
				}
			}
		}

		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		upPart = new JLabel();
		upPart.setSize(1900, 290);
		upPart.setLocation(0, 210);
		upPart.setBackground(Color.WHITE);
		upPart.setOpaque(true);
		add(upPart);
		ctgryBtn = new JButton[12];
		setCategory();
		downPart = new JLabel();
		downPart.setSize(1900, 500);
		downPart.setLocation(0, 500);
		downPart.setBackground(Color.WHITE);
		downPart.setOpaque(true);
		add(downPart);
		if (check == -1) {
			emptyLabel = new JLabel("카테고리 내 상품없음");
			emptyLabel.setSize(400, 50);
			emptyLabel.setLocation(400, 10);
			emptyLabel.setOpaque(true);
			downPart.add(emptyLabel);
		} else {
			itemArea = new JLabel[itemCount];
			for (int i = 0; i < itemCount; i++) {
				itemArea[i] = new JLabel();
				itemArea[i].setSize(270, 450);
				itemArea[i].setLocation(400 + (i * 280), 10);
				itemArea[i].setBackground(Color.WHITE);
				itemArea[i].setOpaque(true);
				downPart.add(itemArea[i]);
			}
			imageBtn = new JButton[itemCount];
			nameBtn = new JButton[itemCount];
			priceLabel = new JLabel[itemCount];
			setItem();
		}
	}

	public void setItem() {
		for (int i = 0; i < itemCount; i++) {
			imageBtn[i] = new JButton();
			imageBtn[i].setBounds(10, 10, 250, 280);
			try {
				Image img = new ImageIcon(list[i].getImageName()).getImage().getScaledInstance(250, 280,
						Image.SCALE_SMOOTH);
				imageBtn[i].setIcon(new ImageIcon(img));

			} catch (Exception ex) {
				System.out.println(ex);
			}
			nameBtn[i] = new JButton(list[i].getItemName());
			nameBtn[i].setBounds(10, 290, 250, 50);
			priceLabel[i] = new JLabel(list[i].getPrice() + " 원", SwingConstants.CENTER);
			priceLabel[i].setBounds(10, 340, 250, 70);
			nameBtn[i].setBackground(Color.white);
			nameBtn[i].setBorder(null);
			nameBtn[i].setFont(new Font("", Font.PLAIN, 15));
			priceLabel[i].setFont(new Font("", Font.BOLD, 20));
			imageBtn[i].addActionListener(this);
			nameBtn[i].addActionListener(this);

			itemArea[i].add(imageBtn[i]);
			itemArea[i].add(nameBtn[i]);
			itemArea[i].add(priceLabel[i]);
		}
	}

	public void setCategory() {
		ctgryLabel = new JLabel(pageName);
		ctgryLabel.setFont(new Font("", Font.BOLD, 50));
		ctgryLabel.setBounds(360, 50, 600, 50);
		ctgryLabel.setBackground(Color.WHITE);
		ctgryLabel.setOpaque(true);
		upPart.add(ctgryLabel);
		LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 1, false);
		for (int i = 0; i < 6; i++) {
			ctgryBtn[i] = new JButton();
			ctgryBtn[i].setBounds(360 + (i * 200), 120, 200, 50);
			ctgryBtn[i].setBackground(Color.WHITE);
			ctgryBtn[i].setBorder(lineBorder);
			if (categoryList[i].equals(ctgryName)) {
				ctgryBtn[i].setBorder(new LineBorder(new Color(153, 255, 51), 1, false));
			}
			ctgryBtn[i].setText(categoryList[i]);
			ctgryBtn[i].addActionListener(this);
			ctgryBtn[i + 6] = new JButton();
			ctgryBtn[i + 6].setBounds(360 + (i * 200), 170, 200, 50);
			ctgryBtn[i + 6].setBackground(Color.WHITE);
			ctgryBtn[i + 6].setBorder(lineBorder);
			if (categoryList[i + 6].equals(ctgryName)) {
				ctgryBtn[i + 6].setBorder(new LineBorder(new Color(153, 255, 51), 1, false));
			}
			ctgryBtn[i + 6].setText(categoryList[i + 6]);
			ctgryBtn[i + 6].addActionListener(this);
			upPart.add(ctgryBtn[i]);
			upPart.add(ctgryBtn[i + 6]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 12; i++) {
			if (e.getSource() == ctgryBtn[i]) {
				System.out.println(ctgryBtn[i].getText());
				if (ctgryBtn[i].getText() != "") {
					Main.frame.setContentPane(new ItemPanel(ctgryBtn[i].getText()));
					Main.frame.revalidate();
				}
			}
		}
		if (imageBtn != null) {
			for (int i = 0; i < itemCount; i++) {
				if (e.getSource() == imageBtn[i] || e.getSource() == nameBtn[i]) {

					Main.frame.setContentPane(new ItemDetailedPanel(list[i]));
					Main.frame.revalidate();

				}
			}
		}
	}
}
