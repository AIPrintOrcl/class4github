package day0824;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import kr.co.sist.common.dao.DbConnection;
import oracle.jdbc.OracleTypes;

/**
 * CP_EMP 테이블의 모든 레코드를 조회하여 반환하는 select_dept_cp_emp 프로시저 사용
 * @author user
 *
 */
public class UseCallableAllSelect2 {

	public void selectAllCpEmp1() throws SQLException{
		
		int deptno=Integer.parseInt(JOptionPane.showInputDialog("부서번호 입력해주세요."));
		
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
			con=db.getConn();
		//3. 쿼리문 생성객체 얻기
			cstmt=con.prepareCall("{call select_dept_cp_emp(?,?)}");
		//4. 바인드 변수에 값 설정
			//in parameter
			//out parameter
			cstmt.setInt(1, deptno);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		//5. 프로시저 실행
			cstmt.execute();
		//6. out parameter에 설정된 값 받기
			rs=(ResultSet)cstmt.getObject(2);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd EEEE");
			System.out.println(deptno + "번 부서 조회 결과");
			while(rs.next()) {
				System.out.printf("%d\t%s\t%d\t%s\n", 
						rs.getInt("empno"), rs.getString("ename"),
						rs.getInt("mgr"),
						sdf.format(rs.getDate("hiredate") == null?new Date():rs.getDate("hiredate")));
			}//end while
		}finally {
			//7. 연결 끊기
			db.dbClose(rs, cstmt, con);
		}//end finally
		
	}//selectAllCpEmp1
	
	public static void main(String[] args) {
		UseCallableAllSelect2 ucas=new UseCallableAllSelect2();
		
		try {
			ucas.selectAllCpEmp1();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
