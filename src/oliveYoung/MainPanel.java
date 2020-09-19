package oliveYoung;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	/*상단화면*/
	HeadBoard head_board = null;

	JLabel selectLabel;
	JLabel backLabel;
	JLabel firstCategory;
	JLabel secondCategory;
	JLabel[] topCtgry;
	int imgCnt = 0;
	String imageList[] = { "./src/images/4778847630093013371.jpg", "./src/images/4825913656622990330.jpg",
			"./src/images/8379252429032192315.jpg", "./src/images/8484518564220394029.jpg" };
	String categoryList[] = { "스킨케어", "메이크업", "바디케어", "헤어케어", "향수/디퓨저", "미용소품", "남성", "건강/위생용품", "건강식품", "일반식품", "반려동물",
			"베이비", "잡화" };
	String[] middleList = {"페이셜케어","마스크팩","클렌징","선케어","더모 코스메틷"};
	
	public MainPanel() {
		setLayout(null);
		addMouseListener(this); // 마우스 와 패널 연결
		addMouseMotionListener(this); // 마우스 모션 과 패널 연결
		this.setBackground(Color.WHITE);
		/*상단화면*/
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		backLabel = new JLabel();
		backLabel.setSize(1900, 550);
		backLabel.setLocation(0, 210);
		backLabel.setBackground(Color.BLUE);
		backLabel.setOpaque(true);
		add(backLabel);

		selectLabel = new JLabel();
		selectLabel.setSize(192, 550);
		selectLabel.setLocation(299, 0);
		selectLabel.setBackground(Color.LIGHT_GRAY);
		selectLabel.setOpaque(true);
		backLabel.add(selectLabel);

		LineBorder lineBorder = new LineBorder(Color.BLACK, 1, false);
		firstCategory = new JLabel();
		firstCategory.setSize(192, 550);
		firstCategory.setLocation(491, 0);
		firstCategory.setBackground(Color.WHITE);
		firstCategory.setHorizontalAlignment(SwingConstants.CENTER);
		// firstCategory.setBorder(lineBorder);
		backLabel.add(firstCategory);

		secondCategory = new JLabel();
		secondCategory.setSize(192, 550);
		secondCategory.setLocation(683, 0);
		secondCategory.setBackground(new Color(220, 220, 220));
		secondCategory.setHorizontalAlignment(SwingConstants.CENTER);
		// secondCategory.setBorder(lineBorder);
		backLabel.add(secondCategory);

		topCtgry = new JLabel[13];
		for (int i = 0; i < 13; i++) {
			topCtgry[i] = new JLabel(categoryList[i]);
			topCtgry[i].setBackground(Color.black);
			topCtgry[i].setBounds(20, i * 38, 170, 38);
			topCtgry[i].setForeground(Color.WHITE);
			selectLabel.add(topCtgry[i]);
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
		imgCnt++;
		if (imgCnt == 80) {
			imgCnt = 0;
		}

		Image img = new ImageIcon(imageList[imgCnt / 20]).getImage().getScaledInstance(1900, 550, Image.SCALE_SMOOTH);
		backLabel.setIcon(new ImageIcon(img));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
//		System.out.println(x + " " + y);
		for (int i = 0; i < 13; i++) {
			if (y > topCtgry[i].getY()+210 && y < 210+topCtgry[i].getY() + 38&&x > 299 && x < 491) {
				topCtgry[i].setOpaque(true);
			}else{
				topCtgry[i].setOpaque(false);
			}
		}
		if (!secondCategory.isOpaque()) {
			if (x > 299 && x < 491 && y > 210 && y < 710) {
				firstCategory.setOpaque(true);
				firstCategory.setText("상품준비중");
			} else if (x < 299 || x > 683 || y < 210 || y > 710) {
				firstCategory.setOpaque(false);
				firstCategory.setText("");
			}
		}
		if (firstCategory.isOpaque()) {
			if (x < 683 && x > 491 && y > 210 && y < 760) {
				secondCategory.setOpaque(true);
				secondCategory.setText("상품준비중");
			} else if (x < 491 || x > 875 || y < 210 || y > 760) {
				secondCategory.setOpaque(false);
				secondCategory.setText("");
			}
		} else {
			secondCategory.setOpaque(false);
			secondCategory.setText("");
		}
	}

}
