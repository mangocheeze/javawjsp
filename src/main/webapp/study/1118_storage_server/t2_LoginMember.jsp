<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>t2_LoginMember.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container text-center">
  <h2>회원 전용방</h2>
  <hr/>
  <p><font color="blue"><b>${sMid}</b></font>님 로그인 중이십니다.</p>
  <p><img src="<%=request.getContextPath()%>/images/1.jpg" width="400px"/></p>
  <hr/>
  <div class="row">
    <div class="col"><a href="${pageContext.request.contextPath}/study/storage/T2_LoginDel" class="btn btn-success form-control">쿠키 아이디 삭제</a></div>
    <div class="col"><a href="${pageContext.request.contextPath}/study/storage/T2_LogOut" class="btn btn-success form-control">로그아웃</a></div>
  </div>
</div>
<p><br/></p>
</body>
</html>