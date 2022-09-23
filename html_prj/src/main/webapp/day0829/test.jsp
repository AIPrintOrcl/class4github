<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Back-end 페이지<br>
<img src="../day0825/images/img1.png" title="열심히 합시다."/><br/>
<strong>${param.name}</strong>님 안녕하세요?<br/>
읽기전용은 "${param.read}" 이렇게 값이 back-end로 넘어오지만 <br/>
비활성화는 "${param.disable}" 이렇게 값이 back-end로 넘어오지 않습니다.<br/>


</body>
</html>