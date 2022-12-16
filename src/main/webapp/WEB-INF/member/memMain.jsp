<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memMain.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>회 원 전 용 방</h2>
	<hr/>
  <div id="memberInfo1" class="mr-5">
		<p><font color="blue"><b>${sNickName}</b></font>님 로그인중이십니다</p>
		<p>현재<font color="blue"><b>${strLevel}</b></font>등급이십니다</p>
		<p>누적 포인트 : ${point}</p>
		<p>최종 접속일 : ${lastDate}</p>
		<p>총 방문횟수 : ${visitCnt}</p>
		<p>오늘 방문횟수 : ${todayCnt} </p>
	</div>
	<hr/>
  <div id="memberInfo2">
    <h3>회원사진</h3>
	  <p><img src="${ctp}/data/member/${photo}" width="200px" /></p>
  </div>
  <hr id="memberInfo3" />
	<h4>활동내역</h4>
	<p>방명록에 올린글수 : ${cnt}개</p>	<!-- 방명록에 들어가있는 name을, 내 name이랑비교하거나 nickName이 일치할때 가져옴 -->
	<p>게시판에 올린글수 : </p>	
	<p>자료실에 올린글수 : </p>	
	<p></p>	
	<p></p>	
	<p></p>	
	<p></p>	
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>