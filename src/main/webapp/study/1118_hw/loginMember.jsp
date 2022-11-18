<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	String mid=""; //전역변수를 줄거임 아래다가 뿌려야하니까
	
	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {//쿠키들중 쿠키명이 cMid(쿠키에있는 아이디랑)랑 비교해서 같은걸가져올거임(cMid를 가져올거야)
				mid = cookies[i].getValue(); //근데 cMid가아니라 거기들어있는 값을 가져와야함 그걸 변수 mid에 담음
				break; //빠져나감
			}
		}
	}
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