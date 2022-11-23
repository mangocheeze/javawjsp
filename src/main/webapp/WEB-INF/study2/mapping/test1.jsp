<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test1.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include>  
	<!-- mapping폴더는 나자신, 위에 study2 , WEB-INF , webapp 이렇게 3개아래에있음 -->
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>이곳은 webapp\WEB-INF\study2\mapping\test1.jsp 입니다</h2> <!-- 윈도우에서의 경로표시 (does윈도우에서의 경로표시 \) -->
</div>
<p><br/></p>
</body>
</html>