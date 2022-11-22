<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>el2.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h3>form값을 통해서 전달받은 배열값들의 처리</h3>
	<p>선택한 취미는?
	<!-- 저장소에 담았으니까 EL로함, EL에는 제어문이없으니까 있는그대로 씀(EL안에는 제어문쓸수있음(삼항연산자)근데 너무길어짐, 그래서 jstl섞어서 사용하면좋음 -->
	  ${hobbys[0]} / ${hobbys[1]} / ${hobbys[2]} / ${hobbys[3]} / ${hobbys[4]} <!-- 체크박스가 5개니까 -->
	</p>
	<h5>forEach를 활용한 배열값의 출력</h5> <!-- jstl사용(jstl사용하려면 web-inf폴더안에 lib폴더에 jstl.jar , standard.jar있어야함 -->
	<c:forEach var="hobby" items="${hobbys}" varStatus="st">
	  ${hobby} /
	</c:forEach>
	<hr/>
	<p>
 <a href="${ctp}/study/1121_EL_JSTL/form2.jsp" class="btn btn-success">돌아가기</a><!-- contextPath를 JSTL로 위로 올려서 앞으로 ctp사용하면됨 -->
	</p>
</div>
<p><br/></p>
</body>
</html>