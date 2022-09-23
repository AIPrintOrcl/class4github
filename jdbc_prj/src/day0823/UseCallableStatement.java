package day0823;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.common.dao.DbConnection;
import kr.co.sist.vo.CpEmp1VO;

public class UseCallableStatement {

	public void insertCpEmp1() throws SQLException{
		
		CpEmp1VO ceVO=new CpEmp1VO(3334,10,2222,"김태규",null);
		
		Connection con=null;//DB연결 유지, Transaction처리, 쿼리문 생성객체 얻기
		CallableStatement cstmt=null;//Procedure호출, Oracle bind변수를 사용.
		
		DbConnection db=DbConnection.getInstance();
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
		con=db.getConn();
		//3. 쿼리문 생성객체 얻기
		cstmt=con.prepareCall("{call insert_cp_emp1(?, ?, ?, ?, ?, ?)}");
		//4. 바인드변수에 값설정
			//in parameter
			cstmt.setInt(1, ceVO.getEmpno());
			cstmt.setString(2, ceVO.getEname());
			cstmt.setInt(3, ceVO.getDeptno());
			cstmt.setInt(4, ceVO.getMgr());
			//out parameter : Oracle bind변수 (var 변수명 데이터형(크기))
			cstmt.registerOutParameter(5, Types.NUMERIC);//프로시저에서 처리된 결과를 저장
			cstmt.registerOutParameter(6, Types.VARCHAR);
		//5. 실행
			cstmt.execute();
		//6. out parameter에 설정된 값 받기
			int cnt=cstmt.getInt(5);
			String msg=cstmt.getString(6);
			System.out.println(cnt + "건 추가");
			System.out.println(msg);
		}finally {
			//7. 연결 끊기
			db.dbClose(null, cstmt, con);
		}//end finally
		
	}//insertCpEmp1
	
	public static void main(String[] args) {
		UseCallableStatement ucs=new UseCallableStatement();
		try {
			ucs.insertCpEmp1();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
