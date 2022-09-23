package day0823;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextArea;

public class Work0823Event extends WindowAdapter {

	Work0823View wv;
	
	public Work0823Event(Work0823View wv) {
		this.wv=wv;
	}//Work0823Event
	
	@Override
	public void windowClosing(WindowEvent e) {
		wv.dispose();
	}//windowClosing
	
	public void setTextArea(String maker) throws SQLException{
		Work0823DAO wDAO=new Work0823DAO();
		List<Work0823VO> list=wDAO.selectCar(maker);
		
		JTextArea jta=wv.getJta();
		jta.setText("");
		
		jta.append("제조국\t제조사\t모델명\t연식\t가격\t옵션\n");
		
		if(list.isEmpty()) {
			jta.append("검색한 내용이 없습니다.");
		}//end if
		
		for(Work0823VO wVO:list) {
			jta.append(wVO.getCountry() + "\t");
			jta.append(wVO.getMaker() + "\t");
			jta.append(wVO.getModel() + "\t");
			jta.append(wVO.getCar_year() + "\t");
			jta.append(wVO.getPrice() + "\t");
			jta.append(wVO.getCar_option() + "\n");
		}//end for
	}//setTextArea

	
}
