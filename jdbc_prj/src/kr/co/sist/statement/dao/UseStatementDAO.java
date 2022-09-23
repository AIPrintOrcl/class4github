package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.vo.CpEmp1InsertVO;
import kr.co.sist.vo.CpEmp1OneVO;
import kr.co.sist.vo.CpEmp1SelectVO;
import kr.co.sist.vo.CpEmp1UpdateVO;

/**
 * DAO(Data Access Object)
 * DB작업에 대한 코드만 구현한 클래스.
 * 업무로직은 Service에서만 처리한다.
 * - method명은 method가 가진 쿼리문을 알 수 있도록 쿼리문을 반영하여 method명을 설정한다.
 * 
 * java.sql.Statement 특징
 * - 객체는 실행되는 쿼리문을 모른다.
 * - 쿼리문을 실행할 때마다 생성한다.
 * - 바인드변수를 사용할 수 없고, 쿼리문과 값이 하나로 합쳐진다.
 * - 쿼리문을 반복적으로 실행하면 효율이 떨어진다.
 * - SQLinjection공격에 취약하다.
 * @author user
 */
public class UseStatementDAO {
	
	/**
	 * VO에 입력된 값을 받아서 CP_EMP1테이블에 insert 하는 일
	 * @param ceiVO
	 * @throws SQLException 
	 */
	public void insertCpEmp1(CpEmp1InsertVO ceiVO) throws SQLException  {
		//1. 드라이버로딩 (Class)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		Connection con=null;
		Statement stmt=null;
		
		try {
		//2. 커넥션얻기 (DriverManager, Connection)
			String url="jdbc:oracle:thin:@localhost:1521:orcl";//uniform resource locator
			String id="scott";
			String pass="tiger";
			con=DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기 (Statement)
			stmt=con.createStatement();// stmt객체 실행하는
		//4. 쿼리문 수행 후 결과 얻기 (exeute(),exeuteUpdate(), exeuteQuery())
			String insertCpEmp1="insert into cp_emp1(empno, ename, deptno,mgr,hiredate) values("+
					ceiVO.getEmpno()+",'"+ceiVO.getEname()+"', "+ceiVO.getDeptno()+", "
					+ceiVO.getMgr()+",sysdate)";
		int rowCnt=stmt.executeUpdate(insertCpEmp1);
		System.out.println(rowCnt + "건 추가되었음.");
		}finally {
		//5. 연결 끊기 (Statement, Connection)
			if(stmt !=null) {stmt.close();}
			if(con !=null) {con.close();}
		}
	}//insertCpEmp1
	
	/**
	 * 사원번호,사원명,부서번호를 입력받아
	 * 사원번호에 해당하는 레코드의 사원명, 부서번호를 변경하는 일.
	 * @param ceuVO
	 * @return
	 * @throws SQLException
	 */
	public int updateCpEmp1 (CpEmp1UpdateVO ceuVO) throws SQLException{
		int rowCnt=0;//update 쿼리문으로 변경된 행의 수를 저장
		//1. 드라이버 로딩(Class => DB Client 객체화)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		Connection con=null;
		Statement stmt=null;
		
		try {
		//2. Connection 얻기 (DriveManager, Connection)
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		//3. 쿼리문 생성객체 얻기 (Statement => 실행할 쿼리문을 모른다.)
		stmt=con.createStatement();
		
		//4. 쿼리문을 실행 후 결과 얻기 (쿼리문이 반복실행되면 실행될 때마다 쿼리문을 계속 생성)
//		String updateQuery="update cp_emp1 set ename='"+ ceuVO.getEmpno() +"', deptno="+ceuVO.getDeptno()+
//				" where empno="+ceuVO.getEmpno();
		StringBuilder updateQuery=new StringBuilder();
		updateQuery
		.append("	update cp_emp1	")
		.append("	set ename='").append(ceuVO.getEname()).append("', deptno=").append(ceuVO.getDeptno())
		.append("	where empno=").append(ceuVO.getEmpno());
		
		//update 쿼리문을 수행하고 변경한 행의 수를 얻는다.
		rowCnt=stmt.executeUpdate(updateQuery.toString());
		
		}finally {
			
		//5. 연결 끊기 (Statement, Connection)
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}// end if
		}//end finally
		return rowCnt;
	}//updateCpEmp1
	
	/**
	 * 사원 번호를 입력받아 사원번호에 해당하는 레코드를 삭제
	 * @param empno
	 * @return
	 * @throws SQLException
	 */
	public int deleteCpEmp1(int empno) throws SQLException{
		int rowCnt=0;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		try {
		//2. 커넥션 얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			
			con=DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt=con.createStatement();
		//4. 쿼리 수행후 결과 얻기
			StringBuilder deleteCpEmp=new StringBuilder();
			deleteCpEmp
			.append("	delete from cp_emp1			")
			.append("	where	empno=").append(empno);
			
			//삭제쿼리문이 수행 된 후, 삭제된 레코드의 수를 얻는다.
			//Connection은 autocommit이 기본 설정이어서 쿼리문 하나로 트랜잭션이 완료됩니다.
			rowCnt=stmt.executeUpdate(deleteCpEmp.toString());
			
		}finally {
			//5. 연결 끊기
			if(stmt != null) {stmt.close();}
			if(con != null) {con.close();}
		}//end finally
		return rowCnt;
	}//deleteCpEmp1
	
	/**
	 * cp_emp1테이블의 모든 사원정보를 조회.
	 * @return
	 * @throws SQLException
	 */
	public List<CpEmp1SelectVO> selectAllCpEmp1() throws SQLException{
		List<CpEmp1SelectVO> list =new ArrayList<CpEmp1SelectVO>();
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//2.커넥션얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			
			con=DriverManager.getConnection(url, id, pass);
		//3.쿼리문 생성객체 얻기
			stmt=con.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			String selectCpEmp1="select empno, ename, deptno, mgr, hiredate from cp_emp1";
			
			rs=stmt.executeQuery(selectCpEmp1); //cursor의 제어권을 받는다.
			
			CpEmp1SelectVO cesVO=null;//조회된 레코드를 저장하기위한 VO
			
			while(rs.next()) {//현재 cursor가 존재하는 위치 다음에 레코드가 있는지?
				cesVO=new CpEmp1SelectVO();//레코드의 컬럼값을 저장하기위한 VO를 생성.
				//조회된 결과를 VO에 설정.
				cesVO.setEmpno(rs.getInt("empno"));
				cesVO.setEname(rs.getString("ename"));
				cesVO.setDeptno(rs.getInt("deptno"));
				cesVO.setMgr(rs.getInt("mgr"));
				cesVO.setHiredate(rs.getDate("hiredate"));
				//VO를 List에 저장
				list.add(cesVO);
			}//end while
			
//			rs=stmt.executeQuery(selectCpEmp1);
//			//rs는 DBMS에서 조회된 결과로 생성된 inline view 앞에 존재하는 cursor의 제어권을 얻는다.
//			//rs는 실행된 쿼리문으로 인한 조회된 레코드가 있는지 알 수 없다.
//			Date date=null;
//			SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
//			while(rs.next()) {
//				System.out.println(rs.getInt(1));//가독성이 좋지 않음
//				System.out.print(rs.getInt("empno")+" ");//가독성이 좋다.
//				System.out.print(rs.getString("ename")+" ");
//				System.out.print(rs.getString("hiredate")+" ");//Date는 String로 받을 수 있다.
//				date=rs.getDate("hiredate");//date는 java.sql.Date로 받을 수 있다.
//				if(date != null) {
//					System.out.println(sdf.format(date));
//				}else {
//					System.out.println();
//				}//end else
//			}//end while
		}finally {
			//5.연결 끊기
			if(rs != null) {rs.close();}//end if
			if(stmt != null) {stmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		return list;
	}//selectAllCpEmp1
	
	/**
	 * 사원번호를 입력받아 사원번호에 해당하는 사원의 사원명, 사원번호, 매니저번호, 입사일을 조회
	 * @param empno
	 * @return
	 * @throws SQLException
	 */
	public CpEmp1OneVO selectOneCpEmp1(int empno) throws SQLException{
		CpEmp1OneVO ceoVO=null;
		
		//1. 드라이브로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end chatch
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con=DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt=con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			StringBuilder selectCpEmp=new StringBuilder();
			selectCpEmp
			.append("	select	ename,deptno,mgr,hiredate,to_char(hiredate, 'yyyy-mm-dd') str_hiredate	")
			.append("	from cp_emp1	")
			.append("	where empno=").append(empno);
			
			rs=stmt.executeQuery(selectCpEmp.toString());
			
			//조회결과가 있다면 (조회결과는 0 or 1건)
			if(rs.next()) {//조회된 레코드가 존재한다면
				ceoVO=new CpEmp1OneVO();
				//레코드 포인터 위치하는 레코드의 컬럼 값을 얻어와서 생성된 VO에 설정한다.
				ceoVO.setEname(rs.getString("ename"));
				ceoVO.setDeptno(rs.getInt("deptno"));
				ceoVO.setMgr(rs.getInt("mgr"));
				ceoVO.setHiredate(rs.getDate("hiredate")); //to_char함수를 사용하지 않은 날짜
				ceoVO.setStrHiredate(rs.getString("str_hiredate"));//to_char함수 사용.
			}//end if
		}finally {
			//5. 연결 끊기
			if(rs != null) { rs.close();}//end if
			if(stmt != null) { stmt.close();}//end if
			if(con != null) { con.close();}//end if
		}
		
		
		return ceoVO;
	}//selectOneCpEmp1
	
	
}//class
