<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_LoginOk.jsp -->

<!-- 지금 서블릿 코드를 jsp에서짜고있음 서블릿에 그대로 옮겨도 된다는걸 보여주기위해서 만듦 --> 
<%
	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
	
	//admin하고 hkd1234 만 허용을해서 두개중 하나라도 맞아야 들어옴(로그인)
	if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("hkd1234") && pwd.equals("1234"))){
		//아이디를 쿠키에저장시켜줄거임
		Cookie cookie = new Cookie("cMid",mid);//쿠키생성
		cookie.setMaxAge(60*5);// 쿠키의 만료시간을 5분으로 설정함
		response.addCookie(cookie); //내 컴퓨터에 쿠키를 저장시킴
		
		out.println("<script>");
		out.println("alert('"+mid+"님 로그인 되셨습니다.');");
		out.println("location.href='t2_LoginMember.jsp';");
		out.println("</script>");
	}
	else {
		out.println("<script>");
		out.println("alert('아이디와 비밀번호를 확인하세요!');");
		out.println("history.back();");
		out.println("</script>");
	}
%>