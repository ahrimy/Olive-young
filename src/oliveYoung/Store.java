package oliveYoung;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
	private String city;
	private String street;
	private int startCode;

	private int lastCode;
	private String storeName;

	ArrayList<Item> itemList;

	public Store() {

	}

	public Store(String city, String street, int startCode, int lastCode, String storeName) {

		this.city = city;
		this.street = street;
		this.startCode = startCode;
		this.lastCode = lastCode;
		this.storeName = storeName;

		itemList = new ArrayList<>();
	}

	public void print() {
		System.out.println(city);
		System.out.println(street);
		System.out.println(startCode + " " + lastCode);
		System.out.println(storeName);
	}

	public int getItemCount(Item item) {
		for (int i = 0; i < itemList.size(); i++) {
			if (item.getImageName().equals(itemList.get(i).getItemName())) {
				return itemList.get(i).getCount();
			}
		}
		return 0;
	}

	public String findItem(Item item) {
		String data = "";
		int check = -1;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(item.getItemName())) {
				check = i;
				break;
			}
		}
		if (check == -1) {
			data = "판매하지 않는 상품";
		} else {
			if (itemList.get(check).getCount() == 0) {
				data = "현재 해당 지점에 재고 없음";
			} else if (itemList.get(check).getCount() < item.getCount()) {
				data = "재고 수량 부족";
			} else {

				data = "구매가능";
				data += "(남은수량 : " + itemList.get(check).getCount() + ")";
			}
		}
		return data;
	}

	/* add count of item */
	public void putItem(Item item) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(item.getItemName())) {
				itemList.get(i).updateCount(item.getCount());
			}
		}
		ItemManager.instance.updateItemCount(item, StoreManager.instance.getItemCounts(item));
		FileManager.instance.save(StoreManager.instance.saveStoreList(), "store.txt");
	}

	/* reduce count of item */
	public void reduceItem(ItemInfo1 item) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(item.itemFullName)) {
				itemList.get(i).updateCount(-item.buyCount);
				break;
			}
		}
//		ItemManager.instance.updateItemCount(item, StoreManager.instance.getItemCounts(item));
		FileManager.instance.save(StoreManager.instance.saveStoreList(), "store.txt");
	}

	public String saveStore() {
		String data = "";
		data += city;
		data += "_";
		data += street;
		data += "_";
		data += startCode;
		data += "_";
		data += lastCode;
		data += "_";
		data += storeName;
		data += "\n";

		for (int i = 0; i < itemList.size(); i++) {
			data += itemList.get(i).saveItem();
		}
		data.substring(0, data.length() - 1);

		return data;
	}

	public void loadStore(String data) {
		String info[] = data.split("_");
		int check = -1;
		Item temp = new Item(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]), Integer.parseInt(info[5]),
				Boolean.parseBoolean(info[5]));
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(info[1])) {
				itemList.get(i).updateCount(Integer.parseInt(info[4]));
				check = i;
				break;
			}
		}
		if (check == -1) {
			itemList.add(temp);
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStartCode() {
		return startCode;
	}

	public void setStartCode(int startCode) {
		this.startCode = startCode;
	}

	public int getLastCode() {
		return lastCode;
	}

	public void setLastCode(int lastCode) {
		this.lastCode = lastCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
