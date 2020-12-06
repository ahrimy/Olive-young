package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MypagePanel extends JPanel implements ActionListener, MouseListener {
	/* 상단화면 */
	HeadBoard head_board = null;
	// 마이페이지 패널
	// 회원정보 수정, 회원탈퇴

	Font font;

	JTextField name_tf;
	JTextField id_tf;
	JTextField pw_tf;
	JTextField city_tf;
	JTextField street_tf;
	JTextField code_tf;


	JLabel updateLabel;
	JLabel mypageLabel;

	JButton mypageBtn[] = new JButton[4]; // 장바구니 조회, 회원탈퇴, QnA, 메인화면

	JButton saveBtn;
	JButton backBtn;
	JButton removeMemberBtn;

	public MypagePanel() {
		setLayout(null);
		addMouseListener(this);
		setBackground(Color.WHITE);

		/* 상단화면 */
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		setButton();
		setImage();

	}

	public void setButton() {

		mypageBtn[0] = new JButton("장바구니");
		mypageBtn[1] = new JButton("MYQnA 게시판");
		mypageBtn[2] = new JButton("회원탈퇴");
		mypageBtn[3] = new JButton("메인화면");

		for (int i = 0; i < mypageBtn.length; i++) {
			mypageBtn[i].setFont(new Font("", Font.PLAIN, 15));
			mypageBtn[i].setBounds(345, 250 + (i * 50), 100, 30);
			mypageBtn[i].setBorder(null);
			mypageBtn[i].setBackground(Color.white);
			mypageBtn[i].addActionListener(this);
			add(mypageBtn[i]);
		}

		updateLabel = new JLabel();
		updateLabel.setBounds(490, 210, 1100, 300);
		updateLabel.setBackground(Color.BLUE);
		updateLabel.setOpaque(true);
		add(updateLabel);

		name_tf = new JTextField(10);
		name_tf.setBounds(730, 260, 200, 30);
		name_tf.setBorder(null);
		name_tf.setText(UserManager.usermanager.userList.get(UserManager.usermanager.logIdx).userName);
		name_tf.addActionListener(this);
		add(name_tf);

		id_tf = new JTextField(10);
		id_tf.setText(UserManager.usermanager.getId());
		id_tf.setBounds(730, 340, 200, 30);
		id_tf.setBorder(null);
		id_tf.addActionListener(this);
		add(id_tf);

		pw_tf = new JTextField(10);
		pw_tf.setBounds(730, 420, 200, 30);
		pw_tf.setText(UserManager.usermanager.userList.get(UserManager.usermanager.logIdx).userPw);
		pw_tf.setBorder(null);
		pw_tf.addActionListener(this);
		add(pw_tf);
		
		city_tf = new JTextField(10);
		city_tf.setBounds(1270, 260, 230, 20);
		city_tf.setText(UserManager.usermanager.userList.get(UserManager.usermanager.logIdx).userCity);
		city_tf.setBorder(null);
		city_tf.addActionListener(this);
		add(city_tf);
		
		street_tf = new JTextField(10);
		street_tf.setBounds(1270, 340, 230, 20);
		street_tf.setText(UserManager.usermanager.userList.get(UserManager.usermanager.logIdx).userStreet);
		street_tf.setBorder(null);
		street_tf.addActionListener(this);
		add(street_tf);
		
		code_tf = new JTextField(10);
		code_tf.setBounds(1270, 425, 230, 20);
		String strCode = "" + UserManager.usermanager.userList.get(UserManager.usermanager.logIdx).userCode;
		code_tf.setText(strCode);
		code_tf.setBorder(null);
		code_tf.addActionListener(this);
		add(code_tf);

		saveBtn = new JButton();
		saveBtn.setBackground(Color.WHITE);
		saveBtn.setBounds(690, 535, 200, 40);
		saveBtn.setBorder(null);
		saveBtn.addActionListener(this);
		add(saveBtn);

	}

	public void setImage() {

		Image img = new ImageIcon("./src/images/updatePanel.png").getImage().getScaledInstance(1100, 300,
				Image.SCALE_SMOOTH);
		updateLabel.setIcon(new ImageIcon(img));

		img = new ImageIcon("./src/images/updateBtn.png").getImage().getScaledInstance(200, 40, Image.SCALE_SMOOTH);
		saveBtn.setIcon(new ImageIcon(img));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.lightGray);
		g.drawLine(320, 290, 470, 290);
		g.drawLine(320, 340, 470, 340);
		g.drawLine(320, 390, 470, 390);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 회원정보 수정
		if (e.getSource() == saveBtn) {
			if (name_tf.getText().equals("") || pw_tf.getText().equals("") 
					|| city_tf.getText().equals("") || street_tf.getText().equals("") || code_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			} else {
				UserManager.usermanager.updateMember(name_tf.getText(), pw_tf.getText(), city_tf.getText(), street_tf.getText(), code_tf.getText());
			}
		}

		// 장바구니
		if (e.getSource() == mypageBtn[0]) {
		}

		// QnA
		if (e.getSource() == mypageBtn[1]) {
			Main.frame.setContentPane(new QnAPanel());
			Main.frame.revalidate();
		}

		// 회원삭제
		if (e.getSource() == mypageBtn[2]) {
			UserManager.usermanager.removeMember();
		}

		// 메인화면이동
		if (e.getSource() == mypageBtn[3]) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
