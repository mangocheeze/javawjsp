<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jstl2.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>반복처리(forEach문)</h2>
	<pre>
		문법1 : < c : forEach var="변수" items="$ {배열/객체명}" varStatus="매개변수(=인자=Argument)">
							수행할 내용~~~
					 < /c : forEach>
		문법2 : < c : forEach var="변수" begin="초기값" end="최종값" step="증감값" varStatus="매개변수">  <!-- begin :변수의 초기값 -->
							수행할 내용~~~
					 < /c : forEach>
		문법3 : < c : forTokens var="변수" items="$ {배열/객체명}" delims="구분기호"> <!-- $ {} 띄워써야함(설명쓰는거라 적용되면안되서?) -->
							수행할 내용~~~
					 < /c : forTokens>
	</pre>
	<p>사용예 1 : <br/>
		<c:forEach var="i" begin="1" end="10" step="1" varStatus="st">  <!-- 10번 도는거임 --><!--  varStatus="st" :변수의 상태값을 st로줌 ,이거나 var 둘다 똑같은기능(생략가능)-->
			${i}.안녕/ &nbsp; 				
		</c:forEach>
	</p>
	
	<p>사용예 2-1 : <br/> <!-- var 사용안한버전 -->
		<c:forEach begin="1" end="10" step="1" varStatus="st">  
			${st.count}.안녕/ &nbsp; 				
		</c:forEach>
	</p>
	
	<p>사용예 2-2 : <br/>
		<c:forEach begin="1" end="10" step="1" varStatus="st">  
<%-- 		<c:forEach begin="5" end="10" step="1" varStatus="st">   인덱스 5부터꺼냄--%>
			${st.index} / ${st.count} / ${st.first} / ${st.last} <br/>		<!-- 인덱스가 0번째가아니라 초기값 1부터 최종값10까지 찍으라는거라 ${st.index}는 1이나옴 -->
			<!-- 인덱스 / 카운트(번째) / 첫번째가 맞으면 true /마지막이 맞으면 true  , ex. 오름차순으로할때 가장큰값을구하라고하면 ${st.last}를 써주면됨-->
		</c:forEach>
	</p>
	
<%
	String hobbys = "등산/낚시/수영/바둑/영화감상";
	request.setAttribute("hobbys", hobbys); //모든저장소로 담아도됨
%>
	<!-- 구분자 기준으로 알아서 잘라서 count 매겨줌 -->
	<p>사용예 3 : <br/>
		<c:forTokens var="hobby" items="${hobbys}" delims="/" varStatus="st">  
			${st.count}.${hobby}<br/>
		</c:forTokens>
	</p>
	<p>
		문제 : 전송된 '취미'중에서 '바둑'은 빨간색, '수영'은 파란색으로, 나머지는 있는그대로(검정색) 출력하시오.<br/>
		<c:forTokens var="hobby" items="${hobbys}" delims="/" varStatus="st">  <!-- delims : 구분자, 위에 "등산/낚시/수영/바둑/영화감상" 중/가 나오면 구분을 하겠다 -->
			<c:choose>
				<c:when test="${hobby == '바둑'}"><font color='red'><b>${hobby}</b></font><br/></c:when>
				<c:when test="${hobby == '수영'}"><font color='blue'><b>${hobby}</b></font><br/></c:when>
				<c:otherwise>${hobby}<br/></c:otherwise>
			</c:choose>
		</c:forTokens>
	</p>
	<hr/>
	<h3>2중 for문 활용(구구단)</h3>
	<p>이중 반복문을 활용하여 3단부터 5단까지 출력하시오</p>
	<c:forEach var="i" begin="3" end="5"> <!--var는 바깥 for문,3단~5단 -->
		${i}단<br/>
		<c:forEach var="j" begin="1" end="9"> <!-- 안쪽 for문 -->
			${i} * ${j} = ${i * j}<br/>
		</c:forEach>
		=====================<br/>
	</c:forEach>
</div>
<p><br/></p>
</body>
</html>