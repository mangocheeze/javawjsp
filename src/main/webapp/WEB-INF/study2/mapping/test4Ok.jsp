<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test4Ok.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>계산된 결과출력</h2>
	<p>
		${su1} ${opt} ${su2} = ${res} <!-- 저장소에 담았으니까 el로 표기 -->
	</p>
	<p>
		<%-- <a href="${ctp}/WEB-INF/study2/mapping/test4.jsp" class="btn btn-secondary"> 돌아가기</a>--%> <!-- 안되는이유 : 이젠 url로 못보냄 -->
		<a href="${ctp}/mapping/Test4.do" class="btn btn-secondary">돌아가기</a>
	</p>
	
</div>
<p><br/></p>
</body>
</html>