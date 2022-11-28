<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int level = session.getAttribute("sLevel")==null? 99 : (int) session.getAttribute("sLevel"); //sLevel이 null이면 4번보다큰거줌(99로)
	pageContext.setAttribute("level", level);
%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="http://192.168.50.210:9090/javawjsp/">Home</a> <!-- ip로 한이유: home누르면 ip로 들어감 ,localhost:9090/javawjsp 로하면 내컴퓨터에서만 접속가능해서안씀 -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${ctp}/guest/guList.gu">GUEST</a>  <!-- 메뉴에서 Guest를 누르면 컨트롤러로 가서 guList에걸려서 guList.jsp로가게함 -->
      </li>
      <c:if test="${level <= 4}"> <!-- level이 4이하일때만 실행(메뉴가 보이게함) -->
	      <li class="nav-item">
	        <a class="nav-link" href="#">BOARD</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">PDS</a> <!-- 자료실 -->
	      </li>    
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">STUDY</a>  <!-- #으로둠:다른데로 이동하면안되니까 /dropdown :메뉴클릭하면 부메뉴나오게함 -->
	      	<div class="dropdown-menu">
			      <a class="dropdown-item" href="${ctp}/pass.st">비밀번호암호화</a>
			      <a class="dropdown-item" href="#">Link 2</a>
			      <a class="dropdown-item-text" href="#">Text Link</a>
			    </div>
	      </li>
      </c:if>  
      <li class="nav-item">
      	<c:if test="${level > 4}"><a class="nav-link" href="${ctp}/memLogin.mem">Login</a></c:if>
      	<c:if test="${level <= 4}"><a class="nav-link" href="${ctp}/memLogout.mem">Logout</a></c:if>
      </li>    
      <li class="nav-item">
        <c:if test="${level > 4}"><a class="nav-link" href="${ctp}/memJoin.mem">Join</a></c:if>
      </li>    
    </ul>
  </div>  
</nav>