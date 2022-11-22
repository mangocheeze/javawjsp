<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 이거 올려야함 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jstl5.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>Format 라이브러리</h2>
	<p>형식문자열을 지정할때 사용(쉼표,화폐단위,백분율..)</p>
	<p>사용법 : < fmt : 명령어 value="$ {값}" pattern="표현패턴" type="화폐단위"></p>
	<div>
		<h5>formatNumber 형식을 지정한 출력</h5>
		1-1.천단위마다 콤마표시 : <fmt:formatNumber value="1234567"/><br/>
<%
	int won = 7654321;
	pageContext.setAttribute("won", won);
%>
	<!-- won변수에 담아서 사용 -->
		1-2. 사용예 :7654321 => : <fmt:formatNumber value="${won}"/><br/>
		1-3. 패턴사용예 :1234.567 => : <fmt:formatNumber value="1234.567" pattern="#,##0.0"/><br/> <%-- 자동으로 반올림됨 --%>
		2-1. 화폐단위 : <fmt:formatNumber value="${won}" type="currency"/><br/> <%--지금 나는 한국꺼로 나옴--%> 
		2-2. 화폐단위(영문) : <fmt:formatNumber value="${won}" type="currency" currencyCode="USD"/><br/> <%--지금 나는 미국꺼로 나옴--%> 
		
		<c:set var="su1" value="0.9543"/>
		3. 백분율: <fmt:formatNumber value="${su1}" type="percent"/><br/>
		<br/>
		<h5>fomatDate 형식을 지정한 출력</h5>
		<p>자바형식의 날짜를 jstl변수에 저장 : <c:set var="now" value="<%=new java.util.Date() %>"/></p>  <!-- 자바객체를 jstl에 저장 -->
		<p>
			오늘날짜 : ${now}<br/>
			<fmt:formatDate value="${now}"/><br/>
			<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/><br/> <!-- 소문자m은 vo -->
			<fmt:formatDate value="${now}" pattern="hh : mm : ss"/><br/> 
			<fmt:formatDate value="${now}" pattern="yyyy-MM-hh:mm:ss"/><br/>
			<fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/><br/>
		</p>
		<hr/>
		<h3>국가별설정(로케일)</h3>
		<p>
			톰캣 서버의 기본 로케일 : <%=response.getLocale() %><br/>
			톰캣 서버의 기본 로케일(미국식변화) : <fmt:setLocale value="en_US"/>
			<fmt:formatNumber value="${won}" type="currency"/><br/> 
		</p>
		<h4>기타</h4>
		<p>지정된곳으로 이동 : location.href()</p>
<%-- 		<c:redirect url ="jstl1.jsp"/> --%>
		<p>절대경로 : 그림1 <img src="${ctp}/images/1.jpg" widdth="200px"/></p>
		<p>
			URL문 : 그림2 <c:url var="img" value="../../images/2.jpg"/><br/> <!-- 상대경로 -->
									<img src="${img}" width="200px"/>
	</div>
</div>
<p><br/></p>
</body>
</html>