package oliveYoung;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class QnAlistPanel extends JPanel implements ActionListener {

	JButton pageBtn[] = new JButton[5];
	JButton leftBtn;
	JButton rightBtn;

	JTextField title_tf;
	JTextField text_tf;

	int qnaNum = 0;
	JButton tempTitleBtn[] = new JButton[qnaNum];
	JButton titleBtn[] = new JButton[qnaNum];

	int btnSize = 20;

	int pageNum = 1; // 초기 페이지번호

	int maxNum = 5;
	int minNum = 0;

	public QnAlistPanel() {
		setLayout(null);
		setButton();
		upDateBtn();
	}

	public void setButton() {
		// 페이지 버튼
		for (int i = minNum; i < maxNum; i++) {
			pageBtn[i] = new JButton();
			pageBtn[i].setText("" + (i + 1));
			pageBtn[i].setBorder(null);
			pageBtn[i].setBackground(Color.WHITE);
			pageBtn[i].setSize(btnSize, btnSize);
			pageBtn[i].setLocation(220 + (i * btnSize), 400);
			pageBtn[i].addActionListener(this);
			add(pageBtn[i]);
		}

		// 페이지 왼쪽으로 넘기기
		leftBtn = new JButton("◀");
		leftBtn.setBounds(200, 400, btnSize, btnSize);
		leftBtn.setBorder(null);
		leftBtn.setBackground(Color.WHITE);
		leftBtn.addActionListener(this);
		add(leftBtn);

		// 페이지 오른쪽으로 넘기기
		rightBtn = new JButton("▶");
		rightBtn.setBounds(320, 400, btnSize, btnSize);
		rightBtn.setBorder(null);
		rightBtn.setBackground(Color.WHITE);
		rightBtn.addActionListener(this);
		add(rightBtn);

		LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 3, true);

		// 조회용 타이틀 텍스트필드
		title_tf = new JTextField(30);
		title_tf.setBorder(lineBorder);
		title_tf.setBounds(300, 50, 300, 30);
		title_tf.addActionListener(this);
		add(title_tf);

		// 조회용 텍스트 텍스트필드
		text_tf = new JTextField(200);
		text_tf.setBorder(lineBorder);
		text_tf.setBounds(300, 100, 300, 200);
		text_tf.addActionListener(this);
		add(text_tf);
	}

	// 로드하기

	public void loadSet() {

		tempTitleBtn = new JButton[qnaNum];
		int n = 0;
		for (int i = 0; i < QnAManager.qnaManager.qnaList.size(); i++) {
			if (QnAManager.qnaManager.qnaList.get(i).logId.equals(UserManager.usermanager.getId())) {

				tempTitleBtn[n] = new JButton("" + QnAManager.qnaManager.qnaList.get(i).title);
				tempTitleBtn[n].setBorder(null);
				tempTitleBtn[n].setBackground(Color.WHITE);

				n += 1;
			}
		}

		upDateBtn();
	}

	// 버튼추가하기(붙이기X)
	public void setTitleBtn() {

		tempTitleBtn = new JButton[qnaNum + 1];

		int n = 0;
		for (int i = 0; i < QnAManager.qnaManager.qnaList.size(); i++) {
			if (UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(i).logId)) {
				tempTitleBtn[n] = new JButton("" + QnAManager.qnaManager.qnaList.get(i).title);
				tempTitleBtn[n].setBackground(Color.WHITE);
				tempTitleBtn[n].setBorder(null);
				n += 1;
			}
		}

		qnaNum += 1;

	}

	// 추가한 버튼 페이지에 맞춰 붙이기
	public void upDateBtn() {
		
		// 붙어있던 버튼 제거하기
		for (int i = 0; i < titleBtn.length; i++) {	// 붙인버튼들이
			if (titleBtn[i] != null) {	// null이 아니면
				this.remove(titleBtn[i]);
			}
		}

		int startIdx = (pageNum - 1) * maxNum;
		int lastIdx = startIdx + maxNum;

		if (lastIdx > qnaNum) {
			lastIdx = qnaNum;
		}

		// 타이틀 버튼 다시 붙이기
		titleBtn = new JButton[maxNum];

		int n = 0;
		for (int i = startIdx; i < lastIdx; i++) {
			titleBtn[n] = tempTitleBtn[i];
			titleBtn[n].setBounds(50, 50 + (n * 50), 200, 30);
			titleBtn[n].addActionListener(this);
			add(titleBtn[n]);
			n += 1;
		}
	}

	public void rightBtn() {
		maxNum += 5;
		minNum += 5;
		int n = 0;
		for (int i = minNum; i < maxNum; i++) {
			pageBtn[n].setText("" + (i + 1));
			n += 1;
		}
	}

	public void leftBtn() {
		if (minNum == 0) {
			return;
		} else {
			maxNum -= 5;
			minNum -= 5;
			int n = 0;
			for (int i = minNum; i < maxNum; i++) {
				pageBtn[n].setText("" + (i + 1));
				n += 1;
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.setColor(Color.lightGray);
		
		g.drawLine(0, 13, 600, 13);
		g.drawLine(0, 14, 600, 14);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == rightBtn) {
			rightBtn();
		}

		if (e.getSource() == leftBtn) {
			leftBtn();
		}

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == pageBtn[i]) {
				pageNum = Integer.parseInt(pageBtn[i].getText());

				// 보드 업데이트(눌린 페이지에 있는 버튼들로!)
				upDateBtn();
			}
		}

		for (int i = 0; i < maxNum; i++) {
			int loadIdx = -1;
			if (e.getSource() == titleBtn[i]) {
				// loadIdx는 버튼의 번호
				loadIdx = i;

				for (int j = 0; j < QnAManager.qnaManager.qnaList.size(); j++) {
					if (UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(j).logId)
							&& titleBtn[loadIdx].getText().equals(QnAManager.qnaManager.qnaList.get(j).title)) {
						title_tf.setText("" + QnAManager.qnaManager.qnaList.get(j).title);
						text_tf.setText("" + QnAManager.qnaManager.qnaList.get(j).text);
					}

				}
			}
		}

	}

}
