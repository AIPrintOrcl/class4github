<%@page import="kr.co.goyang.user.reservation.vo.TourReservaVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.goyang.user.reservation.dao.TourReservaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /*
* Template Name: Tour
* Template Author: Untree.co
* Tempalte URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Untree.co">
<link rel="shortcut icon" href="favicon.png">

<meta name="description" content="" />
<meta name="keywords" content="bootstrap, bootstrap4" />

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&family=Source+Serif+Pro:wght@400;700&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">
<link rel="stylesheet" href="../css/jquery.fancybox.min.css">
<link rel="stylesheet" href="../fonts/icomoon/style.css">
<link rel="stylesheet" href="../fonts/flaticon/font/flaticon.css">
<link rel="stylesheet" href="../css/daterangepicker.css">
<link rel="stylesheet" href="../css/aos.css">
<link rel="stylesheet" href="../css/style.css">
<style type="text/css">
	tr{
		 border-bottom: 1px solid grey;
	}
	
	td{
		margin-top: 10px;
	}
	.aLineNone{text-decoration-line: none;cursor:pointer;}
</style>

<title>고양 시티투어</title>
</head>

<body>
<%
String id=null;
if(session.getAttribute("id") !=null){//세션에서 아이디 가져오기.
	id = (String) session.getAttribute("id");
}//end if
%>


	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<nav class="site-nav">
		<div class="container">
			<div class="site-navigation">
				<a href="index.html" class="logo m-0">고양<span
					class="text-primary">.</span></a>

				<ul
					class="js-clone-nav d-none d-lg-inline-block text-left site-menu float-center">
					<li class="active"><a href="index.html">home</a></li>
					<li><a href="user_reservation_course.jsp">투어예약</a></li>
					<li><a href="user_introduceTour.html">관광지소개</a></li>
					<li><a href="user_review.html">관광지후기</a></li>
					<li><a href="user_mypage_inner.html">마이페이지</a></li>
				</ul>

				<ul
					class="js-clone-nav d-none d-lg-inline-block text-left site-menu float-right">
					<li></li>
					<li style="font-size: 5px; font-weight: bold;"><a
						href="contact.html">로그아웃</a></li>
				</ul>
				
				<a href="#"
					class="burger ml-auto float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
					data-toggle="collapse" data-target="#main-navbar"> <span></span>
				</a>

			</div>
		</div>
	</nav>


	<div class="hero">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6 mx-auto text-center">
					<div class="intro-wrap">
						<h1 class="mb-0">코스 선택</h1>
						<!-- <p class="text-white">로그인을 해주세요.</p> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!--  여기사이에 구상하시면 됩니다!!! -->
	
	<div class="container">
		
			<div style="font-size: 23px;margin: 50px 0px 50px 0px;display: flex;justify-content: space-between; border-bottom: 1px solid grey;padding-bottom: 10px;">
				<div> 정기코스</div>
			</div>
			
			<div style="margin-bottom: 100px;">
				<table style="width: 100%; margin: 20px 0 10px 0; border-bottom: 1px solid grey; padding: 10px 0;">
					<%
					TourReservaDAO trDAO = new TourReservaDAO();
					List<TourReservaVO> cosList=trDAO.selectTourList();
					for(int i=0; i<cosList.size();i++) {
					%>
					<tr>
					
						<td style="max-width: 70px; height: 140px;">
						
							<div style="display: flex; justify-content: center; align-items: center;">
								<img src="http://localhost/goyang/images/<%= cosList.get(i).getThumImg() %>" style="width: 150px; height: 100px;">
							</div>
						</td>
						<td>
							<div style="display: flex; flex-direction: column;">
								<div style="font-size: 17px; padding-bottom: 2px;"><a href="http://localhost/goyang/User/user_introduceDay.html?tourNum=<%= cosList.get(i).getTourNum() %>" class="aLineNone"><%= cosList.get(i).getTourName() %></a></div>
								<div style="font-size: 13px;"><%= cosList.get(i).getExplain() %></div>
							</div>
						</td>
						<td>
							<div style="padding-bottom: 10px;">10:00 ~ 16:00</div>
							<div>성인 : <%= cosList.get(i).getAdultFee() %> <br>기타 : <%= cosList.get(i).getOtherFee() %></div>
						</td>
						<td><div style="display: flex; justify-content: end;"><input type="submit" value="예약하기" class="mainBtn" onclick="location.href='user_reservation_date.jsp?tourNum=<%= cosList.get(i).getTourNum() %>'"></div>
						</td>
					</tr>
					
					<%}//end for %>
				</table>
			</div>
		
	</div>


	<div class="site-footer">
		<div class="inner first">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-4" >
						<div class="widget">
							<p style="font-size: 20px;font-weight: bold; color: red;font-style:inherit;" >매주 월요일은 정기 휴무입니다.</p>
								<p style="font-size: 60px;font-weight: bold;">Goyang Tour</p>
						</div>
					</div>
					<div class="col-md-6 col-lg-4 " style="margin: 0 0 0 auto">
						<div class="widget">
							<h3 class="heading">고양시티투어</h3>
							<ul class="list-unstyled quick-info links">
								<li class="email"><a href="#">goyang@com</a></li>
								<li class="phone"><a href="#">010-1234-5678</a></li>
								<li class="address"><a href="#">경기도 고양시 일산동구 장항동</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="inner dark">
			<div class="container">
				<div class="row text-center">
					<div class="col-md-8 mb-3 mb-md-0 mx-auto">
						<p>
							Copyright &copy;
							<script>document.write(new Date().getFullYear());</script>
							. All Rights Reserved. &mdash; Designed with love by <a
								href="https://untree.co" class="link-highlight">Untree.co</a>
							<!-- License information: https://untree.co/license/ -->
						</p>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div id="overlayer"></div>
	<div class="loader">
		<div class="spinner-border" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>

	<script src="../js/jquery-3.4.1.min.js"></script>
	<script src="../js/popper.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/owl.carousel.min.js"></script>
	<script src="../js/jquery.animateNumber.min.js"></script>
	<script src="../js/jquery.waypoints.min.js"></script>
	<script src="../js/jquery.fancybox.min.js"></script>
	<script src="../js/aos.js"></script>
	<script src="../js/moment.min.js"></script>
	<script src="../js/daterangepicker.js"></script>

	<script src="../js/typed.js"></script>

	<script src="../js/custom.js"></script>

</body>

</html>
