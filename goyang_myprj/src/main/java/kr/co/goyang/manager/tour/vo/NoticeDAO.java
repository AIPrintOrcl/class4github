package kr.co.ecweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.ecweb.vo.NoticeVO;

public class NoticeDAO {

	private DbConnection dbConn;

	public NoticeDAO() {

		dbConn = DbConnection.getInstance();// DB연결 객체화

	}// NoticeDAO

	public int selectNextNum() throws SQLException { // 게시판 번호 이용하여 마지막 게시물 찾기

		int selectNum=1;//첫번째면 1번
		String sql = "SELECT noticeNum FROM NOTICE ORDER BY noticeNum DESC";
		
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				selectNum=rs.getInt(1) + 1; // 게시판 마지막 번호에 다음 번호
			}//end if
		} finally {
			dbConn.dbClose(rs, conn, pstmt);;
		}//end finally
		return selectNum;
	}// selectNextNum

	public int insertWrite(String noticeTitle, String userID, String noticeContent) throws SQLException {
		int rowCnt = 0;
		

		Connection conn=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		String sql = "INSERT INTO NOTICE(noticeNum, noticeTitle, userID, noticeDATE, noticeContent, noticeAvailable) VALUES(?, ?, ?, sysdate, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectNextNum());
			pstmt.setString(2, noticeTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, noticeContent);
			pstmt.setInt(5, 1); // Available 삭제 여부 1:삭제X 0:삭제O
			rowCnt = pstmt.executeUpdate();
		} finally {
			dbConn.dbClose(null, conn, pstmt);
		} // end finally
		return rowCnt; // 게시물 DB에서 얻기 성공
	}// insertWrite

	public List<NoticeVO> selectList(int pageNumber) throws SQLException {// 게시글을 화면에 출력하기 위해 저장하는 리스트
		

		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		String sql = "SELECT * FROM NOTICE WHERE noticeNum < ? AND noticeAvailable = 1 AND ROWNUM <=10 ORDER BY noticeNum DESC";
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectNextNum() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();

			NoticeVO noticeVO;
			while (rs.next()) {
				noticeVO = new NoticeVO();
				noticeVO.setNoticeNum(rs.getInt(1));
				noticeVO.setNoticeTitle(rs.getString(2));
				noticeVO.setUserID(rs.getString(3));
				noticeVO.setNoticeDate(rs.getString(4));
				noticeVO.setNoticeContent(rs.getString(5));
				noticeVO.setNoticeAvailable(rs.getInt(6));
				noticeList.add(noticeVO);
			} // end while
		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		} // end finally
		return noticeList;
	}// selectList
	
public List<NoticeVO> selectList() throws SQLException {// 게시글을 화면에 출력하기 위해 저장하는 리스트
		

		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		String sql = "SELECT * FROM NOTICE WHERE noticeNum > ? AND noticeAvailable = 1 AND ROWNUM <=10 ORDER BY noticeNum DESC";
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		try {
			pstmt = conn.prepareStatement(sql);
			if(selectNextNum() < 4) {
				pstmt.setInt(1, selectNextNum() - 1);
			}else {
				pstmt.setInt(1, selectNextNum() - 4);
			}//end else
			rs = pstmt.executeQuery();

			NoticeVO noticeVO;
			while (rs.next()) {
				noticeVO = new NoticeVO();
				noticeVO.setNoticeNum(rs.getInt(1));
				noticeVO.setNoticeTitle(rs.getString(2));
				noticeVO.setUserID(rs.getString(3));
				noticeVO.setNoticeDate(rs.getString(4));
				noticeVO.setNoticeContent(rs.getString(5));
				noticeVO.setNoticeAvailable(rs.getInt(6));
				noticeList.add(noticeVO);
			} // end while
		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		} // end finally
		return noticeList;
	}// selectList

	public boolean selectNextPage(int pageNumber) throws SQLException {// 페이지 처리를 하여 다음 페이지의 여부 확인
		boolean flag=false;
		
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		String sql = "SELECT * FROM NOTICE WHERE noticeNum < ? AND noticeAvailable = 1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectNextNum() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag=true; // 다음 페이지 있음
			} // end if
		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		}//end finally
		return flag;
		
	}// selectNextPage

	public NoticeVO selectNotice(int noticeNum) throws SQLException {
		NoticeVO noticeVO = null;

		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			System.out.println("DB문제?");
			e.printStackTrace();
		} // catch
		
		String sql = "SELECT * FROM NOTICE WHERE noticeNum = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {// DB에 내용이 있다면
				noticeVO = new NoticeVO();
				noticeVO.setNoticeNum(rs.getInt(1));
				noticeVO.setNoticeTitle(rs.getString(2));
				noticeVO.setUserID(rs.getString(3));
				noticeVO.setNoticeDate(rs.getString(4));
				noticeVO.setNoticeContent(rs.getString(5));
				noticeVO.setNoticeAvailable(rs.getInt(6));
			} // end while
		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		} // end finally

		return noticeVO; // 어짜피 if 안타면 null입니다.
	}// selectNotice

	public int updateNotice(int noticeNum, String noticeTitle, String noticeContent) throws SQLException {// 글 내용 수정을
																											// DB에
		int updateCnt = 0;


		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			System.out.println("DB문제?");
			e.printStackTrace();
		} // catch
		
		String sql = "UPDATE NOTICE SET noticeTitle = ?, noticeContent = ? WHERE noticeNum = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			pstmt.setInt(3, noticeNum);

			updateCnt = pstmt.executeUpdate();

		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		}

		return updateCnt;// 업데이트 실패
	}// updateNotice

	public int deleteNotice(int noticeNum) throws SQLException {// 글 내용 삭제를 DB에
		int deleteCnt = 0;


		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;

		try {
			conn = dbConn.getConn();// DB 연결
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		String sql = "DELETE FROM NOTICE WHERE noticeNum = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);

			deleteCnt = pstmt.executeUpdate();

		} finally {
			dbConn.dbClose(rs, conn, pstmt);
		} // end finally
			// 리턴은 finally 아래로
		return deleteCnt; // 업데이트 성공
	}// deleteNotice

}// class
