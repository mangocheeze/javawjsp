<!-- view -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test4.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>컨트롤러가 2개이상의 URL 제어 연습</h2>
	<p>1개의 폼안에서 POST/GET방식의 제어</p> <!-- 값이 하나기에 하나의 컨트롤러로 두개의방식으로 보낼순없지만, 서블릿에서 service로 했기때문에 받는쪽에서 get이던 post던상관없이 받을수있음 그래서 아래 전송버튼을 두가지방식으로 다르게만듦-->
	<hr/>
<%-- 	<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_Test4"> --%>
	<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_T4"> <!-- ex.너무길어서 T4로 바꿨을경우 2개이상 컨트롤러 만들기, action뒤에쓰는 주소는 큰의미가없음 -->
		<p>
			<input type="submit" value="전송(submit:post방식)" class="btn btn-success"/> <!--  hidden값을 넘길땐 무조건 post방식을써야함 (값을 감춰서 보내야할땐)-->
			<input type="button" value="전송(location:get방식)" onclick="location.href='<%=request.getContextPath()%>/j1114_Test4';" class="btn btn-primary"/> <!-- 이렇게쓰면 무조건 get방식? url에 다 적어서 보냄 왜 hidden이 null값이 나오지?hidden은 post방식으로써야되니까-->
		</p>
		<input type="hidden" name="name" value="홍길동" />  <!-- hidden : 사용자에게는 보이지 않는 숨겨진 입력 필드, 매우중요 -->
	</form>
</div>
<p><br/></p>
</body>
</html>