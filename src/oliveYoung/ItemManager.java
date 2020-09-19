package oliveYoung;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemManager {
	HashMap<String,ArrayList<Item>> itemList = new HashMap<>();
	public static ItemManager instance = new ItemManager();
	public ItemManager(){
	}
	
	/*add or find category*/
	public void setCategory(String category, Item item){
		int check = 0;
		//�̹� �����ϴ� ī�װ�
		for(String key : itemList.keySet()){
			if(key.equals(category)){
				check = 1;
			}
		}
		//�������� �ʴ� ī�װ� �߰�
		if(check==0){
			itemList.put(category, new ArrayList<Item>());
		}
		putItem(category,item);
	}
	
	/*check if the item is available*/
	public String checkStock(String category,Item item){
		int check = -1;
		for(int i=0;i<itemList.get(category).size();i++){
			if(itemList.get(category).get(i).getItemName().equals(item.getItemName())){
				check = i;
			}
		}
		if(check==-1){
			return "���̻� �Ǹ����� �ʴ� ��ǰ";
		}else{
			
			if(itemList.get(category).get(check).getCount()>=item.getCount()){
				return "���� ����";
			}else{
				if(itemList.get(category).get(check).getCount()==0){
					return "ǰ��";
				}else{
				return "��� ���� ����";
				}
			}
		}
	}
	public void updateItemCount(Item item,int count){
		int index = itemIndex(item.getCategory(),item);
		itemList.get(item.getCategory()).get(index).updateCount(count);
	}
	/*add item*/
	public void putItem(String category,Item item){
		int check = itemIndex(category,item);
		if(check==-1){
			//ī�װ� ���� �������� �ʴ� ��ǰ
			itemList.get(category).add(item);
		}else{
			//ī�װ� ���� �����ϴ� ��ǰ
			itemList.get(category).get(check).updateCount(item.getCount());
		}
		FileManager.instance.save(saveItemList(), "item.txt");
	}
	
	/*reduce count of item*/
	public void reduceItem(ItemInfo1 item){
		for(int i=0;i<itemList.get(item.getCategory()).size();i++){
			if(itemList.get(item.getCategory()).get(i).getItemName().equals(item.getItemName())){
				itemList.get(item.getCategory()).get(i).updateCount(-item.getCount());
				break;
			}
		}

		FileManager.instance.save(saveItemList(), "item.txt");
	}
	
	/*return index of item in itemList at specific category*/
	public int itemIndex(String category,Item item){
		int check = -1;
		for(int i=0;i<itemList.get(category).size();i++){
			if(itemList.get(category).get(i).getItemName().equals(item.getItemName())){
				check = i;
			}
		}
		return check;
	}
	
	/*Load Data*/
	public void loadItemList(String data){
		String info[] = data.split("_");
		Item temp = new Item(info[0],info[1],info[2],info[3],Integer.parseInt(info[4]),Integer.parseInt(info[5]),Boolean.parseBoolean(info[6]));
		setCategory(info[0],temp);
	}
	
	/*Save Data*/
	public String saveItemList(){
		String data = "";
		for(String key:itemList.keySet()){
			for(int i=0;i<itemList.get(key).size();i++){
				data+=itemList.get(key).get(i).saveItem();
			}
		}
		return data;
	}

}
