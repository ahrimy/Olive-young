package oliveYoung;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel implements ActionListener, MouseListener{
	/*상단화면*/
	HeadBoard head_board = null;
	// 로그인 패널
	// 로그인가능
	
	Font font;
	
	JTextField id_tf;
	JTextField pw_tf;
	
	JButton logInBtn;
	JButton backBtn;
	JButton findPwBtn;
	
	JLabel loginLabel;
	
	public LoginPanel() {
		setLayout(null);
		addMouseListener(this);
		
		setBackground(Color.WHITE);
		
		/*상단화면*/
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
	
		loginLabel = new JLabel();
		loginLabel.setBackground(Color.BLUE);
		loginLabel.setBounds(600, 300, 700, 400);
		loginLabel.setOpaque(true);
		add(loginLabel);
		
		setButton();
		setImage();
	}
	
	public void setButton() {
		LineBorder lineBorder = new LineBorder(Color.WHITE, 3, true);

		id_tf = new JTextField(10);
		id_tf.setBounds(745, 530, 415, 60);
		id_tf.setFont(new Font("", Font.PLAIN, 30));
		id_tf.setBorder(lineBorder);
		id_tf.addActionListener(this);
		add(id_tf);
		
		pw_tf = new JPasswordField(10);
		pw_tf.setBounds(745, 610, 415, 60);		
		pw_tf.setFont(new Font("", Font.PLAIN, 30));
		pw_tf.setBorder(lineBorder);
		pw_tf.addActionListener(this);
		add(pw_tf);
		
		logInBtn = new JButton();
		logInBtn.setBackground(Color.WHITE);
		logInBtn.setBounds(740, 700, 430, 70);
		logInBtn.setBorder(null);
		logInBtn.addActionListener(this);
		add(logInBtn);
		
		backBtn = new JButton();
		backBtn.setBackground(Color.WHITE);
		backBtn.setBounds(830, 900, 250, 60);
		backBtn.setBorder(null);
		backBtn.addActionListener(this);
		add(backBtn);
		
		findPwBtn = new JButton("비밀번호 찾기");
		findPwBtn.setBackground(Color.WHITE);
		findPwBtn.setBounds(900, 800, 100, 50);
		findPwBtn.setBorder(null);
		findPwBtn.addActionListener(this);
		add(findPwBtn);
	}
	
	public void setImage() {
		Image img = new ImageIcon("./src/images/loginLb.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
		loginLabel.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/loginBtn.png").getImage().getScaledInstance(430, 70, Image.SCALE_SMOOTH);
		logInBtn.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/h1_logo.png").getImage().getScaledInstance(250, 60, Image.SCALE_SMOOTH);
		backBtn.setIcon(new ImageIcon(img));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}
		
		if(e.getSource() == logInBtn) {
			if(id_tf.getText().equals("") || pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			}else {
				UserManager.usermanager.logIn(id_tf.getText(), pw_tf.getText());
			}
		}
		
		if(e.getSource() == findPwBtn) {
			// 비밀번호 찾기 패널 
			Main.frame.setContentPane(new FindPw());
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
		int x = e.getX(); int y = e.getY();
		System.out.println(x + " " + y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
