<!-- 여긴 완벽한 view라 제대로 만들어줘야함 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	//전역변수를 줄거임 아래다가 뿌려야하니까
	String mid="";
	
	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {//쿠키들중 쿠키명이 cMid(쿠키에있는 아이디랑)랑 비교해서 같은걸가져올거임(cMid를 가져올거야)
				mid = cookies[i].getValue(); //근데 cMid가아니라 거기들어있는 값을 가져와야함 그걸 변수 mid에 담음
				pageContext.setAttribute("mid", mid); //저장소pageContext(저장소:현재페이지에서만살아있음) 에 집어넣음 
				break; //빠져나감
			}
		}
	}
	//????
	String imsiMid = request.getParameter("mid")==null ? "" : request.getParameter("mid"); //기존변수는 사라져서 새로운변수로받아서
	if(mid.equals("")) pageContext.setAttribute("mid", imsiMid);		//지금껄로 넣어달라
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t2_LoginMember.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원 전용방</h2>
	<p><font color="blue">${mid}</font>님 로그인 중이십니다.</p> <!-- 쿠키에있는 id를("mid") 읽어옴 -->
	<hr/>
	<!-- 앞으로 절대경로로 적기 -->
	<p><img src="${pageContext.request.contextPath}/images/3.jpg" width="300px"></p>
	<hr/>
	<div class="row">
	  <div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t2_LoginDelete.jsp" class="btn btn-success form-control">쿠키의 아이디 삭제</a></div>
	  <div class="col"><a href="${pageContext.request.contextPath}/study/1118_storage/t2_LogOut.jsp?mid=${mid}" class="btn btn-success form-control">로그아웃</a></div>
	</div>
	<hr/>
	
</div>
<p><br/></p>
</body>
</html>