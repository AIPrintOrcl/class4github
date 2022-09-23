package day0817;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * dDB연결 얻기
 * @author user
 *
 */
public class TestDbConnection {

	public TestDbConnection() {
		//1.드라이버 로딩(DB Vendor사에서 제작 배포한 ojdbc8.jar 안에 있는 OracleDriver.class를 사용)
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // ojdbc8\oracle\jdbc\OracleDriver.class
			System.out.println("드라이버 로딩 성공 : JVM에 JDBC부분에 드라이버를 연결");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버가 없습니다.");
			cnfe.printStackTrace();
		}//end catch
		//2.로딩된 드라이버를 사용하여 DB연결(Connection) 얻기
		try {
//			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id="scott";
			String pass="tiger";
			
			Connection con= DriverManager.getConnection(url, id, pass);
			System.out.println("DB연결 얻기 성공: "+con);
		} catch (SQLException se) {
			System.err.println("DB연결을 얻는 중 문제 발생"); 
			se.printStackTrace();
		}//try catch
	}//TestDbConnection
	
	public static void main(String[] args) {
		new TestDbConnection();
		
	}//main

}//class
