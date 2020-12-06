package oliveYoung;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oliveYoung.ItemManager;
import oliveYoung.UserManager;
import oliveYoung.User;

public class CartMidPanel extends JPanel implements ActionListener, ItemListener {
	// 수량 11 이상시 ComboBox 삭제하고 TextField 넣기 , Revalidate();
	// 수량 10 이하시 TextField 삭제하기 JComboBox 넣기 , Revalidate();
	int n = 0;
	User user = UserManager.usermanager.userList.get(UserManager.logIdx);

	int logIdx = UserManager.usermanager.logIdx;
	Cart cart = user.cart;

	JCheckBox allCheck; // 전체 체크
	JLabel label; // 총 결제금액
	JButton deleteButton; // 선택상품 삭제
	int totalPrice; // 전체가격
	JButton payBtn; // 구매 버튼
	JLabel todaySend; // 오늘 드림 상품

	public CartMidPanel() {

		set();

	}

	public void set() {
		this.setLayout(null);
		allCheck = new JCheckBox("전체체크", true);

		allCheck.setBounds(100, 50, 100, 50);
		allCheck.addItemListener(this);
		add(allCheck);
		for(int t = 0; t < cart.cartList.size(); t++) {
			cart.elseInfo.get(t).count = new JComboBox<String>();
		}

		for (int i = 0; i < cart.cartList.size(); i++) {

			/* 체크박스 */
			cart.elseInfo.get(i).check.setBounds(100, 100 + 220 * i, 50, 50);
			cart.elseInfo.get(i).check.setSelected(true);
			cart.elseInfo.get(i).check.addItemListener(this);
			add(cart.elseInfo.get(i).check);// 체크박스 추가

			/* 이미지 */
			JLabel imageLabel = new JLabel(makeImage(cart.cartList.get(i).imageFile));
			imageLabel.setBounds(300, 100 + 220 * i, 200, 200);
			add(imageLabel);
			
			/* 회사 이름 */
			JLabel titleLabel = new JLabel(cart.cartList.get(i).itemTitleName);
			titleLabel.setBounds(550, 100 + 220 * i, 200, 50);
			add(titleLabel);
			/* 전체 이름 */
			JLabel fullLabel = new JLabel(cart.cartList.get(i).itemFullName);
			fullLabel.setBounds(550, 180 + 220 * i, 200, 50);
			add(fullLabel);
			
			/* 판매가 */
			JLabel priceLabel = new JLabel((cart.cartList.get(i).price + "원"));
			priceLabel.setBounds(800, 100 + 220 * i, 200, 50);
			add(priceLabel);
		
			/* 오늘드림 */
			if (cart.cartList.get(i).today) {
				Font font = new Font("", Font.BOLD, 40);
				todaySend = new JLabel();
				todaySend.setText("오늘드림");
				todaySend.setForeground(Color.PINK);
				todaySend.setBounds(800, 130 + 220 * i, 100, 50);
				add(todaySend);
			} else {

			}
			/* 수량 */
			if (cart.cartList.get(i).buyCount > 10) {// 수량 11이상일경우
				cart.elseInfo.get(i).putCount.setBounds(1000, 100 + 220 * i, 100, 50);
				cart.elseInfo.get(i).putCount.setText(cart.cartList.get(i).buyCount + "");
				cart.elseInfo.get(i).putCount.setHorizontalAlignment(JTextField.CENTER);
				cart.elseInfo.get(i).putCount.addActionListener(this);
				add(cart.elseInfo.get(i).putCount);
				cart.elseInfo.get(i).change.setBounds(1000, 150 + 220 * i, 100, 50);
				cart.elseInfo.get(i).change.setText("변경");
				cart.elseInfo.get(i).change.addActionListener(this);
				add(cart.elseInfo.get(i).change);
			} else if (cart.cartList.get(i).buyCount <= 10) {// 수량 10이하일경우
				
				for (int j = 0; j < 10; j++) {
					cart.elseInfo.get(i).count.addItem(j + 1 + "");
				}
				
				cart.elseInfo.get(i).count.addItem("10+");
				cart.elseInfo.get(i).count.setSelectedIndex(cart.cartList.get(i).buyCount - 1);
				cart.elseInfo.get(i).count.setBounds(1000, 100 + +220 * i, 100, 50);
				((JLabel) cart.elseInfo.get(i).count.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);// 가운데정렬
				cart.elseInfo.get(i).count.addActionListener(this);
				add(cart.elseInfo.get(i).count);
			}

			/* 구매가 */
			cart.elseInfo.get(i).sum.setText((cart.cartList.get(i).buyCount * cart.cartList.get(i).price) + "원");
			cart.elseInfo.get(i).sum.setBounds(1200, 100 + 220 * i, 200, 50);
			add(cart.elseInfo.get(i).sum);

			/* 배송정보 */
			JLabel fedex = new JLabel("무료배송");
			fedex.setBounds(1300, 100 + 220 * i, 200, 50);
			add(fedex);

			Font font = new Font("", Font.BOLD, 10);
			JLabel fedex2 = new JLabel("도서·산간 제외");
			fedex2.setBounds(1300, 130 + 220 * i, 200, 70);
			fedex2.setFont(font);
			add(fedex2);

			/* 선택 */
			cart.elseInfo.get(i).buy.setText("바로구매");
			cart.elseInfo.get(i).buy.setBounds(1600, 80 + 220 * i, 200, 60);
			cart.elseInfo.get(i).buy.addActionListener(this);
			add(cart.elseInfo.get(i).buy);

			cart.elseInfo.get(i).delete.setText("삭제");
			cart.elseInfo.get(i).delete.setBounds(1600, 150 + 220 * i, 200, 60);
			cart.elseInfo.get(i).delete.addActionListener(this);
			add(cart.elseInfo.get(i).delete);
		}
		// UserManager.userList.get[logIdx] , , , ,
		deleteButton = new JButton("선택상품 삭제");
		deleteButton.setBounds(100, 220 + 220 * cart.cartList.size(), 200, 50);
		deleteButton.addActionListener(this);
		add(deleteButton);

		Font font = new Font("", Font.BOLD, 40);
		payBtn = new JButton("결제하기");
		payBtn.setBackground(Color.PINK);
		payBtn.setBounds(1500, 220 + 220 * cart.cartList.size(), 300, 50);
		payBtn.addActionListener(this);
		add(payBtn);
		// totalPrice
		font = new Font("", Font.BOLD, 20);

		totalPrice = totalPrice();
		label = new JLabel("총 결제 금액 : " + totalPrice + "원");
		label.setFont(font);
		label.setBounds(1000, 220 + 220 * cart.cartList.size(), 400, 100);
		add(label);

	}

	public ImageIcon makeImage(String imageFile) { // 이미지 아이콘 만드는 메소드
		Image image = new ImageIcon(imageFile).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	public int totalPrice() {
		int price = 0;
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (cart.elseInfo.get(i).check.isSelected()) {
				price += cart.cartList.get(i).buyCount * cart.cartList.get(i).price;
			}
		}

		return price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		/* [수량 변경] */
		/* [수량 변경] */
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (e.getSource() == cart.elseInfo.get(i).count) {
				if (cart.elseInfo.get(i).count.getSelectedIndex() >= 0
						&& cart.elseInfo.get(i).count.getSelectedIndex() < 10) {
					String temp = (String) cart.elseInfo.get(i).count.getSelectedItem();
					cart.cartList.get(i).buyCount = Integer.parseInt(temp);
					cart.elseInfo.get(i).sum.setText(cart.cartList.get(i).buyCount * cart.cartList.get(i).price + "원");
					FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");

					label.setText("총 결제 금액 : " + totalPrice() + "원");
				} else if (cart.elseInfo.get(i).count.getSelectedIndex() == 10) {
						this.remove(cart.elseInfo.get(i).count);
						cart.elseInfo.get(i).putCount.setBounds(1000, 100 + 220 * i, 100, 50);
						cart.elseInfo.get(i).putCount.setText(cart.cartList.get(i).buyCount + "");
						cart.elseInfo.get(i).putCount.setHorizontalAlignment(JTextField.CENTER);
						cart.elseInfo.get(i).putCount.addActionListener(this);
						add(cart.elseInfo.get(i).putCount);
						cart.elseInfo.get(i).change.setBounds(1000, 150 + 220 * i, 100, 50);
						cart.elseInfo.get(i).change.setText("변경");
						cart.elseInfo.get(i).change.addActionListener(this);
						add(cart.elseInfo.get(i).change);
						revalidate();
						repaint();
					}
				}
						
				
				
			if(e.getSource() == cart.elseInfo.get(i).change) {
				String temp = cart.elseInfo.get(i).putCount.getText(); // 변경 버튼을 누르는 순간 , 텍스트에 있는 숫자때문에
				cart.cartList.get(i).buyCount = Integer.parseInt(temp); // buyCount가 바뀐다.
				if(Integer.parseInt(cart.elseInfo.get(i).putCount.getText()) <= 10) {
					
					Component[] componentList = this.getComponents();

					for (Component c : componentList) {
						remove(c);
					}
					
					set();
					revalidate();
					repaint();
				}else {
				cart.elseInfo.get(i).sum.setText(cart.cartList.get(i).buyCount * cart.cartList.get(i).price + "원");
				label.setText("총 결제 금액 : " + totalPrice() + "원");
				
				}

				FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");
			}
			
			}

		/* [선택상품 삭제] */
		if (e.getSource() == deleteButton) {
			Component[] componentList = this.getComponents();

			for (Component c : componentList) {
				remove(c);
			}

			ArrayList<ItemInfo1> temp1 = cart.cartList;
			ArrayList<ItemInfo2> temp2 = cart.elseInfo;

			cart.cartList = new ArrayList<ItemInfo1>();
			cart.elseInfo = new ArrayList<ItemInfo2>();

			for (int i = 0; i < temp1.size(); i++) {
				if (!temp2.get(i).check.isSelected()) {
					cart.cartList.add(temp1.get(i));
					cart.elseInfo.add(temp2.get(i));
				}

			}
			FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");
			set();
			this.revalidate();
			this.repaint();

		}
		/* [주문 하기] */
		if (e.getSource() == payBtn) {
			if (cart.cartList.size() > 0) {
				ArrayList<ItemInfo1> temp1 = new ArrayList<>();
				for (int i = 0; i < cart.cartList.size(); i++) {
					if (cart.elseInfo.get(i).check.isSelected()) {
						temp1.add(cart.cartList.get(i));
					}
				}
				boolean today = temp1.get(0).today;
				for (int i = 1; i < temp1.size(); i++) {
					System.out.println(cart.cartList.get(i).today);
				
						if (today != temp1.get(i).today) {
							JOptionPane.showMessageDialog(null, "오늘드림 상품과 일반상품은 동시 구매가 불가능 합니다", "안내",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					
				}
				cart.buyList = new ArrayList<>();
				cart.buyList = temp1;

				PurchasePanel purchasePanel = new PurchasePanel(cart.buyList);
				JScrollPane scroll = new JScrollPane();
				Dimension size = new Dimension();
				size.setSize(1920, 1060 + (100 * UserManager.usermanager.getBuyListSize()));
				purchasePanel.setPreferredSize(size);
				scroll.setViewportView(purchasePanel);
				Main.frame.setContentPane(scroll);
				Main.frame.revalidate();
			}else{
				JOptionPane.showMessageDialog(null, "선택된 상품이 없습니다", "안내",
						JOptionPane.WARNING_MESSAGE);
			}

		}

		/* [해당상품 구매] */
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (e.getSource() == cart.elseInfo.get(i).buy) {
				PurchasePanel purchasePanel = null;
				JScrollPane scroll = new JScrollPane();
				cart.buyList = new ArrayList<>();
				cart.buyList.add(cart.cartList.get(i));

				purchasePanel = new PurchasePanel(cart.cartList.get(i));

				Dimension size = new Dimension();
				size.setSize(1920, 1110);

				purchasePanel.setPreferredSize(size);
				scroll.setViewportView(purchasePanel);
				Main.frame.setContentPane(scroll);
				Main.frame.revalidate();
				break;
			}

			if (e.getSource() == cart.elseInfo.get(i).delete) {
				Component[] componentList = this.getComponents();

				for (Component c : componentList) {
					remove(c);
				}

				cart.cartList.remove(i);
				cart.elseInfo.remove(i);
				set();
				this.revalidate();
				this.repaint();

			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		label.setText("총 결제 금액 : " + totalPrice() + "원");

		if (e.getItem() == allCheck) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				for (int i = 0; i < cart.elseInfo.size(); i++) {
					cart.elseInfo.get(i).check.setSelected(true);
				}
			}

			if (e.getStateChange() == ItemEvent.DESELECTED) {
				for (int i = 0; i < cart.elseInfo.size(); i++) {
					cart.elseInfo.get(i).check.setSelected(false);
				}
			}
		}

	}

}
