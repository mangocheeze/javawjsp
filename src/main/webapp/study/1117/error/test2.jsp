<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/study/1117/error/errorUser.jsp" %>  
<!-- test1.jsp에선 web.xml에 에러를써서 모든 400번대나,500번대에러거 났을때 뜨게하는거고 이걸쓰면 이페이지에서만 에러났을때 errorUser.jsp파일이 뜨게함/페이지 지사자가 이런역할도함 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test2.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>에러코드 발생 페이지2</h2>
	<%
		int su = 10 / 0;  //일부로 에러를냄
	%>
</div>
<p><br/></p>
</body>
</html>