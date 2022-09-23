package day0822;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Work0822Event extends WindowAdapter implements ItemListener {
	
	private Work0822View wv;
	private TableInfoDAO tiDAO;
	
	public Work0822Event(Work0822View wv) throws SQLException {
		this.wv=wv;
		tiDAO=new TableInfoDAO();
	}//Work0822Event
	
	@Override
	public void windowClosing(WindowEvent e) {
		wv.dispose();
	}//windowClosing

	@Override
	public void itemStateChanged(ItemEvent e) {
		int index=wv.getDcbTable().getSelectedIndex();
		String tableName=wv.getDcbmTable().getElementAt(index);
		
		try {
			setTextArea(tableName);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(wv, "DB에 장애가 발생했습니다.");
			e1.printStackTrace();
		}//end catch
		
	}//itemStateChanged
	
	public String[] setTabArr() throws SQLException {
		String[] tabArr=null;
		
		tabArr=tiDAO.selectAllTab().toArray(new String[0]);
		
		return tabArr;
	}//setTabArr
	
	public void setTextArea(String tableName) throws SQLException {
		List<ColumnVO> tableList=tiDAO.selectAllColumn(tableName);
		
		JTextArea jta=wv.getJta();//TextArea 값을 호출
		jta.setText(""); //TextArea를 초기화
		
		if(tableList.isEmpty()) {
			jta.append("테이블 정보가 존재하지 않습니다.");
		}//end if
		
		jta.append("컬럼명\t");
		jta.append("데이터타입\t");
		jta.append("데이터길이\n");
		for(ColumnVO clVO : tableList) {
			jta.append(clVO.getColumn_name()+"\t");
			jta.append(clVO.getData_type()+"\t");
			jta.append(clVO.getData_length()+"\n");
		}//end for
		
	}//setTextArea

}//class
