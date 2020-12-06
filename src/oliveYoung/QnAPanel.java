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
import javax.swing.JTextField;

public class QnAPanel extends JPanel implements ActionListener, MouseListener {

	HeadBoard head_board = null;

	JLabel qnaLabel;
	JLabel textLabel;
	JLabel listLabel;

	JTextField title_tf;
	JTextField text_tf;

	JButton addBtn;
	JButton mypageBtn[] = new JButton[4]; // 장바구니 조회, 회원탈퇴, 메인화면, 마이페이지

	QnAlistPanel QnA_list_panel = null;

	public QnAPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		addMouseListener(this);

		/* 상단화면 */
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		setButton();
		setImage();

		// QnAPanel()이 생성되면 QnAPanel()패널 안에 QnAlistPanel()도 생성
		QnA_list_panel = new QnAlistPanel();
		QnA_list_panel.setBackground(Color.WHITE);
		QnA_list_panel.setBounds(1250, 400, 600, 600);
		add(QnA_list_panel);
		
		// 로그인중인 회원의 qna개수
		checkData();
		QnA_list_panel.loadSet();
	}
	
	public void checkData() {
		int cnt = 0;
		if(QnAManager.qnaManager.qnaList != null) {
			for(int i=0; i<QnAManager.qnaManager.qnaList.size(); i++) {
				if(UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(i).logId)) {
					cnt += 1;
				}
			}
		}
		QnA_list_panel.qnaNum = cnt;
	}

	public void setButton() {

		mypageBtn[0] = new JButton("장바구니");
		mypageBtn[1] = new JButton("회원탈퇴");
		mypageBtn[2] = new JButton("메인화면");
		mypageBtn[3] = new JButton("마이페이지");

		for (int i = 0; i < mypageBtn.length; i++) {
			mypageBtn[i].setFont(new Font("", Font.PLAIN, 15));
			mypageBtn[i].setBounds(110, 500 + (i * 50), 100, 30);
			mypageBtn[i].setBorder(null);
			mypageBtn[i].setBackground(Color.white);
			mypageBtn[i].addActionListener(this);
			add(mypageBtn[i]);
		}

		qnaLabel = new JLabel();
		qnaLabel.setBackground(Color.BLUE);
		qnaLabel.setBounds(0, 210, 1900, 200);
		qnaLabel.setOpaque(true);
		add(qnaLabel);

		textLabel = new JLabel();
		textLabel.setBackground(Color.BLUE);
		textLabel.setBounds(340, 400, 900, 500);
		textLabel.setOpaque(true);
		add(textLabel);

		addBtn = new JButton();
		addBtn.setBounds(665, 935, 200, 60);
		addBtn.setBorder(null);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(this);
		add(addBtn);

		title_tf = new JTextField(30);
		title_tf.setBorder(null);
		title_tf.setBounds(575, 440, 150, 30);
		title_tf.addActionListener(this);
		add(title_tf);

		text_tf = new JTextField(200);
		text_tf.setBorder(null);
		text_tf.setBounds(575, 525, 625, 275);
		;
		text_tf.addActionListener(this);
		add(text_tf);

	}

	public void setImage() {
		Image img = new ImageIcon("./src/images/qnapanel.JPG").getImage().getScaledInstance(1900, 200,
				Image.SCALE_SMOOTH);
		qnaLabel.setIcon(new ImageIcon(img));

		img = new ImageIcon("./src/images/addBtn.PNG").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH);
		addBtn.setIcon(new ImageIcon(img));

		img = new ImageIcon("./src/images/qnaTextLabel.JPG").getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
		textLabel.setIcon(new ImageIcon(img));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.lightGray);
		g.drawLine(100, 540, 210, 540);
		g.drawLine(100, 590, 210, 590);
		g.drawLine(100, 640, 210, 640);
		// g.drawLine(100, 690, 210, 690);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 장바구니
		if (e.getSource() == mypageBtn[0]) {
		}

		// 회원탈퇴
		if (e.getSource() == mypageBtn[1]) {
			UserManager.usermanager.removeMember();
		}

		// 메인화면
		if (e.getSource() == mypageBtn[2]) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}

		// 마이페이지
		if (e.getSource() == mypageBtn[3]) {
			Main.frame.setContentPane(new MypagePanel());
			Main.frame.revalidate();
		}

		// 문의하기(저장)
		if (e.getSource() == addBtn) {
			if (title_tf.getText() == "" || text_tf.getText() == "") {
				JOptionPane.showMessageDialog(null, "제목과 내용을 입력해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "문의하기가 등록되었습니다.", "안내", JOptionPane.WARNING_MESSAGE);
				QnAManager.qnaManager.addQnA(title_tf.getText(), text_tf.getText(), UserManager.usermanager.getId()); // arrayList 저장
				QnAManager.qnaManager.saveQnA();	// data로 만들어 저장
				QnA_list_panel.setTitleBtn();
				QnA_list_panel.upDateBtn();
				FileManager.instance.saveQnA(QnAManager.qnaManager.saveQnA(), "qna.txt");	// data 파일로 저장

			}
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
