<!-- 원래 로그아웃창은 서블릿에서 만드는거임. logout.jsp 만든이유는 jsp에도 만들수있다는걸 보여준거임 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>logout.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		alert("로그아웃되었습니다"); //원래는 여기에 쓰지않고 서블릿에서 써야함
		location.href="test2.jsp"; //같은 위치라 경로 안적음
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	
</div>
<p><br/></p>
</body>
</html>