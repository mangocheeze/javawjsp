<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>t1_CookiesCheck.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>쿠키 확인</h2>
	<hr/>
	<!-- 원래 mvc로하려면 스파게티 코드(꺽쇠 %)는 위로 올려야함(공부하느라 여기다씀) -->
<%
	Cookie[] cookies = request.getCookies();  //거기에 있는 쿠키값을 읽어오겠다//쿠키는 여러개 올수있어서 배열로함 ,쿠키는 import가 안걸림(쿠키는 jsp의 내장객체다,request도 내장객체)
	
	out.println("<table class='table table-hover text-center'>"); //테이블로 디자인함
	out.println("<tr class='table-dark'><th>번호</th><th>저장된 쿠키명</th><th>저장된 쿠키값</th></tr>");
	for(int i=0; i<cookies.length; i++) {
		out.println("<tr>");
		String strName = cookies[i].getName();	//쿠키명(저장시켜놓은 쿠키변수명) 가져오기 / str:문자라는뜻 변수명은 마음대로 /쿠키는 문자로되어있음
		String strValue = cookies[i].getValue(); //쿠키값(저장시켜놓은 쿠키값)가져오기
	
		out.print("<td>" + (i+1) + "</td>");  //i로하면 0이라 이상해서 +1해줌
		out.print("<td>" + strName + "</td>"); 
		out.print("<td>" + strValue + "</td>"); 
		out.println("</tr>");
	}
	out.println("</table>"); //}밖에 쓴이유? :스파게티코드를 그나마 벗어나려고?

%>
<hr/>
<div>
	<a href ="t1_CookiesMain.jsp" class ="btn btn-secondary form-control">돌아가기</a>
</div>
</div>
<p><br/></p>
</body>
</html>