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
		//"${url}" = 'http://'; 이렇게 스크립트를 서버변수에는 못담음(서버에 값이 이미 소멸돼서?)
		let msg = "${msg}";  
		let url = "${url}"; 
		// EL로 적어줌(서버프로그램이 먼저실행해야되니까 표현식으로 적어줘야했는데 여기선 jstl사용안해서 EL로도가능) 
		// 서블릿에서 보내는변수는 서버변수임 서버를 자바스크립트 변수에 담는건 가능	
		/*서버값: 저장소에 담아서 가져오는거, 서버의 값은 영원한게 아님, 계속바뀔수있음 그래서 서버의 값을 스크립트변수에 담는건가능하지만
			스크립트변수를 계속변할수도있는 서버변수에 담을수는 없음 서버값은 마지막으로 저장소에 담은값
		*/
		
		
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