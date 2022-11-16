<!-- view -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8"); //혹시 한글깨질경우 작성하는코드 
	
	String mid = request.getParameter("mid");
	String name = request.getParameter("name");
	String hostIp = request.getParameter("hostIp");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test2Res.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
	<p>이곳은 관리자 화면입니다.</p>
	<p>전송된 아이디 : <%=mid %></p>
	<p>전송된 이름 : <%=name%></p>
	<hr/>
	<p><img src="../../images/2.jpg" width="200px"/></p>
	<hr/>
	접속전송방식 : <%=request.getMethod() %> <br/> <!-- post로 전송했는데 접속전송방식이 Get으로 나온이유: Test2Ok.java에서 test2Res.jsp를 get방식으로 넘겼기때문(메소드에 넣어서 보내는 post방식말곤 다 get) -->
	접속 URL " <%=request.getRequestURL() %>"<br/>
	접속 URI " <%=request.getRequestURI() %>"<br/>
	접속 서버이름: <%=request.getServerName() %><br/>
	접속 포트번호: <%=request.getServerPort() %><br/>
	요청 파라메터 길이: <%=request.getContentLength() %>><br/>
	현재 ContextPath : <%=request.getContextPath() %><br/>
	현재 사용중인 프로토콜 : <%=request.getProtocol() %><br/>
	접속자 IP : <%=hostIp %> <br/> <!-- 192.168.50.__:9090 ip로 들어가야됨 -->
	
	<hr/>
	<p>
		<!-- <a href="logout.jsp">로그아웃</a> -->
		<a href="<%=request.getContextPath() %>/j1114_Logout?name=<%=name %>">로그아웃</a> <!-- Logout파일에 name값을 가지고감(~님로그아웃되었습니다 하려고)/name은 jsp변수임. 마지막에있어서 따당안함 -->
	</p>
</div>
<p><br/></p>
</body>
</html>