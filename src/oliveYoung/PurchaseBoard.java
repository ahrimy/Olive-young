package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PurchaseBoard extends JPanel implements ActionListener{
	ArrayList<ItemInfo1> itemList=null;
	boolean today;
	JLabel[][] list;
	JLabel[] imageList;
	JLabel[][] textList;
	JLabel totalPrice;
	int total;
	JLabel title;
	JButton purchase;
	public PurchaseBoard(ArrayList<ItemInfo1> itemList) {
		setLayout(null);
		this.today = itemList.get(0).today;
		this.itemList = itemList;
		totalPrice = new JLabel();
		totalPrice.setBounds(1000,100+(itemList.size()*100),300,50);
		total = 0;
		title = new JLabel();
		if(today){
			title.setText("오늘 드림");
		}else{
			title.setText("올리브영 배송");
		}
		title.setBounds(450, 0, 200, 50);
		title.setFont(new Font("",Font.BOLD,20));
		add(title);
		for(int i=0;i<itemList.size();i++){
			total+=itemList.get(i).buyCount*itemList.get(i).price;
		}
		totalPrice.setText("Total : "+total +" 원");
		totalPrice.setFont(new Font("",Font.BOLD,30));
		add(totalPrice);

		list = new JLabel[itemList.size()][4];
		imageList = new JLabel[itemList.size()];
		textList = new JLabel[itemList.size()][2];
		setList();
		
		purchase = new JButton("Purchase");
		purchase.setBounds(1300, 100+(itemList.size()*100),300,50);
		purchase.setBackground(Color.white);
		purchase.addActionListener(this);
		add(purchase);
		
	}

	
	public void setList(){
		LineBorder lineBorder = new LineBorder(new Color(153, 255, 51), 3, true);
		for(int i=0;i<list.length;i++){
			
			list[i][0] = new JLabel();
			list[i][0].setBounds(450, 50+(i*100), 650, 100);
			list[i][0].setBorder(lineBorder);
			list[i][1] = new JLabel();
			list[i][1].setBounds(1100, 50+(i*100), 120, 100);
			list[i][1].setText(itemList.get(i).price+" 원");
			list[i][1].setBorder(lineBorder);
			list[i][1].setHorizontalAlignment(SwingConstants.CENTER);
			list[i][1].setVerticalAlignment(SwingConstants.CENTER);
			list[i][2] = new JLabel();
			list[i][2].setBounds(1220, 50+(i*100), 120, 100);
			list[i][2].setText(itemList.get(i).buyCount+"");
			list[i][2].setBorder(lineBorder);
			list[i][2].setHorizontalAlignment(SwingConstants.CENTER);
			list[i][2].setVerticalAlignment(SwingConstants.CENTER);

			list[i][3] = new JLabel();
			list[i][3].setBounds(1340, 50+(i*100), 120, 100);
			list[i][3].setText(itemList.get(i).price*itemList.get(i).buyCount+" 원");
			list[i][3].setBorder(lineBorder);
			list[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			list[i][3].setVerticalAlignment(SwingConstants.CENTER);

			add(list[i][0]);
			add(list[i][1]);
			add(list[i][2]);
			add(list[i][3]);
			
			imageList[i] = new JLabel();
			imageList[i].setBounds(20, 10, 80, 80);
			try {
				Image img = new ImageIcon(itemList.get(i).imageFile).getImage().getScaledInstance(80, 80,
						Image.SCALE_SMOOTH);
				imageList[i].setIcon(new ImageIcon(img));
				
			} catch (Exception ex) {
				System.out.println(ex);
			}
			textList[i][0] = new JLabel();
			textList[i][0].setBounds(110, 20, 600,20);
			textList[i][0].setText(itemList.get(i).itemTitleName);
			textList[i][1] = new JLabel();
			textList[i][1].setBounds(110, 50, 600,20);
			textList[i][1].setText(itemList.get(i).itemFullName);
			
			list[i][0].add(imageList[i]);
			list[i][0].add(textList[i][0]);
			list[i][0].add(textList[i][1]);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==purchase){
			Main.frame.setContentPane(new PurchaseCompletePanel(itemList));
			Main.frame.revalidate();
		}
	}
	
	
	
	
	
}
