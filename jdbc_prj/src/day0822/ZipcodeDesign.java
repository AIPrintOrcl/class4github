package day0822;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ZipcodeDesign extends JFrame {
	private JTextField jtfDong;
	private JTextArea jtaZipcodeView;
	
	public ZipcodeDesign() {
		super("지번 주소 검색");
		
		jtfDong=new JTextField(10);
		jtaZipcodeView=new JTextArea();
		jtaZipcodeView.setEditable(false);
		JButton jbtnSearch=new JButton("우편번호조회");
		
		JScrollPane jspCenter=new JScrollPane(jtaZipcodeView);
		jspCenter.setBorder(new TitledBorder("검색결과"));
		
		JPanel jpNorth=new JPanel();
		jpNorth.add( new JLabel("동이름") );
		jpNorth.add( jtfDong );
		jpNorth.add( jbtnSearch );
		
		add("North",jpNorth);
		add("Center", jspCenter);
		
		ZipcodeEvent ze=new ZipcodeEvent(this);
		jbtnSearch.addActionListener(ze);//버튼 이벤트
		jtfDong.addActionListener(ze);//J.T.F 이벤트
		addWindowListener(ze);
		
		setBounds(100, 100, 500, 600);
		setVisible(true);
	}//ZipcodeDesign
	
	public JTextField getJtfDong() {
		return jtfDong;
	}//getJtfDong

	public JTextArea getJtaZipcodeView() {
		return jtaZipcodeView;
	}//getJtaZipcodeView

	public static void main(String[] args) {
		new ZipcodeDesign();
	}//main

}//class
