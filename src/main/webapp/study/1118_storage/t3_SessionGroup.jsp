<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_SessionGroup.jsp -->

<% //자바코드
	Enumeration names = session.getAttributeNames();//인터페이스
	
	while(names.hasMoreElements()) { //hasMoreElements() :너한테 자료있니?
		String name = (String)names.nextElement(); //강제형변환 names:객체 ? 
		out.println("세션명 : " + name + "<br/>");
	}
	
%>

<p>
	<a href="t3_SessionMain.jsp" class="btn btn-warning form-control">돌아가기</a>
</p>