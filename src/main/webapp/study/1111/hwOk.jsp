<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name= request.getParameter("name");
	int age= Integer.parseInt(request.getParameter("age"));
	String birthday= request.getParameter("birthday");
	String phone= request.getParameter("phone");
	String email= request.getParameter("email");
	String mid= request.getParameter("mid");
	String pwd= request.getParameter("pwd");
	String gender= request.getParameter("gender");
	String[] hobbys= request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>hwOk.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원가입폼 입력된 정보</h2>
	<table class="table table-bordered">
		<tr>
			<td>성명</td>
			<td><%=name %></td>
		</tr>
		<tr>
			<td>나이</td>
			<td><%=age %></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><%=birthday %></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=mid %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=pwd %></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=phone %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=email %></td>
		</tr>
		<tr>
			<td>성별</td>
			<td><%=gender %></td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
					<%
						for(String hobby : hobbys){
							out.print(hobby + " / ");
						}
					%>
			</td>
		</tr>
	</table>
	<p><a href="hw.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>