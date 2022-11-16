<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String sw = request.getParameter("sw")==null ? "" : request.getParameter("sw"); 
%> <!-- 여기서 sw는 munu에서 가져온거임. 읽어온 sw가 null값이 참이라면 공백을주고, 거짓이라면 (null이아니라면) 읽어온값을 넣어줘라 그걸 변수 sw에 담음 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>main.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <style>
  	body{
  		width: 1000px;
  		margin: 0px auto; <!-- 가운데로 -->
  		padding: 0px;
  	}
  	#header {
  		background-color : aqua;
  		text-align : center;
  		height : 80px;
  	}
  	#footer {
  		background-color : #ccc;
  		text-align : center;
  		height : 75px;
  	}
  	#content {
  		background-color : #fff;
  		text-align : center;
  	}
  	
  </style>
</head>
<body>
<div class="container">

	<!-- 헤더영역( '메뉴/로고' 를 표시한다) -->
	<div id="header">
	<br/>
		<%@ include file="menu.jsp" %>  <!-- include 지시자 -->
	</div>
	<!-- 본문영역 -->
	<div id ="content">
	<br/>
<% if(sw.equals("guest")) { %> <!-- sw가 guest랑 같으면 guest페이지로 이동함 -->
	<%@ include file="guest.jsp" %>
<%} else if(sw.equals("board")) { %>
	<%@ include file="board.jsp" %>
<%} else if(sw.equals("pds")) { %>
	<%@ include file="pds.jsp" %>
<%} else if(sw.equals("photo")) { %>
	<%-- <%@ include file="photo.jsp" %> include지시자:현재 JSP 페이지에 include로 연결한 페이지를 하나로 합쳐 컴파일해 실행함--%>
	<jsp:include page="photo.jsp"></jsp:include>  <!-- 위랑 똑같은역할.include 액션태그(쓰는이유: 프론트에서 이걸쓰기바람)-->
<%} else { %>
		<h2>이곳은 메인화면 입니다</h2>
		<hr/>
		<p><img src="../../images/1.jpg" width="600px"/></p>
<%} %>
	<br/>
	</div>
	<!-- 푸터영역(Copyright나 주소,소속,작성자 등을 기술한다) -->
	<div id="footer">
		<%@ include file="footer.jsp" %>
	</div>
	
</div>
</body>
</html>