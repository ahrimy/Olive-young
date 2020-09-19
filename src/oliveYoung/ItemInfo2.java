package oliveYoung;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class ItemInfo2 {
	JCheckBox check = new JCheckBox(); // 체크박스 체크할시 , buyList에 추가
	
	
	JTextField putCount = new JTextField();
	JComboBox<String> count = new JComboBox<>(); // 장바구니에 담은 아이템 수량
	JButton change = new JButton();
	
	JLabel sum = new JLabel(); // 총 갯수
	
	JButton buy = new JButton(); // 해당 제품 즉시 구매버튼
	JButton delete = new JButton(); // 장바구니 목록에서 삭제버튼
	
}
