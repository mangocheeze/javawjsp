<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mid = session.getAttribute("sMid") == null ? "" : (String)session.getAttribute("sMid");  //null이면 공백 아니면 문자로바꿔서 mid에 넣어라
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>loginMember.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container text-center">
  <h2>회원 전용방</h2>
  <hr/>
  <p><font color="blue"><b>${sMid}</b></font>님 로그인 중이십니다.</p>
  <p>방문자수(session사용) : ${sVisitCnt}</p>
  <hr/>
  <p><img src="${pageContext.request.contextPath}/images/1.jpg" width="400px"/></p>
  <hr/>
  <div class="row">
    <div class="col"><a href="${pageContext.request.contextPath}/study/j1118_hw/LogOut" class="btn btn-success form-control">로그아웃</a></div>
  </div>
</div>
<p><br/></p>
</body>
</html>