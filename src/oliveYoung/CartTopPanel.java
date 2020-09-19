package oliveYoung;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CartTopPanel extends JPanel{
	
	ImageIcon topImage = null;
	String fileName = "./src/images/타이틀.png";
	
	JLabel label1 = null;
	JLabel label2 = null;
	JLabel label3 = null;
	JLabel label4 = null;
	
	/*[생성자]*/
	public CartTopPanel() { 
		/*[패널 정보]*/
		this.setLayout(null);
		this.setSize(1900 , 130);
		this.setLocation(0, 210);
		
		
		/*[배경]*/
		Image image = new ImageIcon(fileName).getImage().getScaledInstance(1900, 250, Image.SCALE_SMOOTH); 
		topImage = new ImageIcon(image);
		Font font = new Font("", Font.BOLD, 50);
		label1 = new JLabel("장바구니");
		label1.setFont(font);
		label1.setBounds(440, 20, 300, 100);
		add(label1);
		
		/*[탭1]*/
		font = new Font("", Font.BOLD, 22);
		label2 = new JLabel("01 장바구니 > ");
		label2.setFont(font);
		label2.setBounds(1040, 40, 200, 50);
		add(label2);
		
		/*[탭2]*/
		font = new Font("", Font.PLAIN, 20);
		label3 = new JLabel("02 주문/결제 >");
		label3.setFont(font);
		label3.setBounds(1190, 40, 200, 50);
		add(label3);
		
		/*[탭3]*/
		label4 = new JLabel("03 주문완료");
		label4.setFont(font);
		label4.setBounds(1340, 40, 200, 50);
		add(label4);
		
		
	}
	/*[생성자]*/
	
	
	
	public void render(Graphics g) {
		g.drawImage(topImage.getImage(), 0, 0, null);
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		repaint();

		render(g);
	}

}
