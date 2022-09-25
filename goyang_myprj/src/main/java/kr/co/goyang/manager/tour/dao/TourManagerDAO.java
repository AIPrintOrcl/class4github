package kr.co.goyang.manager.tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.goyang.common.dao.DbConnection;
import kr.co.goyang.manager.tour.vo.TourManagerVO;
import kr.co.goyang.user.reservation.vo.TourReservaVO;

/**
 * PreparedStatement를 사용한 CRUD작업.
 * @author user
 */
public class TourManagerDAO {

	public TourManagerDAO() {
		
	}//TourReservaDAO
	
	public List<TourManagerVO> selectSearchTour(TourManagerVO tmVO) throws SQLException {//투어 관리 리스트
		List<TourManagerVO> list = new ArrayList<TourManagerVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbConn=DbConnection.getInstance();
		
		try {
			//1.드라이버 로딩
			//2.커넥션 연결
			con=dbConn.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectTour=new StringBuilder();
			
			selectTour
			.append("	SELECT TOUR_NUM, TOUR_NAME, ADULT_FEE, OTHER_FEE, RUN_FLAG	")
			.append(" 	FROM TOUR	");
			if(tmVO.getTextSearch()!=null) {
				selectTour.append(" 	WHERE TOUR_NAME= ?	");
			}//end if
			
			pstmt=con.prepareStatement(selectTour.toString());
			//4.바운드 값 설정
			if(tmVO.getTextSearch()!=null) {
				pstmt.setString(1, tmVO.getTextSearch());
			}//end if
			//5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();//rs는 CURSOR의 제어권을 가지고 있다.
			
			tmVO=null;//레코드 하나의 값을 저장할 VO
			while (rs.next()) {//검색된 레코드의 수를 모르지만 모든 레코드를 저장하기위해
				tmVO=new TourManagerVO();
				tmVO.setTourNum(rs.getInt("TOUR_NUM"));
				tmVO.setTourName(rs.getString("TOUR_NAME"));
				tmVO.setAdultFee(rs.getInt("ADULT_FEE"));
				tmVO.setOtherFee(rs.getInt("OTHER_FEE"));
				tmVO.setRunFlag(rs.getInt("RUN_FLAG"));
				//생성된 VO를 list에 저장
				list.add(tmVO);
			}//end while
			
		}finally {
			//6. 연결 끊기
			dbConn.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectSearchTour
	
	public TourManagerVO selectTour(int tourNum) throws SQLException {//투어별 상세보기
//		List<TourManagerVO> list = new ArrayList<TourManagerVO>();
		TourManagerVO tmVO;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbConn=DbConnection.getInstance();
		
		try {
			//1.드라이버 로딩
			//2.커넥션 연결
			con=dbConn.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectTour=new StringBuilder();
			
//			selectTour
//			.append("	SELECT	T.TOUR_NUM, T.TOUR_NAME, T.EXPLAIN, T.THUM_IMG, T.MAP_IMG, T.ADULT_FEE, T.OTHER_FEE, T.RUN_FLAG,	")
//			.append("			TS.TOUR_ORDER, TS.SPOTS_NAME, TS.START_HOUR, TS.END_HOUR	")
//			.append(" 	FROM	TOUR T, TOUR_SPOTS TS	")
//			.append(" 	WHERE	T.TOUR_NUM=TS.TOUR_NUM AND	")
//			.append(" 			TOUR_NUM= ?	");
			selectTour
			.append("	SELECT	TOUR_NUM, TOUR_NAME, EXPLAIN, THUM_IMG, MAP_IMG, ADULT_FEE, OTHER_FEE, RUN_FLAG	")
			.append(" 	FROM	TOUR	")
			.append(" 	WHERE	TOUR_NUM= ?	");
			
			pstmt=con.prepareStatement(selectTour.toString());
			//4.바운드 값 설정
			pstmt.setInt(1, tourNum);
			//5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();//rs는 CURSOR의 제어권을 가지고 있다.
			
			tmVO=null;//레코드 하나의 값을 저장할 VO
			while (rs.next()) {//검색된 레코드의 수를 모르지만 모든 레코드를 저장하기위해
				tmVO=new TourManagerVO();
				tmVO.setTourNum(rs.getInt("TOUR_NUM"));
				tmVO.setTourName(rs.getString("TOUR_NAME"));
				tmVO.setExplain(rs.getString("EXPLAIN"));
				tmVO.setThumImg(rs.getString("THUM_IMG"));
				tmVO.setMapImg(rs.getString("MAP_IMG"));
				tmVO.setAdultFee(rs.getInt("ADULT_FEE"));
				tmVO.setOtherFee(rs.getInt("OTHER_FEE"));
				tmVO.setRunFlag(rs.getInt("RUN_FLAG"));
//				tmVO.setTourOrder(rs.getInt("TS.TOUR_ORDER"));
//				tmVO.setSpotName(rs.getString("TS.SPOTS_NAME"));
//				tmVO.setStartHour(rs.getString("TS.START_HOUR"));
//				tmVO.setEndHour(rs.getString("TS.END_HOUR"));
				//생성된 VO를 list에 저장
//				list.add(tmVO);
			}//end while
			
		}finally {
			//6. 연결 끊기
			dbConn.dbClose(rs, pstmt, con);
		}//end finally
		
		return tmVO;
	}//selectTour
	
	public List<TourManagerVO> selectTourSpots(int tourNum) throws SQLException {//투어별 상세보기 관광지
		List<TourManagerVO> list = new ArrayList<TourManagerVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbConn=DbConnection.getInstance();
		
		try {
			//1.드라이버 로딩
			//2.커넥션 연결
			con=dbConn.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectTour=new StringBuilder();
			
			selectTour
			.append("	SELECT	T.TOUR_NUM,	")
			.append("			TS.TOUR_ORDER, TS.SPOTS_NAME, TS.START_HOUR, TS.END_HOUR	")
			.append(" 	FROM	TOUR T, TOUR_SPOTS TS	")
			.append(" 	WHERE	T.TOUR_NUM=TS.TOUR_NUM AND	")
			.append(" 			T.TOUR_NUM= ?	");
			
			pstmt=con.prepareStatement(selectTour.toString());
			//4.바운드 값 설정
			pstmt.setInt(1, tourNum);
			//5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();//rs는 CURSOR의 제어권을 가지고 있다.
			
			TourManagerVO tmVO=null;//레코드 하나의 값을 저장할 VO
			while (rs.next()) {//검색된 레코드의 수를 모르지만 모든 레코드를 저장하기위해
				tmVO=new TourManagerVO();
				tmVO.setTourOrder(rs.getInt("TOUR_ORDER"));
				tmVO.setSpotName(rs.getString("SPOTS_NAME"));
				tmVO.setStartHour(rs.getString("START_HOUR"));
				tmVO.setEndHour(rs.getString("END_HOUR"));
				//생성된 VO를 list에 저장
				list.add(tmVO);
			}//end while
			
		}finally {
			//6. 연결 끊기
			dbConn.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectTour
	
	
//	/**
//	 * VO에 입력된 사원정보를 받아 CP_EMP1테이블에 추가하는 일
//	 * @param ceVO
//	 * @throws SQLException
//	 */
//	public void insertCpEmp(CpEmp1VO ceVO) throws SQLException{
//		
//		kr.co.goyang.common.dao.DbConnection dc=kr.co.goyang.common.dao.DbConnection.getInstance();
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		//1.드라이버 로딩
//		try {
//		//2.커넥션 연결
//			con=dc.getConn();
//		//3.쿼리문생성객체 얻기
//			String insertCpEmp=
//					"insert into cp_emp1(empno,ename,deptno,mgr,hiredate) values(?,?,?,?,sysdate)";
//			
//			pstmt=con.prepareStatement(insertCpEmp);//쿼리문을 넣어 쿼리문 실행객체를 얻는다.
//		//4.바인드변수에 값 설정
//			pstmt.setInt(1, ceVO.getEmpno());
//			pstmt.setString(2, ceVO.getEname());
//			pstmt.setInt(3, ceVO.getDeptno());
//			pstmt.setInt(4, ceVO.getMgr());
//		//5.쿼리문 수행 후 결과 얻기
//			pstmt.executeUpdate();
//		}finally {
//			//6.연결 끊기
//			dc.dbClose(null, pstmt, con);
//		}//end finally
//	}//insertCpEmp
//	
//	public int updateCpEmp(CpEmp1VO ceVO) throws SQLException{
//		int updateCnt=0;
//		DbConnection dc = DbConnection.getInstance();
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		
//		try {
//		//1.드라이버 로딩
//		//2.커넥션 얻기
//			con=dc.getConn();
//		//3.쿼리문 생성객체 얻기
//			StringBuilder updateCpEmp=new StringBuilder();
//			updateCpEmp
//			.append("	update	cp_emp1			 ")
//			.append("	set		ename=?,deptno=? ")
//			.append("	where	empno=?			 ");
//			
//			pstmt=con.prepareStatement(updateCpEmp.toString());
//		//4.바인드 변수에 값 설정
//			pstmt.setString(1, ceVO.getEname());
//			pstmt.setInt(2, ceVO.getDeptno());
//			pstmt.setInt(3, ceVO.getEmpno());
//		//5.쿼리문 수행 후 결과 얻기
//			updateCnt=pstmt.executeUpdate();
//			
//		}finally {
//			//6.연결 끊기
//			dc.dbClose(null, pstmt, con);
//		}//end finally
//		
//		return updateCnt;
//	}//updateCpEmp
//	
//	/**
//	 * 사원번호를 입력받 CP_EMP1테이블에서 해당 레코드를 삭제하고,
//	 * 삭제된 행의 수를 반환 하는 일.
//	 * @param empno
//	 * @return
//	 * @throws SQLException 
//	 */
//	public int delecteCpEmp(int empno) throws SQLException {
//		int deleteCnt=0;
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		
//		DbConnection db=DbConnection.getInstance();
//		try {
//			//1.드라이버로딩
//			//2.커넥션 얻기
//			 con=db.getConn();
//			//3.쿼리문 생성객체 얻기
//			 String deleteCpEmp="delete from cp_emp1 where empno=?";
//			 pstmt=con.prepareStatement(deleteCpEmp);
//			//4.바인드변수에 값 설정
//			 pstmt.setInt(1, empno);
//			//5.쿼리문 수행 후 결과 얻기
//			 deleteCnt=pstmt.executeUpdate(); // 리턴되는 값:0 - 삭제된 행 없는 경우 
//			 								//or 1 - 삭제된 행이 하나인 경우.
//		}finally {
//			//6.연결끊기
//			db.dbClose(null, pstmt, con);
//		}
//		
//		return deleteCnt;
//	}//delecteCpEmp
//	
//	public CpEmp1VO seletOneCpEmp1(int empno) throws SQLException{
//		CpEmp1VO ceVO=null;
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		DbConnection db=DbConnection.getInstance();
//		try {
//		//1.드라이버로딩
//		//2.커넥션 얻기
//			con=db.getConn();
//		//3.쿼리문 생성객체 얻기
//			StringBuilder selectCpEmp=new StringBuilder();
//			selectCpEmp
//			.append("	select	empno, ename, deptno, mgr, hiredate	")
//			.append("	from	cp_emp1								")
//			.append("	where	empno=?								");
//			
//			pstmt=con.prepareStatement(selectCpEmp.toString());
//		//4.바인드 변수 값 설정
//			pstmt.setInt(1, empno);
//		//5.쿼리문 수행 후 결과 얻기
//			rs=pstmt.executeQuery();
//			
//			if(rs.next()) {//조회된 레코드가 있음.
//				//조회컬럼 값을 저장하기위해 VO를 생성
//				ceVO=new CpEmp1VO();
//				//setter method를 호출하여 값을 설정 => 필요한 값만 넣을 수 있다.
//				ceVO.setEname(rs.getString("ename"));
//				ceVO.setDeptno(rs.getInt("deptno"));
//				ceVO.setMgr(rs.getInt("mgr"));
//				ceVO.setHiredate(rs.getDate("hiredate"));
//			}//end if
//		}finally {
//			//6.연결 끊기
//			db.dbClose(rs, pstmt, con);
//		}//end finally
//		
//		return ceVO;
//	}//seletOneCpEmp1
	
//	public static void main(String[] args) {
//		TourManagerDAO tmDAO=new TourManagerDAO();
//		TourManagerVO tmVO=new TourManagerVO();
//		try {
//			tmDAO.selectTourSpots(1);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		for(int i=0;i<seatNum.length;i++) {
//			System.out.println(seatNum[i]);
//		}
//	}//main
	
}//TourReservaDAO
