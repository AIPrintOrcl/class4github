package day0823;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Work0823View extends JFrame {

	private JTextArea jta;
	
	public Work0823View() {
		String makerName=JOptionPane.showInputDialog("차량 제조사");
		
		jta=new JTextArea(10,5);
		jta.setEditable(false);
		
		Work0823Event we=new Work0823Event(this);
		
		try {
			we.setTextArea(makerName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addWindowListener(we);
		
		add("Center", jta);
		
		setBounds(100, 100, 1000, 600);
		setVisible(true);
		
	}//Work0823View
	
	public JTextArea getJta() {
		return jta;
	}

	public static void main(String[] args) {
		new Work0823View();
		
	}

}
