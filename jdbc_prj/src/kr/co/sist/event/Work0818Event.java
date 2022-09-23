package kr.co.sist.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.statement.service.Work0818Service;
import kr.co.sist.view.Work0818View;

public class Work0818Event extends WindowAdapter implements ActionListener {

	private Work0818View wv;

	public Work0818Event(Work0818View wv) {
		this.wv = wv;
	}// work0818Event

	@Override
	public void windowClosing(WindowEvent e) {
		wv.dispose();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == wv.getJbtnInput()) {
			Work0818Service wService = new Work0818Service(wv);
			try {
				wService.addWork_Jdbc(wv.getJtfName());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
				e.printStackTrace();
			}//end catch

		} // end if
	}// actionPerformed

}
