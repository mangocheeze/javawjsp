<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>el1.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>EL(Expression Language)</h2>
	<hr/>
	<p>
		<b>용도 : 사용자가 '값/변수/수식/객체'을 전송후 저장해서 처리할 용도로 사용</b> <!-- 요청하지않고 바로사용가능 -->
		<b>표기법 : $ {값/변수/수식/객체/제어문}</b>
	</p>
	<hr/>
<% 
/* 스크립틀릿: jsp에서 자바코드작성 */
	String atom = "Seoul"; //실제 업무에서는 앞의 폼태그를 통해서 넘어온 값들을 변수로 저장받는다고 가정 (String atom = request.getParameter("atom");)
	String name = "홍길동"; //홍길동을 name변수에 담음
	int su1 = 100, su2 = 300;
	
	//앞의 데이터가 컨트롤러(서비스)객체 에서 저장소를 통해서 넘겨주었을경우에는 EL을 통해서 바로 받을 수있다
	pageContext.setAttribute("atom", atom); //pageContext저장소에 저장
	pageContext.setAttribute("name", name);
	pageContext.setAttribute("su1", su1);
	pageContext.setAttribute("su2", su2);
%>

	<!-- 표현식: 위에 스크립틀릿에 작성한걸 표현식에 출력 -->
	<h4>1.스크립틀릿을 사용하는 방법과 표현식(일반표현식)을 이용한 방법</h4>
	atom = <%=atom %><br/>
	name = <%=name %><br/>
	su1 = <%=su1 %><br/>
	su2 = <%=su2 %><br/>
	
	<!-- form1.jsp에서 받아온 value를 EL표기법으로 출력 -->
	<h4>2.jsp에서 폼태그(get/post)로 넘긴자료를 jsp로 바로받을때</h4> <!-- form.jsp에서 전송버튼 눌러야 값이 들어감 -->
	atom = ${param.atom}<br/> <!-- 매개변수(파라미터)값으로 넘어온 데이터 중 name이라는 이름을갖는 데이터의값을 가져오겠다 -->
	name = ${param.name}<br/>
	su1 = ${param.su1}<br/>
	su2 = ${param.su2}<br/>
	<!-- ${param.name} 는 request.getParameter("name") 과 같다.-->
	
	<!-- ElJstlVO.java에서 vo를 만들고 El1.java에서 vo에 담아온걸 출력 -->
	<h4>3.jsp에서 폼태그(get/post)로 넘긴자료를 서블릿을 통해서 바로받을때</h4>  <!-- vo에 담아서 받음 -->
	atom = ${vo.atom}<br/>
	name = ${vo.name}<br/>
	su1 = ${vo.su1}<br/>
	su2 = ${vo.su2}<br/>
	<hr/>
	
<%
	/* 아래 value="${mid}" 위해서 필요한코드 */
  String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  pageContext.setAttribute("mid", mid); //pageContext저장소에 담음
%>
	<!-- form1.jsp가아닌 여기서 실행해야함 -->
	<h3>현재 폼에서의 값의 전달</h3>
	<div>
		<!-- <form name= "myform" method="post" action="">  --><!-- 서버로 전송할때 method,name 생략가능 action:서버로 보내는 받는화일 생략가능-->
		<form> <!-- 내가보내고 내가받을때 다 생략가능 아이디를 전송하면 그대로 남아있음-->
			<div>아이디: <input type="text" name="mid" value="${mid}"/></div>
			<div><input type="submit" value="전송"/></div>
			<div>전송된 아이디 : <font color="red"><b>${mid}</b></font></div>
		</form>
	</div>
</div>
<p><br/></p>
</body>
</html>