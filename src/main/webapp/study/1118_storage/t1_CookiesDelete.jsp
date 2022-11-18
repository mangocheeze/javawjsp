<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesDelete.jsp -->

<%
	Cookie[] cookies = request.getCookies();

	if(cookies != null) {	//쿠키가 있을때만해라 , 유효성검사
		for(int i=0; i<cookies.length; i++) {
			cookies[i].setMaxAge(0); //쿠키의 만료시간을 0으로 설정하여 쿠키를 제거한다
			response.addCookie(cookies[i]); //위에까지는 내컴퓨터에있음 이걸써야 지운상태가 저장이됨/쿠키저장시간이 0으로 되니까 자동으로 없어짐?
			//특정쿠키만 지우고싶으면 쿠키에 변수이름을 주고 그 변수이름이 맞냐 물어보면됨
		}
	}
%>

<script>
	alert("쿠키 삭제 완료!!");
	location.href ="t1_CookiesMain.jsp";  //location.href ="";보내는명령
</script>