package day0822;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Work0822View extends JFrame {

	private DefaultComboBoxModel<String> dcbmTable;
	private JComboBox<String> dcbTable;
	private JTextArea jta;
	
	public Work0822View() throws SQLException {
		super("테이블 조회");
		
		JLabel jlblTableName=new JLabel("테이블명");
		
		Work0822Event we = new Work0822Event(this);
		
		String[] tabArr=null;
		tabArr=we.setTabArr();
		
		dcbmTable=new DefaultComboBoxModel<String>(tabArr);
		dcbTable=new JComboBox<String>(dcbmTable);
		jta=new JTextArea();
		JScrollPane jspCenter=new JScrollPane(jta);
		
		JPanel jp=new JPanel();
		jp.add(jlblTableName);
		jp.add(dcbTable);
		
		add("North", jp);
		add("Center", jta);
		
		dcbTable.addItemListener(we);
		addWindowListener(we);
		
		setBounds(100, 100, 400, 600);
		setVisible(true);
		
	}//Work0822View
	
	public DefaultComboBoxModel<String> getDcbmTable() {
		return dcbmTable;
	}

	public JComboBox<String> getDcbTable() {
		return dcbTable;
	}

	public JTextArea getJta() {
		return jta;
	}

	public static void main(String[] args) {
		try {
			new Work0822View();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
