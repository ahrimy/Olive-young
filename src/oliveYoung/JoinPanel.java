package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JoinPanel extends JPanel implements ActionListener, MouseListener {
	/* 상단화면 */
	HeadBoard head_board = null;
	// 조인패널(회원가입 패널)
	// 회원가입 가능(아이디, 패스워드, 회원명)

	//Font font;

	JTextField id_tf;
	JTextField pw_tf;
	JTextField name_tf;
	JTextField city_tf;
	JTextField street_tf;
	JTextField code_tf;

	JLabel joinLable;

	JButton joinBtn;
	JButton backBtn;

	JoinPanel() {
		setLayout(null);
		addMouseListener(this);

		setBackground(Color.WHITE);

		/* 상단화면 */
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		joinLable = new JLabel();
		joinLable.setBounds(0, 210, 1900, 600);
		joinLable.setBackground(Color.BLUE);
		joinLable.setOpaque(true);
		add(joinLable);

		//LineBorder lineBorder = new LineBorder(Color.WHITE, 3, true);

		name_tf = new JTextField(10);
		name_tf.setBounds(700, 577, 230, 20);
		name_tf.setBorder(null);
		name_tf.addActionListener(this);
		add(name_tf);

		id_tf = new JTextField(10);
		id_tf.setBounds(700, 623, 230, 20);
		id_tf.setBorder(null);
		id_tf.addActionListener(this);
		add(id_tf);

		pw_tf = new JTextField(10);
		pw_tf.setBounds(700, 698, 230, 20);
		pw_tf.setBorder(null);
		pw_tf.addActionListener(this);
		add(pw_tf);

		city_tf = new JTextField(10);
		city_tf.setBounds(1210, 580, 230, 20);
		city_tf.setBorder(null);
		city_tf.addActionListener(this);
		add(city_tf);
		
		street_tf = new JTextField(10);
		street_tf.setBounds(1210, 625, 230, 20);
		street_tf.setBorder(null);
		street_tf.addActionListener(this);
		add(street_tf);
		
		code_tf = new JTextField(10);
		code_tf.setBounds(1210, 700, 230, 20);
		code_tf.setBorder(null);
		code_tf.addActionListener(this);
		add(code_tf);
		
		joinBtn = new JButton();
		joinBtn.setBackground(Color.WHITE);
		joinBtn.setBounds(650, 810, 150, 60);
		joinBtn.setBorder(null);
		joinBtn.addActionListener(this);
		add(joinBtn);

		backBtn = new JButton();
		backBtn.setBackground(Color.WHITE);
		backBtn.setBounds(900, 810, 300, 50);
		backBtn.setBorder(null);
		backBtn.addActionListener(this);
		add(backBtn);

		setImage();
	}

	public void setImage() {
		Image img = new ImageIcon("./src/images/joinPage.jpg").getImage().getScaledInstance(1900, 600,
				Image.SCALE_SMOOTH);
		joinLable.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/h1_logo.png").getImage().getScaledInstance(300, 50,
				Image.SCALE_SMOOTH);
		backBtn.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/join.PNG").getImage().getScaledInstance(150,60,
				Image.SCALE_SMOOTH);
		joinBtn.setIcon(new ImageIcon(img));
				
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}

		if (e.getSource() == joinBtn) {
			if (name_tf.getText().equals("") || id_tf.getText().equals("") || pw_tf.getText().equals("") 
					|| city_tf.getText().equals("") || street_tf.getText().equals("") || code_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			} else {
				UserManager.usermanager.join(name_tf.getText(), id_tf.getText(), pw_tf.getText(), city_tf.getText(), street_tf.getText(), code_tf.getText());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

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

		System.out.println(x + " " + y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
