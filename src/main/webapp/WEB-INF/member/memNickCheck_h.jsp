<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memNickCheck.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<!-- <script>
		'use strict';
		
		//중복 닉네임 재검색하기
		function nickCheck() {
			let nickName = childForm.nickName.value;
			
			if(nickName.trim() == "") {
				alert("닉네임을 입력하세요!");
				childForm.nickName.focus();
			}
			else {
				childForm.submit();
			}
		}
		
		//부모창에 닉네임값 보내기
		function sendCheck() {
			opener.window.document.myform.nickName.value ='${nickName}'; //부모한테 담음
			//opener.window.document.myform.nickCheck.value = 1;
			
      //opener.window.document.getElementById("nickCheck_label").style.visibility = "hidden";
      opener.window.document.myform.name.focus();			
			window.close();
		}
		
	</script> -->
</head>
<body>
<p><br/></p>
<div class="container">
	<h3>닉네임 체크폼</h3>
	<c:if test="${res == 1}">
		<h4><font color="blue"><b>${nickName}</b></font> 닉네임은 사용 가능합니다.</h4>
		<p><input type="button" value="창닫기" onclick="sendCheck()"/></p>
	</c:if>
	<c:if test="${res != 1}">
		<h4><font color="blue"><b>${nickName}</b></font> 닉네임은 이미 사용중인 아이디입니다.</h4>
		<form name="childForm" method="post" action="${ctp}/memNickCheck.mem"> <!-- id체크해서 넘겨주는거 재사용 -->
			<p>
				<input type="text" name="nickName"/>
				<input type="button" value="닉네임재검색" onclick="nickCheck()"/>
			</p>			
		</form>
	</c:if>
</div>
</body>
</html>