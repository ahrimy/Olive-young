package oliveYoung;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FindPw extends JPanel implements ActionListener, MouseListener{
	/*상단화면*/
	HeadBoard head_board = null;
	
	JLabel findPwLabel;
	
	JTextField name_tf;
	JTextField id_tf;
	
	JButton resultBtn;
	
	
	public FindPw(){
		setLayout(null);
		setBackground(Color.WHITE);
		addMouseListener(this);
		
		/*상단화면*/
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		
		
		setButton();
		setImage();
	}
	
	public void setButton() {
		findPwLabel = new JLabel();
		findPwLabel.setBounds(0, 210, 1900, 300);
		findPwLabel.setBackground(Color.BLUE);
		findPwLabel.setOpaque(true);
		add(findPwLabel);
		
		LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 2, true);
		
		name_tf = new JTextField(10);
		name_tf.setBounds(855, 550, 200, 30);
		name_tf.setText(" 회원 이름을 입력해주세요. ");
		name_tf.setBorder(lineBorder);
		name_tf.addActionListener(this);
		add(name_tf);

		id_tf = new JTextField(10);
		id_tf.setBounds(855, 600, 200, 30);
		id_tf.setText(" 회원 아이디를 입력해주세요. ");
		id_tf.setBorder(lineBorder);
		id_tf.addActionListener(this);
		add(id_tf);
		
		resultBtn = new JButton("비밀번호 찾기");
		resultBtn.setBackground(Color.PINK);
		resultBtn.setBorder(null);
		resultBtn.setBounds(880, 650, 150, 30);
		resultBtn.addActionListener(this);
		add(resultBtn);
		
	}
	
	public void setImage() {
		Image img = new ImageIcon("./src/images/findPw.JPG").getImage().getScaledInstance(1900, 300, Image.SCALE_SMOOTH);
		findPwLabel.setIcon(new ImageIcon(img));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawLine(460, 525, 1490, 525);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resultBtn) {
			UserManager.usermanager.findPw(name_tf.getText(), id_tf.getText());
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
