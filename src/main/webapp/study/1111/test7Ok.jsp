<!-- test7.jsp에서 받아옴, 여기말고 test7.jsp에서 실행해야함 -->
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
	<%-- 간단한 출력은 표현식이 편함 --%>
	성명 : <%=request.getParameter("name") %><br/>  <%--request:매개변수가 나한테 온거있니? 하고 서버에게 물어보는거임 Parameter :매개변수 --%>
	나이 : <%=request.getParameter("age") %><br/>  
	<p><a href="test7.jsp">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>
 