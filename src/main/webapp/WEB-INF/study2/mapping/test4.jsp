<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>  <!-- ${ctp}를 사용할수있게해줌 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test4.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<form name="myform" method="post" action="${ctp}/mapping/Test4Ok.do"> <!-- 확장자 .do로보냄/ 그럼 모든 do는 오라고하는 Test3Controller.java로감 --><!-- /mapping은 써도되고안써도되고 -->
		<h2>자료 전송 연습</h2>
		<div>
			첫번째수 : <input type="text" name="su1" value="100" class="form-control m-3" />
		</div>
		<div>
			연산자 :
			<select name="opt" class="form-control m-3">
				<option value="+" selected>더하기</option> <!-- 기본값 -->
				<option value="-">빼기</option>
				<option value="*">곱하기</option>
				<option value="/">나누기</option>
				<option value="%">나머지</option>
			</select>
		</div>
		<div>
			두번째수 : <input type="text" name="su2" value="200" class="form-control m-3" />
		</div>
		<div>
			<input type="submit" value="전송" class="btn btn-success form-control" />
		</div>
		</form>
</div>
<p><br/></p>
</body>
</html>