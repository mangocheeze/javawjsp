<!-- 폼태그를 jsp에 넘김(원랜 서블릿에 넘겨야함) -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>값 전송하기(get방식)</h2>
	<!-- <form name="myform" method="get" action="test7Ok.jsp">  <%-- action에 쓴건 서버로가서 받는 서버로가서 받는화일명을 적어줌파일명을 적어줌,폼태그를 jsp를 넘긴다(해커들에게 다보여지는방법이라 안씀,앞으론 폼태그를 서블릿으로보낼거임 --%> -->
		<form name="myform" method="post" action="test7Ok.jsp"> <%-- post방식(post는 이방법밖에없음)으로 바꿔봄 --%>
		성명 : <input type="text" name="name" value="Hong kil Dong" class="form-control"/>
		나이 : <input type="number" name="age" value="25" class="form-control"/>
		<input type="submit" value="전송" class="btn btn-success"/>
	</form>
</div>
<p><br/></p>
</body>
</html>