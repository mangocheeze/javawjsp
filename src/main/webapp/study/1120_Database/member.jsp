<!-- 여긴 완벽한 view라 제대로 만들어줘야함 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>member.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		function searchMid() { //개별회원조회
			let mid = prompt("찾고자 하는 아이디를 입력하세요?")
			
			location.href ="${pageContext.request.contextPath}/database/SearchMid?mid="+mid; //개별회원조회 출력창 호출 //get방식 mid변수에 mid담아서 보냄
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원 전용방</h2>
	<p><font color="blue">${sName}</font>님 로그인 중이십니다.</p> <!-- 쿠키에있는 id를("mid") 읽어옴 -->
	<hr/>
	<!-- 앞으로 절대경로로 적기 -->
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px"></p>
	<hr/>
	<p>
		<!-- 나중에 할거 -->
	  현재 보유중인 포인트 : <font color='red'><b>${point}</b></font><br/>
	  최종 방문일자 : <font color='red'><b>${sLastDate}</b></font> <!-- 세션에 있는걸 꺼냄 -->
	</p>
  <hr/>
  <div><a href="javascript:searchMid()" class="btn btn-success form-control m-3">개별 회원 조회</a></div> <!-- 자바스크립트로해서 함수쓸수있음 .조회 출력하는 창 예쁘게 바꾸기 -->
  <div><a href="${pageContext.request.contextPath}/database/MemberList" class="btn btn-success form-control m-3">회원 전체 조회</a></div> <!-- 원래 관리자만 조회할수있게해야되는데 공부하는거니까 그냥함 -->
  <hr/>
	<div class="row">
	  <div class="col"><a href="${pageContext.request.contextPath}/database/LogOut" class="btn btn-success form-control m-3">로그아웃</a></div>
	  <!-- 경로에서 study 써도되고 안써도됨, 쓸거면 LogOut.java에 서블릿도 고쳐주면됨 -->
	</div>
	<hr/>
</div>
<p><br/></p>
</body>
</html>