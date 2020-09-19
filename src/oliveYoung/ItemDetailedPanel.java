package oliveYoung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ItemDetailedPanel extends JPanel implements ActionListener {

	HeadBoard head_board = null;
	JLabel mainPart;
	JLabel itemImage;
	JLabel itemName;
	JLabel itemInfo[][];
	JLabel itemCount;
	JButton itemPlus;
	JButton itemMinus;
	JLabel count;
	JButton cart;
	JButton purchase;
	Item item;
	JLabel deliveryToday;
	JCheckBox checkDeliveryToday;
	JButton deliveryOption;
	JButton checkAvailableStore;
	JLabel deliveryStore;
	static Popup p;//
	JPanel storeList;//
	Store availableStore;
	JFrame f = new JFrame("pop");
	String[][] labelList = { { "판매가", "" }, { "카드할인혜택", "The CJ카드 추가 10%" }, { "CJ ONE 포인트 예상적립", "2%적립" },
			{ "배송비", "2,500원(결제금액 20,000원 이상 무료)" }, { "배송방법", "올리브영 배송" }, { "배송기간", "평균 3일 이내 배송" },
			{ "상품 금액 합계", "" } };

	public ItemDetailedPanel(Item item) {
		setLayout(null);
		this.item = new Item(item.getCategory(), item.getItemTitle(), item.getItemName(), item.getImageName(),
				item.getPrice(), 1, item.isOnlyOnline());
		availableStore = null;
		storeList = new StoreListPanel(item);//
		storeList.setSize(1000, 900);

		storeList.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));

		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		mainPart = new JLabel();
		mainPart.setBounds(0, 210, 1900, 900);
		mainPart.setBackground(Color.WHITE);
		mainPart.setOpaque(true);
		add(mainPart);
		itemImage = new JLabel();
		itemName = new JLabel();
		itemInfo = new JLabel[7][2];
		itemCount = new JLabel();
		count = new JLabel();
		itemPlus = new JButton();
		itemMinus = new JButton();
		setDetailedInfo();
		setCount();
		cart = new JButton();
		purchase = new JButton();
		checkAvailableStore = new JButton();
		setButton();

		deliveryToday = new JLabel();
		if (item.isOnlyOnline()) {
			deliveryToday.setBounds(1000, 560, 600, 40);
			deliveryToday.setFont(new Font("", Font.BOLD, 20));
			deliveryToday.setText("오늘드림 불가 상품(온라인 판매)");
			deliveryToday.setHorizontalAlignment(SwingConstants.CENTER);
			mainPart.add(deliveryToday);
		} else {
			deliveryToday = new JLabel();
			checkDeliveryToday = new JCheckBox();
			deliveryOption = new JButton();
			deliveryStore = new JLabel();
			deliveryOption();
		}

	}

	public void deliveryOption() {
		deliveryToday.setBounds(1000, 560, 600, 40);
		deliveryToday.setFont(new Font("", Font.BOLD, 20));
		deliveryToday.setText("오늘드림으로 받아보시겠어요?");
		deliveryToday.setHorizontalAlignment(SwingConstants.CENTER);
		// deliveryToday.setOpaque(true);
		mainPart.add(deliveryToday);

		// deliveryStore.setOpaque(true);
		deliveryStore.setBounds(1000, 600, 600, 40);
		deliveryStore.setFont(new Font("", Font.BOLD, 20));
		deliveryStore.setHorizontalAlignment(SwingConstants.CENTER);

		mainPart.add(deliveryStore);
		checkDeliveryToday.setBounds(130, 10, 20, 20);
		checkDeliveryToday.addActionListener(this);
		checkDeliveryToday.setBackground(Color.white);
		checkDeliveryToday.setBorder(null);
		deliveryToday.add(checkDeliveryToday);

		deliveryOption.setBounds(480, 10, 100, 30);
		deliveryOption.setFont(new Font("", Font.BOLD, 15));
		deliveryOption.setText("배송옵션보기");
		deliveryOption.setForeground(Color.LIGHT_GRAY);
		deliveryOption.setBackground(Color.white);
		deliveryOption.setBorder(null);
		deliveryOption.addActionListener(this);
		deliveryToday.add(deliveryOption);
	}

	public void setButton() {
		cart.setBounds(1000, 650, 250, 50);
		cart.setBackground(Color.WHITE);
		cart.setText("장바구니");
		cart.setFont(new Font("", Font.BOLD, 20));
		cart.setForeground(Color.PINK);
		cart.addActionListener(this);
		mainPart.add(cart);

		purchase.setBounds(1300, 650, 250, 50);
		purchase.setBackground(Color.PINK);
		purchase.setText("바로구매");
		purchase.setFont(new Font("", Font.BOLD, 20));
		purchase.setForeground(Color.WHITE);
		purchase.addActionListener(this);
		mainPart.add(purchase);
		if (ItemManager.instance.checkStock(item.getCategory(), item).equals("품절")) {
			cart.setBackground(Color.GRAY);
			cart.setText("일시 품절");
			cart.setForeground(Color.white);
			purchase.setBackground(Color.white);
			purchase.setText("재입고 알림");
			purchase.setForeground(Color.pink);
		}

		checkAvailableStore.setBounds(1100, 710, 500, 50);
		checkAvailableStore.setFont(new Font("", Font.BOLD, 20));
		checkAvailableStore.setText("구매 가능 매장을 확인하세요");
		checkAvailableStore.setBackground(Color.white);
		checkAvailableStore.setBorder(null);
		checkAvailableStore.addActionListener(this);

		try {
			Image img = new ImageIcon("./src/images/logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			checkAvailableStore.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		mainPart.add(checkAvailableStore);

	}

	public void setDetailedInfo() {
		itemImage.setBounds(350, 50, 600, 600);
		try {
			Image img = new ImageIcon(item.getImageName()).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
			itemImage.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		mainPart.add(itemImage);

		itemName.setText(item.getItemName());
		itemName.setVerticalAlignment(SwingConstants.TOP);
		itemName.setBounds(1000, 50, 600, 100);
		itemName.setFont(new Font("", Font.BOLD, 30));
		mainPart.add(itemName);

		for (int i = 0; i < itemInfo.length; i++) {
			itemInfo[i][0] = new JLabel(labelList[i][0], SwingConstants.LEFT);
			itemInfo[i][1] = new JLabel(labelList[i][1], SwingConstants.RIGHT);
			itemInfo[i][0].setBounds(1000, 150 + (i * 50), 300, 50);
			itemInfo[i][1].setBounds(1300, 150 + (i * 50), 300, 50);
			itemInfo[i][0].setForeground(Color.BLACK);
			itemInfo[i][1].setForeground(Color.black);
			mainPart.add(itemInfo[i][0]);
			mainPart.add(itemInfo[i][1]);
		}
		itemInfo[0][1].setText(item.getPrice() + " 원");
		itemInfo[0][1].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][1].setText(item.getPrice() + " 원");
		itemInfo[6][0].setLocation(1000, 520);
		itemInfo[6][1].setLocation(1300, 520);
		itemInfo[6][0].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][0].setForeground(Color.red);
		itemInfo[6][1].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][1].setForeground(Color.red);

	}

	public void setCount() {

		itemCount.setBounds(1000, 450, 600, 70);
		itemCount.setText("        구매수량");
		itemCount.setBackground(Color.LIGHT_GRAY);
		itemCount.setOpaque(true);
		itemCount.setForeground(Color.black);
		mainPart.add(itemCount);

		count.setBounds(490, 15, 40, 40);
		count.setText(item.getCount() + "");
		count.setVerticalAlignment(SwingConstants.CENTER);
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setBackground(Color.white);
		count.setOpaque(true);
		count.setForeground(Color.BLACK);
		itemCount.add(count);

		itemPlus.setBounds(530, 15, 40, 40);
		itemPlus.setText("+");
		itemPlus.setFont(new Font("", Font.BOLD, 11));
		itemPlus.setBackground(Color.LIGHT_GRAY);
		itemPlus.addActionListener(this);
		itemCount.add(itemPlus);

		itemMinus.setBounds(450, 15, 40, 40);
		itemMinus.setText("-");
		itemMinus.setFont(new Font("", Font.BOLD, 10));
		itemMinus.setBackground(Color.LIGHT_GRAY);
		itemMinus.addActionListener(this);
		itemCount.add(itemMinus);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == itemPlus) {
			if (item.getCount() < 10) {
				item.updateCount(1);
			}
			count.setText(item.getCount() + "");
			itemInfo[6][1].setText(item.getCount() * item.getPrice() + " 원");
		} else if (e.getSource() == itemMinus) {
			if (item.getCount() > 1) {
				item.updateCount(-1);
			}
			count.setText(item.getCount() + "");
			itemInfo[6][1].setText(item.getCount() * item.getPrice() + " 원");
		} else if (e.getSource() == purchase) {
			if (UserManager.usermanager.logIdx == -1) {
				JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
			} else {
				if (purchase.getText().equals("바로구매")) {
					if (checkDeliveryToday.isSelected()) {
						int index = StoreManager.instance.storeIndex(availableStore.getCity(), availableStore);
						String data = StoreManager.instance.storeList.get(availableStore.getCity()).get(index)
								.findItem(item);
						if (data.equals("판매하지 않는 상품")) {
							JOptionPane.showMessageDialog(null, "현재 판매하지 않는 상품입니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("현재 해당 지점에 재고 없음")) {
							JOptionPane.showMessageDialog(null, "현재 해당 지점에서 일시 품절 되었습니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("재고 수량 부족")) {
							JOptionPane.showMessageDialog(null, "현재 해당 지점에 재고가 부족합니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else {
							PurchasePanel purchasePanel = null;
							JScrollPane scroll = new JScrollPane();
							if (checkDeliveryToday.isSelected()) {
								purchasePanel = new PurchasePanel(item, true);
							} else {
								purchasePanel = new PurchasePanel(item, false);
							}
							Dimension size = new Dimension();
							size.setSize(1920, 1110);

							purchasePanel.setPreferredSize(size);
							scroll.setViewportView(purchasePanel);
							Main.frame.setContentPane(scroll);
							Main.frame.revalidate();
						}
					}else{
						String data = ItemManager.instance.checkStock(item.getCategory(), item);
						if (data.equals("구매 가능")) {
							PurchasePanel purchasePanel = null;
							JScrollPane scroll = new JScrollPane();
							if (checkDeliveryToday.isSelected()) {
								purchasePanel = new PurchasePanel(item, true);
							} else {
								purchasePanel = new PurchasePanel(item, false);
							}
							Dimension size = new Dimension();
							size.setSize(1920, 1060);

							purchasePanel.setPreferredSize(size);
							scroll.setViewportView(purchasePanel);
							Main.frame.setContentPane(scroll);
							Main.frame.revalidate();
						} else if (data.equals("재고 수량 부족")) {
							JOptionPane.showMessageDialog(null, "재고 수량이 부족합니다.", "안내", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "현재 판매되지 않는 상품입니다.", "안내", JOptionPane.WARNING_MESSAGE);
						}
					}

				}
			}
		} else if (e.getSource() == cart) {
			if (cart.getText().equals("장바구니")) {
				if (UserManager.usermanager.logIdx == -1) {
					JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
				} else {
					if (checkDeliveryToday.isSelected()) {
						int index = StoreManager.instance.storeIndex(availableStore.getCity(), availableStore);
						String data = StoreManager.instance.storeList.get(availableStore.getCity()).get(index)
								.findItem(item);
						if (data.equals("판매하지 않는 상품")) {
							JOptionPane.showMessageDialog(null, "현재 판매하지 않는 상품입니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("현재 해당 지점에 재고 없음")) {
							JOptionPane.showMessageDialog(null, "현재 해당 지점에서 일시 품절 되었습니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("재고 수량 부족")) {
							JOptionPane.showMessageDialog(null, "현재 해당 지점에 재고가 부족합니다", "안내", JOptionPane.WARNING_MESSAGE);
						} else {
							
							if (checkDeliveryToday.isSelected()) {
									UserManager.usermanager.userList.get(UserManager.logIdx).cart.add(item.getImageName(),
									item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , true,item.getCategory());
							} else {
									UserManager.usermanager.userList.get(UserManager.logIdx).cart.add(item.getImageName(),
									item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , false,item.getCategory());
							}
							FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");
							JOptionPane.showMessageDialog(null, "장바구니 담기 완료", "안내", JOptionPane.WARNING_MESSAGE);
						} //
					}else {
						String data = ItemManager.instance.checkStock(item.getCategory(), item);
						if (data.equals("구매 가능")) {
						UserManager.usermanager.userList.get(UserManager.logIdx).cart.add(item.getImageName(),
								item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , false,item.getCategory());
						FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");
						JOptionPane.showMessageDialog(null, "장바구니 담기 완료", "안내", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("재고 수량 부족")) {
						JOptionPane.showMessageDialog(null, "재고 수량이 부족합니다.", "안내", JOptionPane.WARNING_MESSAGE);
						} else {
						JOptionPane.showMessageDialog(null, "현재 판매되지 않는 상품입니다.", "안내", JOptionPane.WARNING_MESSAGE);
						}
					}

				}
			}

		} else if (e.getSource() == checkDeliveryToday) {
			if (checkDeliveryToday.isSelected()) {

				if (UserManager.logIdx == -1) {
					JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
					checkDeliveryToday.setSelected(false);
				} else {
					String data = "";
					Store temp = StoreManager.instance.findStore(
							UserManager.usermanager.userList.get(UserManager.logIdx).userCity,
							UserManager.usermanager.userList.get(UserManager.logIdx).userStreet,
							UserManager.usermanager.userList.get(UserManager.logIdx).userCode);
					if (temp != null) {
						String status = temp.findItem(item);
						if (status.equals("현재 해당 지점에 재고 없음")) {
							cart.setBackground(Color.GRAY);
							cart.setText("일시 품절");
							cart.setForeground(Color.white);
							purchase.setBackground(Color.white);
							purchase.setText("재입고 알림");
							purchase.setForeground(Color.pink);
						} else if (status.equals("판매하지 않는 상품")) {
							JOptionPane.showMessageDialog(null, "오늘 드림 가능한 매장이 없습니다.", "안내",
									JOptionPane.WARNING_MESSAGE);
							checkDeliveryToday.setSelected(false);
						} else {
							if (cart.getText().equals("일시 품절")) {
								 cart.setBackground(Color.WHITE);
								 cart.setText("장바구니");
								 cart.setForeground(Color.PINK);

								purchase.setBackground(Color.PINK);
								purchase.setText("바로구매");
								purchase.setForeground(Color.WHITE);
							}
							availableStore = temp;
						}
						data = temp.getStoreName() + " : ";
						data += status;
						deliveryStore.setText(data);
					} else {
						deliveryStore.setText("오늘 드림 가능한 매장이 없음");
						JOptionPane.showMessageDialog(null, "오늘 드림 가능한 매장이 없습니다.", "안내", JOptionPane.WARNING_MESSAGE);
						checkDeliveryToday.setSelected(false);
					}
				}
			} else {
				deliveryStore.setText("");
				if (purchase.getText().equals("재입고 알림")) {
					if (ItemManager.instance.checkStock(item.getCategory(), item).equals("구매 가능")) {

						cart.setBackground(Color.WHITE);
						cart.setText("장바구니");
						cart.setForeground(Color.PINK);

						purchase.setBackground(Color.PINK);
						purchase.setText("바로구매");
						purchase.setForeground(Color.WHITE);
					}
				} else if (purchase.getText().equals("바로구매")) {
					if (ItemManager.instance.checkStock(item.getCategory(), item).equals("품절")) {
						cart.setBackground(Color.GRAY);
						cart.setText("일시 품절");
						cart.setForeground(Color.white);
						purchase.setBackground(Color.white);
						purchase.setText("재입고 알림");
						purchase.setForeground(Color.pink);
					}
				}

			}
		} else if (e.getSource() == checkAvailableStore) {
			PopupFactory pf = new PopupFactory();
			p = pf.getPopup(Main.frame, storeList, 450, 100);
			p.show();

			// Main.frame.setContentPane(new StoreListPanel(item));
			// Main.frame.revalidate();
		}
	}
}
