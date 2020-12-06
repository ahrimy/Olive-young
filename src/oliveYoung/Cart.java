package oliveYoung;

import java.util.ArrayList;

// 아이템 타이틀 이름 , 아이템 전체 이름 , 판매가  , 

public class Cart {
	
	String data = ""; // 파일에 저장할 데이타 
	
	public ArrayList<ItemInfo1> cartList = new ArrayList<ItemInfo1>(); // 장바구니 전체 목록
	public ArrayList<ItemInfo1> buyList = new ArrayList<ItemInfo1>(); // 체크된 실제 구매할 상품 목록
	public ArrayList<ItemInfo2> elseInfo = new ArrayList<>(); 
	//카테고리 부분 수정
	public void add(String imageFile , String itemTitleName ,String itemFullName ,int price , int count , boolean today,String category){
			if(findItem(itemFullName) != -1) { // 중복 있을때
				int index = findItem(itemFullName);
				cartList.get(index).today = today;
				cartList.get(index).buyCount += count;
				
				/*채우기*/
			
				
				
			}else { // 중복 없을때
				
				ItemInfo1 temp = new ItemInfo1(imageFile ,itemTitleName , itemFullName, price , count , today,category);
				cartList.add(temp);
				
				ItemInfo2 temp2 = new ItemInfo2();
				
				elseInfo.add(temp2);
				
				
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// 파일 저장 추가
			}
	}
	
	
	public int findItem(String itemFullName){ // 검색 빠르게 도와주는 메소드 - > HashMap과 ArrayList 연결
		int index = -1;
		for(int i = 0; i < cartList.size(); i++) {
			if(itemFullName.equals(cartList.get(i).itemFullName)) {
				index = i;
			}
		}
		return index;
	}
	
	
	public void loadCart(String imageFile , String itemTitleName ,String itemFullName ,int price , int buyCount , boolean today,String category) {
		if(findItem(itemFullName) == -1) {
		ItemInfo1 temp = new ItemInfo1();
		temp.initLoad(imageFile, itemTitleName, itemFullName, price, buyCount, today);
		temp.setCategory(category);
		cartList.add(temp);
		
		ItemInfo2 temp2 = new ItemInfo2();
		
		elseInfo.add(temp2);
		}
	}
	
}
