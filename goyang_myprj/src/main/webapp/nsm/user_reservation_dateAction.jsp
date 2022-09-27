<%@page import="java.sql.Date"%>
<%@page import="kr.co.goyang.user.vo.TourReservaVO"%>
<%@page import="kr.co.goyang.user.dao.TourReservaDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="trVO" class="kr.co.goyang.user.vo.TourReservaVO" scope="page" />
<jsp:setProperty name="trVO" property="reserDate"/>
<jsp:setProperty name="trVO" property="tourNum"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

<%

TourReservaDAO trDAO=new TourReservaDAO();
trDAO.selectSeatNum(trVO);
%>
예약한 좌석 번호 : 
<%
int[] seatNum=trDAO.selectSeatNum(trVO);

for(int i=0;i<seatNum.length;i++) {
%>
<%= seatNum[i] %>
<%}//end for %><br/>
예약 가능한 인원 : <%= (28-seatNum.length) %>
</body>
</html>