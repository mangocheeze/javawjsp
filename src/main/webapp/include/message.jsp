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
		let val = "${val}"; //~님 로그아웃되셨습니다하려고
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
		else if(msg == "loginOk") msg = val + "님 로그인 성공!!";
		else if(msg == "loginNo") msg ="로그인 실패~~";
		else if(msg == "memLogoutOk") msg = val + "님 로그아웃 되었습니다";
		else if(msg == "idCheckNo") msg = "중복된 아이디 입니다.";
		else if(msg == "nickCheckNo") msg = "중복된 닉네임 입니다.";
		else if(msg == "memJoinOk") msg = "회원가입 되셨습니다.";
		else if(msg == "memJoinNo") msg = "회원가입 실패~~";
		else if(msg == "passwordNo") msg = "기존비밀번호와 같지 않습니다";
		else if(msg == "passwordOk") msg = "비밀번호를 변경하였습니다!";
		else if(msg == "passwordOkNo") msg = "비밀번호변경실패~~";
		else if(msg == "passwordYes") msg = "회원정보 변경페이지로 이동합니다.";
		else if(msg == "memUpdateOk") msg = "회원정보가 변경되었습니다";
		else if(msg == "memUpdateNo") msg = "회원정보 변경실패~~";
		else if(msg == "levelCheckOk") msg = "회원 등급이 변경처리 되었습니다";
		else if(msg == "memberDelOk") msg = "정상적으로 회원탈퇴 되셨습니다.";
		else if(msg == "adMemberDelOk") msg = "탈퇴신청회원의 정보를 삭제처리했습니다";
		else if(msg == "boInputOk") msg = "게시글이 입력되었습니다.";
		else if(msg == "boInputNo") msg = "게시글이 입력취소~~~";
		else if(msg == "boDeleteOk") msg = "게시글이 삭제되었습니다!";
		else if(msg == "boDeleteNo") msg = "게시글 삭제실패~~~";
		else if(msg == "userCheckNo") msg = "정상적인 사용자가 아니시군요...혹시 해커??";
		else if(msg == "boUpdateOk") msg = "게시글이 수정되었습니다!";
		else if(msg == "boUpdateNo") msg = "게시글이 수정실패~~~";
		else if(msg == "upLoad1Ok") msg = "파일 업로드 성공!!";
		else if(msg == "upLoad1No") msg = "파일 업로드 실패~~";
		else if(msg == "boReplyDataOk") msg = "현재 게시글에 댓글이 존재하기에 현 게시글을 삭제할 수 없습니다.\n댓글을 삭제후 게시글을 삭제하세요.";
		else if(msg == "pdsInputOk") msg = "자료실에 파일이 업로드 되었습니다 !!";
		else if(msg == "pdsInputNo") msg = "자료실에 파일 업로드 실패~~";
		else if(msg == "pdsDeleteOk") msg = "자료실 삭제 완료!!";
		else if(msg == "pdsDeleteNo") msg = "자료실 삭제 실패~~";
		
		
		
		//이 두개를 서버에서 클라이언트에게 넘기는거임 (두개가한쌍)
		alert(msg);
		if(url != "") location.href = url; // 메세지뜬다음 여기로감 , url이 비어있지않을때만 
		
	</script>
</body>
</html>