<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%
	int level = session.getAttribute("sLevel")==null? 99 : (int) session.getAttribute("sLevel"); //sLevel이 null이면 4번보다큰거줌(99로)
	pageContext.setAttribute("level", level);
%>
<script>
	'use strict';
	function memberDel() {
		let ans = confirm("정말로 탈퇴하시겠습니까?");
		if (ans) {
			ans = confirm("탈퇴후 1개월간은 같은 아이디로 가입하실수 없습니다.\n탈퇴 하시겠습니까?"); //두번씩이나 물어보는거임
			if(ans) location.href="${ctp}/memDelete.mem";
		}
		
	}
</script>

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
	        <a class="nav-link" href="${ctp}/boList.bo">BOARD</a>
	      </li>
	      <c:if test="${level != 1}"> <!-- level이 1이아닐경우만 실행  -->
		      <li class="nav-item">
		        <a class="nav-link" href="#">PDS</a>
		      </li>    
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">STUDY</a>
		        <div class="dropdown-menu">
				      <a class="dropdown-item" href="${ctp}/pass.st">비밀번호암호화</a>
				      <a class="dropdown-item" href="${ctp}/ajax1.st">AJax연습1</a> <!-- 디렉토리패턴 -->
				      <a class="dropdown-item" href="${ctp}/userList.st">AJax연습2</a> 
				      <a class="dropdown-item-text" href="#">Text Link</a>
				    </div>
		      </li>
	      </c:if>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">My Page</a>
		        <div class="dropdown-menu">
				      <a class="dropdown-item" href="${ctp}/memMain.mem">회원방</a>
				      <c:if test="${level != 1}"><a class="dropdown-item" href="${ctp}/memList.mem">회원리스트</a></c:if> <!-- level1은 회원리스트안보이게함 -->
				      <a class="dropdown-item" href="${ctp}/memUpdatePwd.mem">회원비밀번호변경</a>
				      <a class="dropdown-item" href="${ctp}/memPwdCheck.mem">회원정보변경</a>
				      <a class="dropdown-item" href="javascript:memberDel()">회원탈퇴</a> <!-- 회원탈퇴신청을 받는거지,바로 DB까지 삭제처리하는게아님 -->
				      <c:if test="${level == 0}"><a class="dropdown-item" href="${ctp}/adMain.ad">관리자</a></c:if>
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