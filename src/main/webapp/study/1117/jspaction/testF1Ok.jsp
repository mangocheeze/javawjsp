<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 길게쓰면 힘드니까 변수 c 에 담아서씀(접두어 c, 변수 c 쓰는건 프로그래머의 약속) -->
<!-- 여기선 한글처리해줘야함 -->
<% request.setCharacterEncoding("utf-8"); %>  <!-- 위에 contentType="text/html; charset=UTF-8" 가있으니까 이거 한줄만써줌 -->

<!-- 앞에서 전송된 값을 변수에 담아보자... -->
<c:set var="name" value="${param.name}"/> <!-- 앞의 매개변수로 넘겨주는 name에서 값을 넘겨주겠다?? -->
<c:set var="gender" value="${param.gender}"/>
<c:set var="age" value="${param.age}"/>
<c:set var="job" value="${param.job}"/>
<c:set var="address" value="${param.address}"/>
<!-- c: JSTL의 core라이브러리라는뜻, 변수를 설정한다는 뜻) ,/로 잘라줌 ,var="name" name이라는 변수에 넣어달라-->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>testF1Ok.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>전송된 자료 결과 보기 2</h2>
	<div> 
		<table class="table table-bordered">
			<tr>
				<th>성명</th>
				<td>${name}</td> <!--원래 ${vo.name}  --> <!-- 여기서 name은 var의 변수 name?? -->
			</tr>
			<tr>
				<th>성별</th>
				<td>${gender}</td>  
			</tr>
			<tr>
				<th>나이</th>
				<td>${age}</td> 
			</tr>
			<tr>
				<th>직업</th>
				<td>${job}</td>  
			</tr>
			<tr>
				<th>주소</th>
				<td>${address}</td>  
			</tr>
		</table>
	</div>
	
</div>
<p><br/></p>
</body>
</html>