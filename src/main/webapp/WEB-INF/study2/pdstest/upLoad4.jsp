<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>upLoad4.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';

		//파일전송
		function fCheck()	{
			let fName = $("#fName").val();
			let maxSize = 1024 * 1024 * 20; //업로드 가능한 최대파일의 용량은 20MByte로한다
			
    	if(fName.trim() == "") {
    		alert("업로드할 파일을 선택하세요.");
    		return false;
    	}
			//파일의 길이체크 ,확장자체크, 파일사이즈체크
			let fileLength = document.getElementById("fName").files.length; //선택한 파일의 갯수
			let fileSize = 0; 
			for(let i=0; i<fileLength; i++) { //파일의 갯수만큼 돌릴거임
				fName = document.getElementById("fName").files[i].name; //이름 ,이걸 해보라고했던거임
				let ext = fName.substring(fName.lastIndexOf(".")+1); //확장자 발췌
				let uExt1 = ext.toUpperCase();//확장자를 대문자로변환
				
				if(uExt1 != "JPG" && uExt1 != "GIF" && uExt1 != "PNG" && uExt1 != "ZIP" && uExt1 != "HWP" && uExt1 != "PPT" && uExt1 != "PPTX" ) {//실행용화일은 전송하지못하게함( =>내가 필요한 화일(여기쓴것들)만 전송하게함,바이러스때문에)
					alert("업로드 가능한 파일은 'JPG/GIF/PNG/ZIP/HWP/PPT/PPTX' 입니다"); //여기쓰여있는 7가지만 전송가능함
					return false;
				}
				fileSize += document.getElementById("fName").files[i].size;
			}
			
			if(fileSize > maxSize) {
				alert("업로드할 파일의 최대용량은 10MByte 입니다.");
			}
			else {
				myform.submit();
			}
			
}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>파일 업로드 테스트 4(멀티파일처리3)</h2>
	<p>cos라이브러리를 이용한 파일 업로드</p>
	<p><font color="blue">여러개의 파일을 선택할 경우에는 'Ctrl+클릭/Shift+클릭'하세요</font></p>
	<hr/>
	<form name="myform" method="post" action="${ctp}/upLoad2Ok.st" enctype="multipart/form-data">
		<div>
			<input type="file" name="fName" id="fName" class="form-control-file border" multiple/> <!-- multiple:사진 여러개 선택가능 -->
		</div>
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-primary form-control"/>
		<input type="hidden" name="upLoadFlag" value="4"/> <!-- 4번넘어온애는 스위치로 upLoad4.jsp로보내게할거임 -->
	</form>
	<hr/><br/>
	<input type="button" value="다운로드폼으로" onclick="location.href='${ctp}/downLoad.st';" class="btn btn-info form-control"/><!-- 하나만할거임(다운로드폼으로 가게함) -->
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>