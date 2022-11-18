<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t5_LoginOk.jsp -->
<%
	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
	
	//admin하고 hkd1234 만 허용을해서 두개중 하나라도 맞아야 들어옴(로그인)
	if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("hkd1234") && pwd.equals("1234"))){
		
		session.setAttribute("sMid", mid);//session에 저장
		// 필요하면 더넣기
		
		int visitCnt = 0; 
		
		if(application.getAttribute("aVisitCnt")==null){
			application.setAttribute("aVisitCnt", 1);//전체전역변수인 application의 변수로저장 /null이면 1로주고		
		}
		else {
			visitCnt= (int) application.getAttribute("aVisitCnt") + 1; //로그인을 성공하고 한번이라도 이 변수가 생성되어있으면 들어옴(변수에들어가있는 값을읽어와서 거기다 1더한것을)
			application.setAttribute("aVisitCnt", visitCnt );
		}
		out.println("<script>");
		out.println("alert('"+mid+"님 로그인 되셨습니다.');"); //mid로 써도되고 sMid로써도되는데 이건 el표기법으로해야함${mid}
		out.println("location.href='t5_LoginMember.jsp';");
		out.println("</script>");
	}
	else {
		out.println("<script>");
		out.println("alert('아이디와 비밀번호를 확인하세요!');");
		out.println("history.back();");
		out.println("</script>");
	}
%>