package oliveYoung;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
	static JFrame frame = new JFrame("oliveYoung");
	public static void main(String[] args) {
		
		FileManager.instance.loadItem("item.txt");
		FileManager.instance.loadUser("user.txt");
		FileManager.instance.loadStore("store.txt");
		FileManager.instance.loadQnA("qna.txt");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		frame.setBounds(0,0,screenSize.width,screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new MainPanel());
		frame.revalidate();
		
	}
}
