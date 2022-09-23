package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Work0818DAO {
	
	public int insertName(String name) throws SQLException {
		int rowCnt=0;
		//1. 드라이버로딩 (Class)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		Statement stmt=null;
		Connection con=null;
		try {
			//2. 커넥션얻기 (DriverManager, Connection)
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기 (Statement)
		stmt=con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기 (exeute(),exeuteUpdate(), exeuteQuery())
		StringBuilder insertQuery=new StringBuilder();
		insertQuery.append("insert into WORK_JDBC(name) values('")
			.append(name).append("')"); 
		
		rowCnt=stmt.executeUpdate(insertQuery.toString());
		}finally {
			//5. 연결 끊기 (Statement, Connection)
			if(stmt!=null) {stmt.close();}
			if(con!=null) {stmt.close();}
		}//end finally
		return rowCnt;
		
	}//insertName
}//class
