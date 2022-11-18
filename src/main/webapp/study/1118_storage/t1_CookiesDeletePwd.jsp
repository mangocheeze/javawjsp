<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesDeletePwd.jsp -->

<%
	Cookie[] cookies = request.getCookies();

	//특정쿠키만 지우고싶으면 쿠키에 변수이름을 주고 그 변수이름이 맞냐 물어보면됨
	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cPwd")) { //쿠키이름이 cPwd면 
				cookies[i].setMaxAge(0); 
				response.addCookie(cookies[i]);				
			}
		}
	}
%>

<script>
	alert("비밀번호 쿠키 삭제 완료!!");
	location.href ="t1_CookiesMain.jsp";  //location.href ="";보내는명령
</script>