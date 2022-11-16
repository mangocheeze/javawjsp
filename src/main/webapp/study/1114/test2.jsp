<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test2.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<!-- 로그인창에서 '아이디/비밀번호/성명'을 입력후 
		 서버로 전송후, 관리자 인증이 되면 인증성공창에서 입력된 '아이디/성명'을
		 모두 출력하시오
 -->
<body>
<p><br/></p>
<div class="container">
	<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_Test2Ok"> <!-- (패키지명_불러주는 java화일)나중에 혹시나 이름같아질까봐 폴더이름까지 적어줌 -->
		<div><h2>로 그 인</h2></div>
		<p>
			아이디: <input type="text" name="mid" id="mid" autofocus required class="form-control"/> <!-- required : 서버로 제출되기전 반드시 입력되어야하는 필드를 명시함 -->
		<p>
		<p>
			비밀번호: <input type="password" name="pwd" id="pwd" class="form-control"/>
		</p>
		<p>
			성명: <input type="text" name="name" id="name" required class="form-control"/>
		</p>
		<p>
			<input type="submit" value="전송" class="btn btn-success"/> <!--  submit 은 바로전송, button은 체크(onclick)하고 전송(엔터쳐도 안넘어옴) -->
		</p>
		<input type="hidden" name="hostIp" value="<%=request.getRemoteAddr()%>"/>  <!-- 접속하는사람의 ip를 읽어오라 -->
	</form>
</div>
<p><br/></p>
</body>
</html>