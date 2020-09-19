package oliveYoung;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInfoBoard extends JPanel implements ActionListener, MouseListener{
	
	JLabel infoLb;
	
	JLabel textLb[];
	
	public UserInfoBoard() {
		setLayout(null);
		addMouseListener(this);

		infoLb = new JLabel();
		infoLb.setBounds(0, 0, 1900, 600);
		infoLb.setOpaque(true);
		add(infoLb);
		
		textLb = new JLabel[4];
		
		setTextField();
		setImage();
	}
	
	public void setTextField() {
		for(int i=0; i<textLb.length; i++) {
			textLb[i] = new JLabel();
			textLb[i].setBackground(Color.WHITE);
			textLb[i].setOpaque(true);
			textLb[i].setSize(150, 20);
		}
		
		// 유저 이름
		textLb[0].setText(UserManager.usermanager.getName());
		textLb[0].setLocation(650, 197);
		infoLb.add(textLb[0]);
	

		// 유저 도시명
		textLb[1].setText(UserManager.usermanager.getCity());
		textLb[1].setLocation(665, 335);
		infoLb.add(textLb[1]);
		

		// 유저 도로명
		textLb[2].setText(UserManager.usermanager.getStreet());
		textLb[2].setLocation(665, 405);
		infoLb.add(textLb[2]);
		
	
		// 유저 우편번호
		textLb[3].setText("" + UserManager.usermanager.getCode());
		textLb[3].setLocation(665, 460);
		infoLb.add(textLb[3]);

	}
	
	public void setImage() {
		Image img = new ImageIcon("./src/images/userInfoBoard.PNG").getImage().getScaledInstance(1900, 600, Image.SCALE_SMOOTH);
		infoLb.setIcon(new ImageIcon(img));
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
		System.out.println(x + " : " + y);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
