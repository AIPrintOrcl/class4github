package day0822;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.common.dao.DbConnection;

/**
 * PreparedStatement의 bind변수와 SQL문의 like 특수문자의 사용.
 * @author user
 */
public class ZipcodeDAO {

	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException{
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		try {
			//1.
			//2.
			con=db.getConn();
			//3.
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select	zipcode, sido, gugun, dong, nvl(bunji,' ') bunji	")
			.append("	from	zipcode												")
			.append("	where	dong like ?||'%'									");
			
			pstmt=con.prepareStatement(selectZipcode.toString());
			//4.
			pstmt.setString(1, dong);
			//5.
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO=null;// 조회된 레코드를 저장할 VO
			while (rs.next()) {
				zVO=new ZipcodeVO();
				//생성된 VO에 조회된 값을 설정.
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//레코드 값을 가진 VO를 list에 추가
				list.add(zVO);
			}//end while
		}finally {
			//6.연결 끊기
			db.dbClose(rs, pstmt, con);
		}//finally
		return list;
	}//selectZipcode
	
}//class
