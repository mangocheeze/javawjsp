<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>message.jsp</title> <!-- 메세지만 관리하는 역할 -->
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';

		//변수에 담아서 뿌림
		let msg = "${msg}";  <%-- EL로 적어줌(서버프로그램이 먼저실행해야되니까 표현식으로 적어줘야했는데 여기선 jstl사용안해서 EL로도가능) --%>
		let url = "${url}";
		
		if(msg == "guInputOk") msg ="방명록에 글이 등록되었습니다.";
		else if(msg == "guInputNo") msg ="방명록에 글 등록이 실패했습니다!!.";
		else if(msg == "adminLoginOk") msg ="관리자 인증 성공 !";
		else if(msg == "adminLoginNo") msg ="관리자 인증 실패 !";
		else if(msg == "adminLogoutOk") msg ="관리자님 로그아웃 되셨습니다.";
		else if(msg == "guDeleteOk") msg ="방명록의 글이 삭제 되었습니다.";
		else if(msg == "guDeleteNo") msg ="방명록의 글을 삭제하는데 실패했습니다!";
		
		
		
		//이 두개를 서버에서 클라이언트에게 넘기는거임 (두개가한쌍)
		alert(msg);
		if(url != "") location.href = url; // 메세지뜬다음 여기로감 , url이 비어있지않을때만 
		
	</script>
</body>
</html>