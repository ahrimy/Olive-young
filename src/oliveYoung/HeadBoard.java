package oliveYoung;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class HeadBoard extends JPanel implements ActionListener {
	JButton[] headBtn;
	JButton[] menuBtn;
	JButton mainBtn;
	JButton categoryBtn;
	JButton[] subBtn;
	JButton searchBtn;
	JTextField searchArea;
	JLabel user;

	public HeadBoard() {
		setLayout(null);
		headBtn = new JButton[7];
		menuBtn = new JButton[10];
		mainBtn = new JButton();
		categoryBtn = new JButton();
		subBtn = new JButton[2];
		searchBtn = new JButton();
		searchArea = new JTextField();
		if (UserManager.logIdx != -1) {
			user = new JLabel();
			user.setBounds(1000, 10, 50, 30);
			user.setFont(new Font("", Font.BOLD, 12));
			user.setText(UserManager.usermanager.userList.get(UserManager.logIdx).userName);
			add(user);
		}
		setButton();
		setImage();
		addButton();
		validate();
	}

	private void setImage() {

		try {
			Image img = new ImageIcon("./src/images/h1_logo.png").getImage().getScaledInstance(300, 50,
					Image.SCALE_SMOOTH);
			mainBtn.setIcon(new ImageIcon(img));
			img = new ImageIcon("./src/images/ico_search21x212.png").getImage().getScaledInstance(35, 35,
					Image.SCALE_SMOOTH);
			searchBtn.setIcon(new ImageIcon(img));
			img = new ImageIcon("./src/images/ico_menu_off.png").getImage().getScaledInstance(18, 18,
					Image.SCALE_SMOOTH);
			categoryBtn.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private void setButton() {
		mainBtn.setBounds(300, 50, 330, 90);
		mainBtn.setBackground(Color.WHITE);
		mainBtn.setBorder(null);

		headBtn[0] = new JButton("회원가입");
		headBtn[1] = new JButton("로그인");
		headBtn[2] = new JButton("장바구니");
		headBtn[3] = new JButton("주문배송");
		headBtn[4] = new JButton("고객센터");
		headBtn[5] = new JButton("매장안내");
		headBtn[6] = new JButton("Global");
		for (int i = 0; i < headBtn.length; i++) {
			headBtn[i].setFont(new Font("", Font.PLAIN, 12));
			headBtn[i].setBounds((i * 80) + 1050, 10, 70, 30);
			headBtn[i].setBackground(Color.WHITE);
			headBtn[i].setBorder(null);
		}
		LineBorder lineBorder = new LineBorder(new Color(153, 255, 51), 3, true);
		searchArea.setBorder(lineBorder);
		searchArea.setBounds(730, 70, 430, 50);
		searchArea.setBackground(null);

		searchBtn.setBounds(385, 8, 35, 35);
		searchBtn.setBackground(Color.white);
		searchBtn.setBorder(null);
		searchArea.add(searchBtn);

		menuBtn[0] = new JButton("오특");
		menuBtn[1] = new JButton("신상");
		menuBtn[2] = new JButton("베스트");
		menuBtn[3] = new JButton("프리미엄관");
		menuBtn[4] = new JButton("오늘드림");
		menuBtn[5] = new JButton("기획전");
		menuBtn[6] = new JButton("세일");
		menuBtn[7] = new JButton("기프트카드");
		menuBtn[8] = new JButton("멤버십/쿠폰");
		menuBtn[9] = new JButton("이벤트");
		for (int i = 0; i < menuBtn.length; i++) {
			menuBtn[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
			menuBtn[i].setBounds((i * 110) + 500, 165, 108, 30);
			menuBtn[i].setBackground(Color.WHITE);
			menuBtn[i].setBorder(null);
		}
		categoryBtn.setFont(new Font("", Font.BOLD, 18));
		categoryBtn.setText("카테고리");
		categoryBtn.setBounds(300, 165, 190, 30);
		categoryBtn.setBackground(Color.white);
		categoryBtn.setBorder(null);

		subBtn[0] = new JButton("관심 매장소식 ");
		subBtn[1] = new JButton("최근 본 상품 ");
		for (int i = 0; i < subBtn.length; i++) {
			subBtn[i].setFont(new Font("", Font.PLAIN, 15));
			subBtn[i].setBounds((i * 150) + 1320, 70, 100, 50);
			subBtn[i].setBackground(Color.WHITE);
			subBtn[i].setBorder(null);
		}
	}

	private void addButton() {
		mainBtn.addActionListener(this);
		add(mainBtn);
		for (int i = 0; i < headBtn.length; i++) {
			headBtn[i].addActionListener(this);
			add(headBtn[i]);
		}
		add(searchArea);
		searchBtn.addActionListener(this);
		// add(searchBtn);
		for (int i = 0; i < menuBtn.length; i++) {
			menuBtn[i].addActionListener(this);
			add(menuBtn[i]);
		}
		categoryBtn.addActionListener(this);
		add(categoryBtn);
		for (int i = 0; i < subBtn.length; i++) {
			subBtn[i].addActionListener(this);
			add(subBtn[i]);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3.5F));

		g2d.drawLine(0, 208, 1900, 208);
		g2d.setStroke(new BasicStroke(1.0F));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, 150, 1900, 150);
		g2d.drawLine(299, 150, 299, 206);
		g2d.drawLine(490, 150, 490, 206);
		for (int i = 0; i < headBtn.length - 1; i++) {
			g.drawLine((i * 80) + 1125, 15, (i * 80) + 1125, 30);
		}
		g2d.setStroke(new BasicStroke(2.0F));
		g2d.drawLine(1445, 85, 1445, 105);
		if (UserManager.usermanager.logIdx == -1) {
			headBtn[0].setText("회원가입");
			headBtn[1].setText("로그인");
		} else {
			headBtn[0].setText("로그아웃");
			headBtn[1].setText("마이페이지");
		}
	}

	/*
	 * JButton[] headBtn; JButton[] menuBtn; JButton[] subBtn;
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == mainBtn) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		} else if (e.getSource() == categoryBtn) {

		} else if (e.getSource() == searchBtn) {

		}
		if (e.getSource() == menuBtn[2]) {
			ItemPanel.pageName = menuBtn[2].getText();
			Main.frame.setContentPane(new ItemPanel("전체"));
			Main.frame.revalidate();
		}

		if (headBtn[0] == e.getSource()) {
			if (headBtn[0].getText().equals("로그아웃")) {
				UserManager.usermanager.logIdx = -1;
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			} else {
				Main.frame.setContentPane(new JoinPanel());
				Main.frame.revalidate();
			}
		} else if (headBtn[1] == e.getSource()) {
			if (headBtn[1].getText().equals("마이페이지")) {
				Main.frame.setContentPane(new MypagePanel());
				Main.frame.revalidate();

			} else {
				Main.frame.setContentPane(new LoginPanel());
				Main.frame.revalidate();
			}
		} else if (headBtn[2] == e.getSource()) {
			if (headBtn[2].getText().equals("장바구니")) {
				if (UserManager.usermanager.logIdx == -1) {
					JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
				} else {
					CartAllPanel cartPanel = new CartAllPanel();
					JScrollPane scroll = new JScrollPane();
					Dimension size = new Dimension();
					size.setSize(1920, 1080
							+ (250 * UserManager.usermanager.userList.get(UserManager.logIdx).cart.cartList.size()));
					cartPanel.setPreferredSize(size);
					scroll.setViewportView(cartPanel);
					Main.frame.setContentPane(scroll);
					Main.frame.revalidate();
				}
			}
		}

	}
}
