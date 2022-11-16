<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- test1Ok.jsp -->
<%@ include file="../../include/bs4.jsp" %> <!-- 부트스트랩파일 -->
<%
  request.setCharacterEncoding("utf-8");

	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid"); //mid 입력된게 null이 참일경우 공백 참이아닐경우 입력된값을 불러옴
	String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
	String name = request.getParameter("name")==null ? "" : request.getParameter("name");
%>
<p><br/></p>
<div class="container">
	<p>아이디 : <%=mid%></p> <%-- 위의 변수에 담은것을 가져와 출력--%>
	<p>비밀번호 : <%=pwd%></p>
	<p>이름 : <%=name%></p>
	<p><a href="test1.jsp" class="btn btn-success">돌아가기</a></p>	
</div>
<% if(mid.equals("admin")&& pwd.equals("1234")) { %> <%--mid랑 pwd가 관리자가 맞으면 아래실행 --%>
	<jsp:forward page="test1Res.jsp">
	<jsp:param value="안녕" name="flag"/>
	</jsp:forward>  <%-- forward : 중간에 멈추지않고 page="" 에 쓴곳으로 바로가게함 /바로 test1Res.jsp로 가게함(url이test1Ok.jsp라고 보여짐)--%>
<% }else { %> 
	<jsp:forward page="test1.jsp"></jsp:forward>   <%-- 관리자 아이디나 비번이아니면 test1.jsp에서 머물러있게함 --%>

<% } %>