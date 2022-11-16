<!-- 여기서 실행해야함,콘솔에 값이뜸 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test11.jsp</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		'use strict';
		
		function fCheck() {
			let name = myform.name.value;
			
			if(name == "") {
				alert("이름을 입력하세요?");
				myform.name.focus();
				return false;
			}
			else {
				myform.submit();
			}
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>값 전송하기</h2>
	<!-- 	<form name="myform" method="post" action="../../t12"> --> <!-- 여기의 t12는 web.xml에 있음 -->
	<!-- <form name="myform" method="post" action="/javawjsp/t12"> --> <!-- 원랜 ../../말고 이렇게 쓰는게 맞음 -->
	<!--<form name="myform" method="post" action="test12Ok.jsp"> --> <!-- 이렇게쓰면 전송을 누르면 test12Ok.jsp 로감 -->
	<%-- <form name="myform" method="post" action="<%=request.getContextPath()%>/test12Ok"> --%> <!-- 전송을 누르면 test12Ok로가고,web.xml의 컨트롤러가  j1111아래 Test12.java를 부름  -->
	<form name="myform" method="post" action="<%=request.getContextPath()%>/t12"> <!--action에 쓰는건 url주소, 전송을 누르면 가는곳(submit을 통해서) (url에다가 쓰면간다)/ 위에 javawjsp대신 이렇게써주면됨 -->
		성명 : <input type="text" name="name" value="Hong kil Dong" class="form-control"/>
		나이 : <input type="number" name="age" value="25" class="form-control"/>
		<div>
		성별 :	<input type="radio" name="gender" value="남자" checked />남자
				 	<input type="radio" name="gender" value="여자"/>여자
		</div>
		<div>
		취미:
			<input type="checkbox" name="hobby" value="등산" checked>등산 <%-- 지금은 name만썼지만 원래 id로 프론트 체크해야함 --%>
			<input type="checkbox" name="hobby" value="낚시">낚시
			<input type="checkbox" name="hobby" value="독서">독서
			<input type="checkbox" name="hobby" value="음악감상">음악감상
			<input type="checkbox" name="hobby" value="수영">수영
		</div>
		<input type="submit" value="전송" onclick= "fCheck()" class="btn btn-success"/>
	</form>
	<hr/>
	<p>
		<img src="../../images/1.jpg" width="300px"/>
	</p>
</div>
<p><br/></p>
</body>
</html>