package kr.co.sist.statement.service;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.statement.dao.Work0818DAO;
import kr.co.sist.view.Work0818View;

public class Work0818Service {
	private Work0818View wv;
	
	public Work0818Service(Work0818View wv) {
		this.wv=wv;
	}//Work0818Service
	
	public void addWork_Jdbc(JTextField jtfName) throws SQLException {
		String name=jtfName.getText().trim();
		Work0818DAO wd=null;
		
		if(name.isEmpty()) {
			JOptionPane.showMessageDialog(wv, "이름을 입력해 주세요.");
			return;
		}//end if
		wd=new Work0818DAO();
		int rowCnt=wd.insertName(name);
		if(rowCnt==1) {
			JOptionPane.showMessageDialog(wv, "INSERT 완료");
		}//end if
		
	}//addWork_Jdbc
}//class
