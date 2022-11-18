<!-- web.xml에 공통변수에 초기값 할당하기 (web.xml건들면 무조건 서버 재시작해서 보기) -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>green.jsp</title>
	<jsp:include page="../../../include/bs4.jsp"></jsp:include> <!-- EL표기법 -->
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>웹사이트에 접속시 초기 설정된 값 받아오기 연습</h2>
	<hr/>
	<p>특정서블릿에서 설정된 초기값 받아오기</p>
	<div>
		<p>아이디 : ${mid}</p>
		<p>비밀번호 : ${pwd}</p>
		<p>아이디 : ${className}</p>
	</div>
	<hr/>
	
	<p>공통변수로 설정된 초기값 받아오기</p>
	<p>회사명 : ${logoName} </p>
	<p>홈페이지주소 : <a href="${homeAddress}" target="_blank">${homeAddress}</a></p> <!-- http://192.168.50.210:9090/javawjsp/ 까지만쓰면 홈페이지 초기파일(index.jsp) 의 주소링크가 뜸 파일을 지정하고싶으면 /뒤에 파일명쓰기,초기파일은 web.xml에 설정 -->
	<div>
		<input type="button" value="서블릿으로부터초기값가져오기" onclick="location.href='${pageContext.request.contextPath}/Green';" class="btn btn-success"/>
		<input type="button" value="공통변수할당값가져오기" onclick="location.href='${pageContext.request.contextPath}/GlobalGreen';" class="btn btn-success"/>
	</div>
</div>
<p><br/></p>
</body>
</html>