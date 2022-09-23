package kr.co.sist.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.co.sist.event.Work0818Event;



@SuppressWarnings("serial")
public class Work0818View extends JFrame {

	private JTextField jtfName;
	private JButton jbtnInput;
	
	public Work0818View() {
		JLabel lblName = new JLabel("이름");
		jtfName = new JTextField(10);
		jbtnInput = new JButton("입력");
		
		JPanel jpName=new JPanel();
		jpName.add(lblName);
		jpName.add(jtfName);
		jpName.add(jbtnInput);
		
		add("Center", jpName);
		// 이벤트객체 생성
		Work0818Event we = new Work0818Event(this);
		// 리스너에 등록
		addWindowListener(we);
		jbtnInput.addActionListener(we);

		setBounds(100, 100, 250, 150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//work0818View
	
	public JTextField getJtfName() {
		return jtfName;
	}

	public JButton getJbtnInput() {
		return jbtnInput;
	}

	public static void main(String[] args) {
		new Work0818View();
	}

}