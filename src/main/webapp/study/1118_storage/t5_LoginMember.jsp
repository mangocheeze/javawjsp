<!-- 여긴 완벽한 view라 제대로 만들어줘야함 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = session.getAttribute("sMid") == null ? "" : (String)session.getAttribute("sMid");  //null이면 공백 아니면 문자로바꿔서 mid에 넣어라
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t5_LoginMember.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원 전용방</h2>
	<p><font color="blue">${sMid}</font>님 로그인 중이십니다.</p> <!-- 쿠키에있는 id를("mid") 읽어옴 -->
	<hr/>
	<!-- 앞으로 절대경로로 적기 -->
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px"></p>
	<hr/>
	<p>
	  방문카운트(session) : <br/>
	  전체 총방문카운트(application) : <font color='red'><b>${aVisitCnt}</b></font>
	</p>
  <hr/>
	<div class="row">
	  <!-- 아직안배웠으니스크립틀릿으로하기 -->
<% if(mid.equals("admin")) { %> <!-- 한줄이여도 무조건 {}해야함 -->
	  <div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t5_LoginDelete.jsp" class="btn btn-success form-control">전체 방문카운트 초기화</a></div>
<% } %>
	  <div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t5_LogOut.jsp" class="btn btn-success form-control">로그아웃</a></div>
	</div>
	<hr/>
</div>
<p><br/></p>
</body>
</html>