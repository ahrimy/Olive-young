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

		int i=0; 
		while(i < qnaList.size()) {
			if (QnAManager.qnaManager.qnaList.get(i).logId.equals(UserManager.usermanager.userList.get(UserManager.logIdx).userId)) {
				qnaList.remove(i);
				i = 0;
			}else {
				i += 1;				
			}
		
		}
		
		String data = saveQnA();	
	}

}
