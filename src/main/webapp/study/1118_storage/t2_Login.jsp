<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	//전역변수를 줄거임 아래다가 뿌려야하니까
	String mid="";
	
	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {
				mid = cookies[i].getValue();
				pageContext.setAttribute("mid", mid); 
				break; //빠져나감
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t2_Login.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<form name = "myform" method="post" action="t2_LoginOk.jsp">
		<table class="table table-bordered text-center">
			<tr>
				<td colspan="2"><font size="5">로 그 인</font></td>
			</tr>
			<tr>
				<th class="bg-secondary text-white">아이디</th>
				<td><input type="text" name="mid" value="${mid}" autofocus required class="form-control"/></td> <!-- el표기법은 무조건 저장소에 넣어야나옴 -->
			</tr>
			<tr>
				<th class="bg-secondary text-white">비밀번호</th>
				<td><input type="password" name="pwd" required class="form-control"/></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="로그인" class="btn btn-success"/> &nbsp;&nbsp;
				<input type="reset" value="다시입력" class="btn btn-danger"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<p><br/></p>
</body>
</html>