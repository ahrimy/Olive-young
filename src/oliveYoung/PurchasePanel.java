package oliveYoung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PurchasePanel extends JPanel{
	HeadBoard head_board = null;
	PurchaseBoard purchase = null;
	ArrayList<ItemInfo1> itemList= null;
	UserInfoBoard userinfo_board = null;

	public PurchasePanel(Item item, boolean today) {
		setLayout(null);
		mainSet();
		itemList = new ArrayList<ItemInfo1>();
//		itemList.add(new ItemInfo1(item.getImageName(), item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount()));
		itemList.add(new ItemInfo1(item.getImageName(), item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , today,item.getCategory()));
		purchase = new PurchaseBoard(itemList);
		purchaseSet();
		userSet();
	}
	public PurchasePanel(ArrayList<ItemInfo1> itemList){
		setLayout(null);
		mainSet();
		this.itemList = itemList;
		purchase = new PurchaseBoard(itemList);
		purchaseSet();
		userSet();
	}
	public PurchasePanel(ItemInfo1 item){
		setLayout(null);
		mainSet();
		itemList = new ArrayList<ItemInfo1>();
		this.itemList.add(item);
		purchase = new PurchaseBoard(itemList);
		purchaseSet();
		userSet();
	}
	private void mainSet(){

		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
	}
	private void userSet(){
		userinfo_board = new UserInfoBoard();
		userinfo_board.setBounds(0, 210, 1900, 600);
		add(userinfo_board);
	}
	private void purchaseSet(){
		purchase.setBounds(0, 810, 1900, 300+(100*itemList.size()));
		purchase.setBackground(Color.white);
		add(purchase);
	}

}
