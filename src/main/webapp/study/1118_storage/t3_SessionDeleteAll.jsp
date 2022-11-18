<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_SessionDeleteAll.jsp -->

<%
	String imsiName = (String) session.getAttribute("sName"); //강제 타입변환
	pageContext.setAttribute("imsiName", imsiName);

	
	session.invalidate(); //현재 저장된 모든 세션 삭제
%>

<script>
	alert("${imsiName}님 모든 세션 삭제 완료!!"); //변수에 담아서 띄워야함
	location.href ="t3_SessionMain.jsp";  
</script>