package oliveYoung;

import java.util.ArrayList;

class QnA {
	String title;
	String text;
	// int logIdx;
	String logId;
}

public class QnAManager {

	static QnAManager qnaManager = new QnAManager();
	ArrayList<QnA> qnaList = new ArrayList<>();

	public void addQnA(String addTitle, String addText, String addLogId) {
		QnA temp = new QnA();
		temp.title = addTitle;
		temp.text = addText;
		temp.logId = addLogId;

		qnaList.add(temp);
	}

	public void getList() {
		for (int i = 0; i < qnaList.size(); i++) {
			System.out.println(qnaList.get(i).title + " : " + qnaList.get(i).text + " : " + qnaList.get(i).logId);
		}
	}

	public String saveQnA() {
		String data = "";

		for (int i = 0; i < qnaList.size(); i++) {
			data += qnaList.get(i).title;
			data += "/";
			data += qnaList.get(i).text;
			data += "/";
			data += qnaList.get(i).logId;

			if (i < qnaList.size() - 1) {
				data += "\n";
			}
		}

		// System.out.println("QnA데이터 출력  \n" + data);

		return data;
	}

	public void loadQnA(String data) {
		String info[] = data.split("\n");

		for (int i = 0; i < info.length; i++) {
			QnA temp = new QnA();
			String qnaInfo[] = info[i].split("/");
		
			temp.title = qnaInfo[0];
			temp.text = qnaInfo[1];
			temp.logId = qnaInfo[2];
			
			qnaList.add(temp);
		}
	}

	public void removeQnA() {

		System.out.println("qnaList.size() = " + qnaList.size());
		System.out.println("현재 로그인한 아이디 = " + UserManager.usermanager.userList.get(UserManager.logIdx).userId);

		
		int i=0; 
		while(i < qnaList.size()) {
			if (QnAManager.qnaManager.qnaList.get(i).logId.equals(UserManager.usermanager.userList.get(UserManager.logIdx).userId)) {
				qnaList.remove(i);
				System.out.println("qnaList.size() = " + qnaList.size());
				i = 0;
			}else {
				i += 1;				
			}
		
		}
		
		
		System.out.println(qnaList);
		
		String data = saveQnA();	
		System.out.println(data);
	}

}
