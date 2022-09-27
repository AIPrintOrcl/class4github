<%@page import="kr.co.goyang.manager.dao.TourManagerDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="kr.co.goyang.user.vo.TourReservaVO"%>
<%@page import="kr.co.goyang.user.dao.TourReservaDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<script type="text/javascript">
<%
String id=null;
if(session.getAttribute("id") !=null){//세션에서 아이디 가져오기.
	id = (String) session.getAttribute("id");
}//end if
int tourNum=0;
if(request.getParameter("tourNum") != null){
	tourNum = Integer.parseInt(request.getParameter("tourNum"));
}//end if
%>

<%
TourManagerDAO tmDAO=new TourManagerDAO();
int upResult=tmDAO.updateTourDown(tourNum); //투어 정보 수정
if(upResult>=1){
%>
	alert("해당 투어가 종료되었습니다.");
	location.href='manager_tour_detail.jsp?tourNum=<%= tourNum %>';
<%}else {%>
	alert("해당 투어가 종료되지 못했습니다.");
	history.back();
	<%-- location.href='manager_tour_rectify.jsp?tourNum=<%= tmVO.getTourNum() %>'; --%> 
<%}%>
</script>
<body>

</body>
</html>