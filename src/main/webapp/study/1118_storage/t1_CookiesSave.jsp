<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesSave.jsp -->

<% //jsp에서 서블릿 코드를 넣음

	String mid = "hkd1234";
	Cookie cookieMid = new Cookie("cMid",mid); //스페이스로 해줘야함 /선생님과의 약속 cMid: 쿠키에 있는 id를 이렇게쓸거임
	//쿠키는 처음부터 값을 넣어줘야함(기본생성자가없음)//Cookie("cMid",mid) :id를 저장시킬거임 ("Cookie가 저장하는변수",값이들어있는변수(사용하는변수))
	cookieMid.setMaxAge(60*60*24);  //setMaxAge(만료시간) :생명주기(쿠키의 만료시간 : 단위 '초' -1000분의1초말고 그냥초임 , 1일(60*60*24))
	
	String pwd = "1234";
	Cookie cookiePwd = new Cookie("cPwd",pwd); 
	cookieMid.setMaxAge(60*60*24);

	String job = "학생";
	Cookie cookieJob = new Cookie("cJob",job); 
	cookieMid.setMaxAge(60*60*24);
	
	// 쿠키를 클라이언트에 저장하기(즉,사용자 컴퓨터에 저장된다)
	response.addCookie(cookieMid); //내컴퓨터에 만든게 저장 /mid쿠키가 저장됨/이것만 set으로 저장안하고 add로함
	response.addCookie(cookiePwd);
	response.addCookie(cookieJob);
%>
<script>
	alert("쿠키에 저장 완료!!");
	location.href ="t1_CookiesMain.jsp";  //location.href ="";보내는명령
</script>