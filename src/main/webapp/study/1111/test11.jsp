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
<!-- 	<form name="myform" method="post" action="../../test11Ok"> --> <!-- 서블릿으로연결(콘솔에 출력) -->
	<form name="myform" method="post" action="test11Ok.jsp"> <!-- test11Ok.jsp로 연결 -->
		성명 : <input type="text" name="name" value="Hong kil Dong" class="form-control"/>
		나이 : <input type="number" name="age" value="25" class="form-control"/>
		<div>
		성별 :	<input type="radio" name="gender" value="남자" checked />남자
				 	<input type="radio" name="gender" value="여자"/>여자
		</div>
		<div>
		취미:
			<input type="checkbox" name="hobby" value="등산">등산 <%-- id로 프론트 체크해야함 --%>
			<input type="checkbox" name="hobby" value="낚시">낚시
			<input type="checkbox" name="hobby" value="독서">독서
			<input type="checkbox" name="hobby" value="음악감상">음악감상
			<input type="checkbox" name="hobby" value="수영">수영
		</div>
		<input type="submit" value="전송" onclick= "fCheck()" class="btn btn-success"/>
	</form>
</div>
<p><br/></p>
</body>
</html>