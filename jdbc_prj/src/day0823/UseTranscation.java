package day0823;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.common.dao.DbConnection;

/**
 * 여러개의 insert문이 하나의 Transaction을 구성할 때
 * 
 * @author user
 */
public class UseTranscation {

	private Connection con;

	/**
	 * insert 쿼리문을 실행하는 일
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int transaction(TransactionVO tVO) throws SQLException {
		int rowCnt = 0;

		DbConnection db = DbConnection.getInstance();

		PreparedStatement pstmt = null;

		// 1.드라이버 로딩
		// 2.커넥션 얻기
		con = db.getConn();
		con.setAutoCommit(false);//오토커밋 해제
//		System.out.println(con.getAutoCommit()); //오토커밋 여부 확인
		// 3.쿼리문 생성객체 얻기
		String insert = "insert into transaction_test1(name,age) values(?, ?)";
		pstmt = con.prepareStatement(insert);
		// 4.바인드변수 값할당
		pstmt.setString(1, tVO.getName());
		pstmt.setInt(2, tVO.getAge());
		// 5.쿼리문 수행 후 결과 얻기
		int cnt1 = pstmt.executeUpdate();

		pstmt.close(); // 메모리를 끊어준다.
		// 3.쿼리문 생성객체 얻기
		String insert2 = "insert into transaction_test2(name,age) values(?, ?)";
		pstmt = con.prepareStatement(insert2);
		// 4.바인드변수 값할당
		pstmt.setString(1, tVO.getName());
		pstmt.setInt(2, tVO.getAge());
		// 5.쿼리문 수행 후 결과 얻기
		int cnt2 = pstmt.executeUpdate();

		rowCnt = cnt1 + cnt2; // 쿼리문을 수행한 행수를 변수에 할당한다.

		return rowCnt;
	}// transaction

	/**
	 * transaction을 처리하는 method<br>
	 * commit과 rollback을 결정한다. Connection 끊는다.
	 * 
	 * @throws SQLException
	 */
	public void useTransation() {

		TransactionVO tVO = new TransactionVO("차승주주주주주", 26);

		try {
			int cnt = transaction(tVO);// insert 두개이 하나의 transaction으로 구성.
			if (cnt == 2) {// insert 2개가 모두 성공 시
				System.out.println("트랜잭션 완료");
				con.commit();
			} // end if
		} catch (SQLException e) {
			try {
				System.out.println("트랜잭션 실패");
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // end catch
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		} // end finally

	}// useTransation

	public static void main(String[] args) {
		UseTranscation ut = new UseTranscation();
		ut.useTransation();
	}// main

}// class
