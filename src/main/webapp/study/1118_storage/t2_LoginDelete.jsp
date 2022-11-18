<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_LoginDelete.jsp -->
<!-- 쿠키에 아이디 삭제 버튼 누르면 실행(지우고 머물러있음) -->
<%
	Cookie[] cookies = request.getCookies();
	String mid = "";

	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {		
			mid = cookies[i].getValue();
			pageContext.setAttribute("mid", mid); //_님의 쿠키 아이디 삭제완료
			cookies[i].setMaxAge(0); 
			response.addCookie(cookies[i]); 
			break;
			}
		}
	}
%>

<script>
	alert("${mid}쿠키 아이디 삭제 완료!!");
	location.href ="t2_LoginMember.jsp?mid=${mid}";  // 다시 회원 전용방으로감(쿠키아이디만 삭제할거니까 다시 돌아가도 ~님 로그인중이십니다 뜨게하기)
</script>