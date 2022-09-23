package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.common.dao.DbConnection;

/**
 * ResultSet 에서 별도의 Stream을 연결하여 데이터를 얻어내야하는 CLOB 데이터 형의 사용.
 * 
 * 사용법)
 * 1. ResultSet에서 clob를 얻는다.
 * 	java.sql.Clob clob=rs.getClob("컬럼명")
 * 2. Clob에서 16bit Stream을 얻는다.
 * 	Reader reader=clob.getCharacterStream();
 * 3. 줄 단위로 읽어들이는 기능을 가진 스트림과 연결
 *  BuffereReader br=new BufferedReader(reader);
 * 4. 모든 문자열데이터를 줄 단위로 읽어 들인다.
 *  String data="";
 *  
 *  while((data=br.readLine()) != null){
 *  	data
 *  }
 * @author user
 */
public class TestClob {
	
	public TestClob() throws SQLException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		try {
			//1. 드라이버 로딩
			//2. 커넥션 얻기
			con=db.getConn();
			//3. 쿼리문생성객체 얻기
			StringBuilder selectClob = new StringBuilder();
			selectClob
			.append("	select	num, subject, content	")
			.append("	from	test_clob				");
			pstmt=con.prepareStatement(selectClob.toString());
			//4. 바인드변수에 값 설정
			//5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			BufferedReader br=null;
			String readData="";
			while(rs.next()) {
				System.out.println("-----------------------------------------");
				System.out.printf("번호 : %d\t제목 : %s\n", rs.getInt("num"), rs.getString("subject"));
//				System.out.println(rs.getString("content"));//CLOB는 Window에서는 getString으로
//				얻어 올 수 있으나 Linux로 배포되면 에러가 나지 않고 값을 얻을 수 없다. => 별도의 Stream 연결하여 처리
				br=new BufferedReader(rs.getClob("content").getCharacterStream());
				try {
				while ((readData=br.readLine()) != null) {//EOF
					System.out.println(readData);
				}//end while
				}catch(IOException ie) {
					ie.printStackTrace();
				}finally {
					try {
						if(br != null) {br.close();}//end if
					}catch(IOException ie) {
						ie.printStackTrace();
					}//end catch
				}//end finally
				System.out.println("-----------------------------------------");
			}//end while
			
		}finally {
			//6. 연결 끊기
			db.dbClose(null, pstmt, con);
		}//end finally
	}//TestClob

	public static void main(String[] args) {
		try {
			new TestClob();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
