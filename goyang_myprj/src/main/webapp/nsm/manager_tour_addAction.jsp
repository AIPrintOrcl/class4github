<%@page import="kr.co.goyang.manager.tour.dao.TourManagerDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="kr.co.goyang.user.reservation.vo.TourReservaVO"%>
<%@page import="kr.co.goyang.user.reservation.dao.TourReservaDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="tmVO" class="kr.co.goyang.manager.tour.vo.TourManagerVO" scope="page" />
<jsp:setProperty name="tmVO" property="tourNum"/>
<jsp:setProperty name="tmVO" property="tourName"/>
<jsp:setProperty name="tmVO" property="explain"/>
<jsp:setProperty name="tmVO" property="thumImg"/>
<jsp:setProperty name="tmVO" property="mapImg"/>
<jsp:setProperty name="tmVO" property="tourOrderIn"/>
<jsp:setProperty name="tmVO" property="spotNameIn"/>
<jsp:setProperty name="tmVO" property="startHourIn"/>
<jsp:setProperty name="tmVO" property="endHourIn"/>
<jsp:setProperty name="tmVO" property="adultFee"/>
<jsp:setProperty name="tmVO" property="otherFee"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<script type="text/javascript">
<%
TourManagerDAO tmDAO=new TourManagerDAO();
tmVO.setTourNum(tmDAO.insertTour(tmVO)); //투어 정보 수정 - 반환값 tourNum. 바로 투어 관광지 추가 할 때 사용 
tmDAO.insertTourSport(tmVO); // 투어 관광지 추가
%>
	alert("투어 추가가 완료되었습니다.");
	location.href='manager_tour_manager.jsp';
</script>
<body>

</body>
</html>