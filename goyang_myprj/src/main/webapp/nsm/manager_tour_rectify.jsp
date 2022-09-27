<%@page import="java.util.List"%>
<%@page import="kr.co.goyang.manager.vo.TourManagerVO"%>
<%@page import="kr.co.goyang.manager.dao.TourManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="author" content="Untree.co">
  <link rel="shortcut icon" href="favicon.png">

  <meta name="description" content="" />
  <meta name="keywords" content="bootstrap, bootstrap4" />

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&family=Source+Serif+Pro:wght@400;700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/owl.carousel.min.css">
  <link rel="stylesheet" href="../css/owl.theme.default.min.css">
  <link rel="stylesheet" href="../css/jquery.fancybox.min.css">
  <link rel="stylesheet" href="../fonts/icomoon/style.css">
  <link rel="stylesheet" href="../fonts/flaticon/font/flaticon.css">
  <link rel="stylesheet" href="../css/daterangepicker.css">
  <link rel="stylesheet" href="../css/aos.css">
  <link rel="stylesheet" href="../css/style.css">

  <title>관리자화면-투어관리</title>
  
<style type="text/css">
#container{width:1000px;margin: 0px auto;}
.floatRight{float:right}
.floatLeft{float:left}
.centerText{text-align: center}
.rightText{text-align: right}
.centerAlign{margin: 0px auto;}
.tableSize{width:1000px;margin: 0px auto;}
.textSize{width: 1008px; height: 40px;}
.imgTextSize{width: 550px; height: 40px;}
.imgSize{width: 300px; height: 200px; border:1px solid #333;}
.margin20{margin: 20px;}
.marginLR10{margin : 10px 0px 10px 20px}

</style>
<!-- google jquery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script type="text/javascript">

tableCnt=0;
$(function(){
	tableCnt=$("#tableAction tr").length;//생성된 테이블의 행의 수
	
	$('#btn-add-row').click(function() {
		if($('#tableAction tr').length>12){
			return;
		}
		var tableTemp ="<tr><td><input type='text' name='tourOrderIn' value='" + tableCnt++ + "' size=1 readonly='readonly'/></td>"
		tableTemp +="<td><input type='text' name='spotNameIn' value=''/></td>"
		tableTemp +="<td><input type='text' name='startHourIn' value=''/></td>"
		tableTemp +="<td><input type='text' name='endHourIn' value=''/></td></tr>"
		
		$("#tableAction").last().append(tableTemp);
	  });
	
	$("#btn-delete-row").click(function(){
		if($('#tableAction tr').length<4){
			return;
		}
		$("#tableAction tr").last().remove();
		tableCnt--;
		if(tableCnt == -1){tableCnt=0;}
	});//click
})

function fileInput(fileInput, nameOutput, imgOutput){
	var file = document.getElementById(fileInput);
	var fReader = new FileReader();
	fReader.readAsDataURL(file.files[0]);
	fReader.onloadend = function(event) {
	document.getElementById(imgOutput).src=event.target.result;
		
		/* 파일명 */
		var fileValue = $('#'+fileInput).val().split("\\");
		var fileName = fileValue[fileValue.length-1]; // 파일명
		$('#'+nameOutput).val(fileName);
	}//end onloadend
}//fileInput
</script>
</head>

<body>
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
  <!-- header -->

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
				<a href="manager_dashboard.html" class="logo m-0">고양<span
					class="text-primary">.</span></a>

				<ul
					class="js-clone-nav d-none d-lg-inline-block text-left site-menu float-center">
					<li class="active"><a href="manager_dashboard.html">dash board</a></li>
					<li><a href="manager_member_popup.html">회원관리</a></li>
					<li><a href="manager_tour_manager.jsp">투어관리</a></li>
					<li><a href="manager_reservation.html">예약관리</a></li>
					<li><a href="manager_spot_list.html">관광지 관리</a></li>
					<li><a href="manager_review.html">후기관리</a></li>
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


	<div class="hero hero-inner">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6 mx-auto text-center">
					<div class="intro-wrap">
						<h1 class="mb-0">관리자 화면</h1>
						<!-- <p class="text-white">로그인을 해주세요.</p> -->
					</div>
				</div>
			</div>
		</div>
	</div>



  <!-- container -->
  <form id="recitfyFrm" action="manager_tour_rectifyAction.jsp" method="post">
  <%
  TourManagerDAO tmDAO = new TourManagerDAO();
  TourManagerVO tourVO=tmDAO.selectTour(tourNum);
  %>
  <div class="container">
  <div style="font-size: 20px; margin: 50px 0px 10px 0px">투어 정보 수정</div>
  <hr>
  <div class="margin20"><!-- 코스명, 요약 설명, 사진, 맵 -->
   <div class="margin20">
   <input type="hidden" name="tourNum" value="<%= tourNum %>"/>
    <span><strong>코스명</strong></span><br/>
    <input type="text" class="textSize" name="tourName" value="<%= tourVO.getTourName() %>" placeholder="코스명을 입력하세요." maxlength=20/><br/>
    </div>
    <div class="margin20">
    <span><strong>요약 설명</strong></span><br/>
    <input type="text" class="textSize" name="explain" value="<%= tourVO.getExplain() %>" placeholder="내용을 입력하세요." maxlength=30/>
   </div>
   
   <div class="margin20"><!-- 대표 사진 -->
   <span><strong>대표 사진</strong></span><br/>
   <div style="display: flex; justify-content: left; margin:20px 20px 0px 5px;">
    <div>
     <input type='file' id="thumImgFile" onchange="fileInput('thumImgFile', 'thumImg', 'thumImgOutput')" accept="image/png, image/jpeg" value="<%= tourVO.getThumImg() %>">
     <input type="hidden" id="thumImg" name="thumImg" value="<%= tourVO.getThumImg() %>"/>
    </div>
     <div class="imgSize"><img class="imgSize" id="thumImgOutput" src="http://localhost/goyang_myprj/images/<%= tourVO.getThumImg() %>"/></div>
    </div>
   </div>
   
   <div class="margin20"><!-- MAP -->
    <span><strong>MAP</strong></span><br/>
   <div style="display: flex; justify-content: left; margin:20px 20px 0px 5px;">
    <div>
     <input type='file' id="mapImgFile" onchange="fileInput('mapImgFile', 'mapImg', 'mapImgOutput')" accept="image/png, image/jpeg" value="<%= tourVO.getMapImg() %>">
     <input type="hidden" id="mapImg" name="mapImg" value="<%= tourVO.getMapImg() %>"/>
    </div>
     <div class="imgSize"><img class="imgSize" id="mapImgOutput" src="http://localhost/goyang_myprj/images/<%= tourVO.getMapImg() %>"/></div>
   </div>
   </div>
   
  </div>
  
  
  <div class="margin20"><!-- 코스 테이블 -->
  <span class="margin20"><strong>코스</strong></span><br/>
  <table class="member tableSize" id="tableAction">
  	<tr>
  		<th>순번</th>
  		<th>경유지명/내용</th>
  		<th>시작 시간</th>
  		<th>끝나는 시간</th>
  	</tr>
  	<%
  	List<TourManagerVO> tourSportList=tmDAO.selectTourSpots(tourNum);
  	for(int i=0;i<tourSportList.size();i++){
  	%>
  	<tr>
  		<td><input type="text" name="tourOrderIn" value="<%= tourSportList.get(i).getTourOrder() %>" size=1 readonly="readonly"/></td>
  		<td><input type="text" name="spotNameIn" value="<%= tourSportList.get(i).getSpotName() %>" /></td>
  		<td><input type="text" name="startHourIn" value="<%= tourSportList.get(i).getStartHour() %>"/></td>
  		<td><input type="text" name="endHourIn" value="<%= tourSportList.get(i).getEndHour() %>"/></td>
  	</tr>
  	<%}//end for %>
  	
  </table>
   <div style="display: flex; justify-content: space-evenly;">
    <input type="button" value="항목추가" class="mainBtn" id="btn-add-row"/>
    <input type="button" value="항목제거" class="mainBtn" id="btn-delete-row"/>
   </div>
  </div>
  
  <div class="margin20"> <!-- 탑승료, 종료하기/수정하기 버튼 -->
   <div class="margin20"><strong>탑승료</strong></div>
   <div class="margin20"><strong>성인:</strong><input type="text" name="adultFee" value="<%= tourVO.getAdultFee() %>"/></div>
   <div class="margin20"><strong>기타:</strong><input type="text" name="otherFee" value="<%= tourVO.getOtherFee() %>"/></div>
   <div style="display: flex; justify-content: end; margin-bottom: 5px; margin-top: 20px;">
    <div class="marginLR10"></div><div class="marginLR10"><input type="button" id="recitfyBtn" value="수정하기" class="mainBtn" onclick="showPopup(true,'popup')"/></div>
   </div>
  </div>
  </div>
  </form>
  
  <!-- footer -->

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
	
	<!-- 투어 수정 확인 팝업 popup -->
	<div id="popup" class="hide popup">
		<div class="content">
			<div style="width: 450px;">
				<div style="font-size: 12px; width: 450px; height: 30px; padding-left: 10px;
				display: flex; align-items: center; background-color: #f0f6f9; border: 1px solid #ddd; margin-bottom: 5px">투어 수정 확인</div>
				
				<div style="background-color: #f0f6f9;">
					<div style="font-size: 16px; display: flex; justify-content: center; 
					align-items: center; height: 70px ;background-color: #f0f6f9;">해당 투어를 수정하시겠습니까?</div>
					
					<div style="display: flex; align-items: center; justify-content: center; padding-bottom: 10px;">
						<input type="button" value="확인" class="popupBtn" onclick="windowMove('popup')">
						<input type="button" value="취소" class="popupBtn" onclick="closePopup('popup')">
					</div>
				</div>
			</div>
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
	
<script type="text/javascript">
		function showPopup(hasFilter,id) {
			const popup = document.querySelector("#"+id);
			
			if (hasFilter) {
				popup.classList.add('has-filter');
			} else {
				popup.classList.remove('has-filter');
			}
				
			popup.classList.remove('hide');
		}//showPopup
		
		function closePopup(id) {
			const popup = document.querySelector("#"+id);
			popup.classList.add('hide');
		}//closePopup
		
		function windowMove(id) {
			closePopup(id);
			$("#recitfyFrm").submit();
		}//windowMove()
		
	</script>

</body>

</html>
