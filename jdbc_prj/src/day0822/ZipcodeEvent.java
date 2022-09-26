package day0822;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ZipcodeEvent extends WindowAdapter implements ActionListener {

	private ZipcodeDesign zd;
	
	public ZipcodeEvent(ZipcodeDesign zd) {
		 this.zd=zd;
	}//ZipcodeEvent
	
	@Override
	public void windowClosing(WindowEvent we) {
		zd.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			setZipcode();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(zd, "서비스 도중 장애가 발생하였습니다.\n 잠시 후 다시 시도해 주세요");
			e.printStackTrace();
		}//end catch
	}//actionPerformed
	
	private void setZipcode()throws SQLException{
		//1. 동을 가져와서
		String dong=zd.getJtfDong().getText().trim();
		
		if(dong.equals("")) {//동에 입력된 값이 없다면
			return;
		}//end if
		//2. DAO단의 클래스를 생성하여
		ZipcodeDAO zDAO=new ZipcodeDAO();
		//3. 동을 입력한 후 조회된 우편번호, 주소,,를 가진 List를 받는다.
		List<ZipcodeVO> zipcodeList=zDAO.selectZipcode(dong);
		//4. J.T.A를 초기화 한 후
		JTextArea jta=zd.getJtaZipcodeView();
		jta.setText("");
		//5. J.T.A에 결과를 출력한다.
		jta.append(dong+"으로 검색한 결과\n");
		
		jta.append("우편번호\t주소\n");
		if(zipcodeList.isEmpty()) {
			jta.append("입력하신 동은 존재하지 않습니다.");
		}//end if
		
		for(ZipcodeVO zVO : zipcodeList) {
			jta.append(zVO.getZipcode());
			jta.append("\t");
			jta.append(zVO.getSido()+" "+zVO.getGugun()+" "+zVO.getDong()+" "+zVO.getBunji()+"\n");
		}//end for
		
		zd.getJtfDong().setText("");//사용자 편의성을 위해 JTextFiled를 초기화
		
	}//setZipcode

}//class
