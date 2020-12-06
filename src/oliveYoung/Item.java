package oliveYoung;

public class Item {
	private String category;
	private String itemTitle;
	private String itemName; // 상품명
	private String imageName;
	private int price; // 가격
	// static int systemCount; // 상품재고
	private int count; // 유저 구매수량
	private boolean onlyOnline;
	
	public Item() {
	}
	public Item(String category, String itemTitle,String itemName, String imageName, int price, int count, boolean onlyOnline) {
		this.category = category;
		this.itemTitle = itemTitle;
		this.itemName = itemName;
		this.imageName = imageName;
		this.price = price;
		this.count = count;
		this.onlyOnline = onlyOnline;
	}
public String saveItem(){
	String data = "";
	data+=category;
	data+="_";
	data+=itemTitle;
	data+="_";
	data+=itemName;
	data+="_";
	data+=imageName;
	data+="_";
	data+=price;
	data+="_";
	data+=count;
	data+="_";
	data+=onlyOnline;
	data+="\n";
	
	return data;
}

	public String getItemTitle() {
	return itemTitle;
}
public void setItemTitle(String itemTitle) {
	this.itemTitle = itemTitle;
}
	public boolean isOnlyOnline() {
		return onlyOnline;
	}

	public void setOnlyOnline(boolean onlyOnline) {
		this.onlyOnline = onlyOnline;
	}

	public String getItemName() {
		return itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void updateCount(int count) {
		this.count += count;
	}

}
