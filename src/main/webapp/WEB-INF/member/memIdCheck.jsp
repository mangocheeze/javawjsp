<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memIdCheck.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
    'use strict';
    
    // 중복 아이디 재검색하기
    function idCheck() {
    	let mid = childForm.mid.value;
    	
    	if(mid.trim() == "") { //공백이라면
    		alert("아이디를 입력하세요!");
    		childForm.mid.focus();
    	}
    	else {
    		childForm.submit();
    	}
    }
    
    function sendCheck() {
    	opener.window.document.myform.mid.value = '${mid}'; //여기 폼에 썼던 아이디를 부모의 아이디입력칸에 보낼거야, ${mid} : 자식창(childform)에 입력한 mid
    	//window는 document의 상위객체임 ,써도되고 안써도되는거 / opener : 폼을가지는 상위객체를 불러오는 명령어(부모객체)
    	//opener.window.document.myform.mid.value : 부모창의 form에 mid의 값
    	
    	//opener.window.document.myform.mid.disabled = true;  에러
    	opener.window.document.myform.mid.readOnly = true;
    	opener.window.document.myform.pwd.focus(); //부모에있는 pwd에 포커스를둠
    	window.close(); //자식창 닫기
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>아이디 체크폼</h3>
  <c:if test="${res == 1}">
    <h4><font color="blue"><b>${mid}</b></font> 아이디는 사용 가능합니다.</h4>
    <p><input type="button" value="창닫기" onclick="sendCheck()"/></p> <!-- 창닫기를 누르면 sendCheck()실행 -->
  </c:if>
  <c:if test="${res != 1}">
    <h4><font color="blue"><b>${mid}</b></font> 아이디는 이미 사용중인 아이디입니다.</h4>
    <form name="childForm" method="post" action="${ctp}/memIdCheck.mem">
    	<p>
    	  <input type="text" name="mid"/>
    	  <input type="button" value="아이디재검색" onclick="idCheck()"/>
    	</p>
    </form>
  </c:if> 
</div>
</body>
</html>