<%@page import="java.util.ArrayList"%>
<%-- <%@ page import="study.database.*"%> 이렇게쓰면 database의 전체를 임포트건거임--%>
<%@page import="study.database.JusorokVO"%>
<%@page import="study.database.JusorokDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jstl3.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<%
	JusorokDAO dao = new JusorokDAO(); // database패키지에있는 dao가져옴
	
	ArrayList<JusorokVO> vos = dao.getMemberList();
	pageContext.setAttribute("vos", vos); //EL표기법쓰려면 저장소에담아야함 /안담으면 스크립틀릿으로써야함(<%)
	
%>
<div class="container">
	<h2>회원 전체 리스트1</h2>
	<table class="table table-hover">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>성명</th>
			<th>포인트</th>
			<th>최근방문일</th>
		</tr>
		<c:set var="cnt0" value="0"/>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${st.count}</td>
				<td>${vo.mid}</td>
				<td>${vo.pwd}</td>
				<td>${vo.name}</td>
				<td>${vo.point}</td>
				<td>${vo.lastDate}</td>
			</tr>
			<c:set var="cnt0" value="${cnt0 + 1}"/>
		</c:forEach>
		<tr><td colspan="6" class="m-0 p-0"></td></tr>
	</table>
	<p>전체 출력건수: <font color="blue"><b>${cnt0} 건</b></font></p>
	<hr/>
	
	
	<h4>등록된 회원중 홀수번째 가입한 회원만 출력하시오?(방법1)</h4>
	<h2>회원 전체 리스트2</h2>
	<table class="table table-hover">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>성명</th>
			<th>포인트</th>
			<th>최근방문일</th>
		</tr>
		<c:forEach var="vo" items="${vos}" step="2" varStatus="st"> <!-- step : 반복시 증가폭 -->
			<tr>
				<td>${st.count}</td>
				<td>${vo.mid}</td>
				<td>${vo.pwd}</td>
				<td>${vo.name}</td>
				<td>${vo.point}</td>
				<td>${vo.lastDate}</td>
			</tr>
			<c:set var="cnt" value="${st.count}"/>
		</c:forEach>
		<tr><td colspan="6" class="m-0 p-0"></td></tr>
	</table>
	<p>전체 출력건수(방법1): <font color="red"><b>${cnt} 건</b></font></p>
	<hr/>
	
	
	<h4>등록된 회원중 홀수번째 가입한 회원만 출력하시오?(방법2)</h4>
	<h2>회원 전체 리스트3</h2>
	<table class="table table-hover">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>성명</th>
			<th>포인트</th>
			<th>최근방문일</th>
		</tr>
		<!-- 누적해서 전체출력건수 출력 -->
		<c:set var="cnt2" value="0"/> <!-- i=0준거랑똑같다고생각 -->
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<c:if test="${st.count % 2 != 0}">
				<tr>
					<td>${st.count}</td>
					<td>${vo.mid}</td>
					<td>${vo.pwd}</td>
					<td>${vo.name}</td>
					<td>${vo.point}</td>
					<td>${vo.lastDate}</td>
				</tr>
				<c:set var="cnt2" value="${cnt2 + 1}"/> 
				<!-- i+1한거랑 똑같다고생각 (초기값 0줬으니까 1씩 증가해서 누적시킴) / if안에 넣어줘야하는이유 : if의 조건문이 포함될때만 누적해야되니까(홀수일때만 누적시켜야되니까)-->
			</c:if>
		</c:forEach>
		<tr><td colspan="6" class="m-0 p-0"></td></tr>
	</table>
	<p>전체 출력건수(방법2) : <font color="blue"><b>${cnt2} 건</b></font></p>
	<hr/>
</div>
<p><br/></p>
</body>
</html>