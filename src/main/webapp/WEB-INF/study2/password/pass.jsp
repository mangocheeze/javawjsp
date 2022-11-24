<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>pass.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
	
	//폼안에있는내용이 같으니 action만 다르게해서 폼하나에 같이함
		'use strict';
		function fCheck(idx) {
			let pwd = myform.pwd.value; //myform에있는 pwd에있는 값을 pwd 변수에담음
			if(pwd.trim() == "") {
				alert("비밀번호를 입력하세요");
				myform.pwd.focus();
			}
			else{
				if(idx == 1) {
					myform.idx.value = "1"; //idx에 1을 폼에넣어서보냄 (post방식으로)
					myform.action = "${ctp}/passOk1.st"; //갈 주소를 적음 , 숫자처리하는애
				}
				else if(idx ==2){
					myform.idx.value = "2"; //idx에 2를 폼에넣어서보냄 (post방식으로)
					myform.action = "${ctp}/passOk1.st"; // 혼합 처리하는애
				}
				else {
					myform.action = "${ctp}/passOk2.st";
				}
				//document.getElementById("demo").innerHTML = "<font color =:'red'><b>콘솔창을 확인하세요..</b></font>";//데모를부름
				//document.getElementById("demo").innerText = "<a href='${ctp}/pass.st' class='btn btn-warning'>돌아가기</a>";//데모2를부름
				myform.submit();  //form을 전송시킨다는뜻
				
			}
			
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>비밀번호 암호화 연습</h2>
	<p>(비밀번호를 10자이내로 입력하세요)</p>
	<br/>
	<form name="myform" method="post"> 
		<table class="table table-bordered">
			<tr>
				<td>
					<p>아이디 : <input type="text" name="mid" value="hkd1234" class="form-control"/></p>
					<p>비밀번호 : <input type="password" name="pwd" maxlength="9" autofocus class="form-control" /></p>
					<p><input type="button" value="확인(숫자비밀번호)" onclick="fCheck(1)" class="form-control btn-secondary"/></p>
					<p><input type="button" value="확인(혼합비밀번호)" onclick="fCheck(2)" class="form-control btn-secondary"/></p>
					<p><input type="button" value="확인(SHA256)" onclick="fCheck(3)" class="form-control btn-secondary"/></p>
				</td>
			</tr>
		</table>
		<input type="hidden" name="idx" /> <!-- idx를 숨겨서 넘김 -->
	</form>
<!-- 	<div id ="demo"></div>
	<div id ="demo2"></div> -->
	<p>비밀번호를 전송후 콘솔창을 확인하세요</p>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>