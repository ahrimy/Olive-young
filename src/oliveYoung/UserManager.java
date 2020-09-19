package oliveYoung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UserManager {
	// 클래스 : 유저매니저
	// 유저리스트, 회원가입, 로그인, 로그아웃, 회원탈퇴, 회원정보 수정

	static UserManager usermanager = new UserManager();

	ArrayList<User> userList = new ArrayList<>();

	static int logIdx = -1;

	// 회원가입
	public void join(String joinName, String joinId, String joinPw, String joinCity, String joinStreet,
			String joinCode) {
		int check = -1;

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).userId.equals(joinId)) {
				check = i;
			}
		}

		// System.out.println("check : " + check);

		if (check == -1) {
			User temp = new User();
			temp.userName = joinName;
			temp.userId = joinId;
			temp.userPw = joinPw;
			temp.userCity = joinCity;
			temp.userStreet = joinStreet;

			int code = Integer.parseInt(joinCode);
			temp.userCode = code;

			userList.add(temp);
			JOptionPane.showMessageDialog(null, "회원가입 완료!", "안내", JOptionPane.WARNING_MESSAGE);

			// 회원가입 완료후 fileManager에 유저 정보 저장
			FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");

			// 회원가입 완료후 메인화면으로 이동
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "중복아이디 입니다.", "안내", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	// 로그인
	public void logIn(String loginId, String loginPw) {
		int check = -1;

		for (int i = 0; i < userList.size(); i++) {
			if (loginId.equals(userList.get(i).userId)) {
				check = i;
			}
		}

		if (check == -1) {
			JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			int checkPw = -1;
			for (int i = 0; i < userList.size(); i++) {
				if (loginPw.equals(userList.get(check).userPw)) {
					checkPw = i;
				}
			}

			if (checkPw == -1) {
				JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				// 로그인완료 => 멤버패널로 이동
				logIdx = check;

				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}

		}

	}

	// 로그아웃
	public void logOut() {
		Main.frame.setContentPane(new MainPanel());
		Main.frame.revalidate();
		logIdx = -1;
	}

	// 회원정보수정
	public void updateMember(String updateName, String updatePw, String updateCity, String updateStreet,
			String updateCode) {
		userList.get(UserManager.usermanager.logIdx).userName = updateName;
		// userList.get(UserManager.usermanager.logIdx).userId = updateId;
		userList.get(UserManager.usermanager.logIdx).userPw = updatePw;
		userList.get(UserManager.usermanager.logIdx).userCity = updateCity;
		userList.get(UserManager.usermanager.logIdx).userStreet = updateStreet;
		int code = Integer.parseInt(updateCode);
		userList.get(UserManager.usermanager.logIdx).userCode = code;

		// 회원정보 수정 후 fileManager에 유저 정보 저장
		FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");

		JOptionPane.showMessageDialog(null, "정보가 저장되었습니다!", "안내", JOptionPane.WARNING_MESSAGE);
	}

	// 회원탈퇴
	public void removeMember() {
		if (logIdx != -1) {

			int rs = JOptionPane.showConfirmDialog(null, "회원 탈퇴를 진행하시겠습니까?", "안내", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				
				// qna리스트에서 로그인한 유저(logIdx)와 일치하는 ID의 정보 삭제 ==> 저장된 데이터 콘솔에 출력
				QnAManager.qnaManager.removeQnA();
				
				// 유저리스트에서 로그인한 idx 삭제
				userList.remove(logIdx);
				JOptionPane.showMessageDialog(null, "회원탈퇴 완료!", "안내", JOptionPane.WARNING_MESSAGE);
				
				logIdx = -1;

				// 회원 탈퇴후 fileManager에 유저 정보 저장
				FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");
				
				// 회원 탈퇴후 fileManaget에 유저 qna정보 저장
				FileManager.instance.saveQnA(QnAManager.qnaManager.saveQnA(), "qna.txt");
				
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}

		}
	}

	// 회원정보 저장 + 장바구니.
	public String saveUser() {
		String data = "";

		for (int i = 0; i < userList.size(); i++) {
			data += userList.get(i).userName;
			data += "/";
			data += userList.get(i).userId;
			data += "/";
			data += userList.get(i).userPw;
			data += "/";
			data += userList.get(i).userCity;
			data += "/";
			data += userList.get(i).userStreet;
			data += "/";
			data += userList.get(i).userCode;
			data += "\n";
			
		
			// 여기서부터 카트리스트
			data += userList.get(i).cart.cartList.size();
			if(userList.get(i).cart.cartList.size() != 0) {
			data += "\n";
			}
			for(int j = 0; j < userList.get(i).cart.cartList.size(); j++) {
				data += userList.get(i).cart.cartList.get(j).imageFile;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).itemTitleName;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).itemFullName;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).price;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).buyCount;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).today;
				data += "/";
				data += userList.get(i).cart.cartList.get(j).getCategory();
				if(j < userList.get(i).cart.cartList.size() - 1) {
					data += "\n";
				}
				
			}
			
			if (i < userList.size() - 1) {
				data += "\n\n";
			}
			
			
		}
		
		
		System.out.println(data);

		return data;
	}

	// 회원정보 로드후 어레이리스트에 저장 + 장바구니 //카테고리 부분 수정
	public void loadUser(String data) {
		String info[] = data.split("\n\n");

		for (int i = 0; i < info.length; i++) {
			String user[] = info[i].split("\n");
			
			User temp = new User();
			String userinfo[] = user[0].split("/");

			temp.userName = userinfo[0];
			temp.userId = userinfo[1];
			temp.userPw = userinfo[2];
			temp.userCity = userinfo[3];
			temp.userStreet = userinfo[4];
			temp.userCode = Integer.parseInt(userinfo[5]);

			
			
			
			int size = Integer.parseInt(user[1]); // 카트의 cartList의 갯수
			if(size != 0) {
			for(int j = 2; j < 2 + size; j++) {
				String itemInfo[] = user[j].split("/");
				for(int t = 0;  t < size; t++) {
				ItemInfo1 tempItem = new ItemInfo1();
				tempItem.imageFile = "";
				tempItem.imageFile += itemInfo[0];
				tempItem.imageFile += "/";
				tempItem.imageFile += itemInfo[1];
				tempItem.imageFile += "/";
				tempItem.imageFile += itemInfo[2];
				tempItem.imageFile += "/";
				tempItem.imageFile += itemInfo[3];
				tempItem.itemTitleName = itemInfo[4];
				tempItem.itemFullName = itemInfo[5];
				tempItem.price = Integer.parseInt(itemInfo[6]);
				tempItem.buyCount = Integer.parseInt(itemInfo[7]);
				if(itemInfo[8].equals("true")) {
					tempItem.today = true;
				}else {
					tempItem.today = false;
				}
				temp.cart.loadCart(tempItem.imageFile, tempItem.itemTitleName, tempItem.itemFullName, tempItem.price, tempItem.buyCount, tempItem.today,itemInfo[9]);
				}
			}		
			}
				userList.add(temp);	
				
			}
		}
	
	
	// 비밀번호 찾기
	public void findPw(String userName, String userId) {
		int check = -1;
		String pw = "";
		for (int i = 0; i < userList.size(); i++) {
			if (userId.equals(userList.get(i).userId) && userName.equals(userList.get(i).userName)) {
				check = i;
			}
		}

		if (check == -1) {
			JOptionPane.showMessageDialog(null, "회원정보를 찾을 수 없습니다.", "안내", JOptionPane.WARNING_MESSAGE);
		} else {
			pw = userList.get(check).userPw;
			JOptionPane.showMessageDialog(null, "비밀번호는 " + pw + " 입니다.", "안내", JOptionPane.WARNING_MESSAGE);
		}
	}

	// 유저리스트 출력
	public void printUserList() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).userName + ":" + userList.get(i).userId + ":" + userList.get(i).userPw
					+ ":" + userList.get(i).userCity + ":" + userList.get(i).userStreet + ":"
					+ userList.get(i).userCode);
		}
	}

	// 도시명 리턴
	public String getCity() {
		String city = "";
		city += userList.get(logIdx).userCity;

		return city;
	}

	// 도로명 리턴
	public String getStreet() {
		String street = "";
		street += userList.get(logIdx).userStreet;

		return street;
	}

	// 우편번호 리턴
	public int getCode() {
		int code = 0;
		code += userList.get(logIdx).userCode;

		return code;
	}

	public String getId() {
		return userList.get(logIdx).userId;
	}
	
	public String getName() {
		return userList.get(logIdx).userName;
	}
	
	public int getCartSize() {
		return userList.get(logIdx).cart.cartList.size();
	}
	public int getBuyListSize() {
		return userList.get(logIdx).cart.buyList.size();
	}

}
