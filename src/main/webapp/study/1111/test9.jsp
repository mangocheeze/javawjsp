<!-- 여기서 실행해야함,콘솔에 값이뜸 -->
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
	<form name="myform" method="post" action="../../test9Ok"> <%-- 서블렛으로보냄 --%>
		성명 : <input type="text" name="name" value="Hong kil Dong" class="form-control"/>
		나이 : <input type="number" name="age" value="25" class="form-control"/>
		<input type="submit" value="전송" class="btn btn-success"/>
	</form>
</div>
<p><br/></p>
</body>
</html>