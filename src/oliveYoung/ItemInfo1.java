package oliveYoung;

public class ItemInfo1 extends Item{
	//item.getImageName(), item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , today
	String imageFile;	
	String itemTitleName;
	String itemFullName;
	int price;
	int buyCount;
	boolean today; // 오늘 배송 여부
	// 카테고리는 아이템에서 상속받았음.
	
	// sumPrice(수량 X 가격) 는 MidPanel에서 구현
	public ItemInfo1() {
		
	}
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count , boolean today ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
		this.today = today;
		// sumPrice 는 MidPanel에서 구현
	}
	//카테고리 부분 수정
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count , boolean today ,String category) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
		this.today = today;
		// sumPrice 는 MidPanel에서 구현
		super.setCategory(category);
	}
	
	
	//파일로드시
	public void initLoad(String imageFile , String itemTitleName , String itemFullName , int price , int buyCount , boolean today ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount = buyCount;
		this.today = today;
		// sumPrice 는 MidPanel에서 구현
	}
	
	@Override
	public String getImageName() {
		return imageFile;
	}
	
	@Override
	public String getItemName() {
		return itemFullName;
	}
	@Override
	public String getItemTitle() {
		return itemTitleName;
	}
	@Override
	public int getPrice() {
		return price;
	}
	
	@Override
	public int getCount() {
		return buyCount;
	}
}
