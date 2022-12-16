<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>upLoad1.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		 
		function fCheck()	{
			let fName = $("#file").val();
			let ext = fName.substring(fName.lastIndexOf(".")+1); //확장자 발췌
			let uExt = ext.toUpperCase();//대문자로변환
			let maxSize = 1024 * 1024 * 10; //업로드 가능한 최대파일의 용량은 10MByte로한다
			
			if(fName.trim() == "") {
				alert("업로드할 파일을 선택하세요");
				return false;
			}
			//파일사이즈 체크
			let fileSize = document.getElementById("file").files[0].size; //.files[0].size : 자바스크립트 명령어, 올려지는파일이 여러개라고 가정했을때 첫번째들어오는 파일이 0번째
			
			//확장자 체크. return이라 다시 if로체크			
			if (uExt != "JPG" && uExt != "GIF" && uExt != "PNG" && uExt != "ZIP" && uExt != "HWP" && uExt != "PPT" && uExt != "PPTX" ) {//실행용화일은 전송하지못하게함( =>내가 필요한 화일(여기쓴것들)만 전송하게함,바이러스때문에)
				alert("업로드 가능한 파일은 'JPG/GIF/PNG/ZIP/HWP/PPT/PPTX' 입니다"); //여기쓰여있는 7가지만 전송가능함
			// return false;
			}
			else if(fileSize > maxSize) {
				alert("업로드할 파일의 최대용량은 10MByte 입니다.");
			}
			else {
				myform.submit();
			}
			
			//alert("파일전송중!!")
			myform.submit();
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>파일 업로드 테스트 1(싱글파일처리)</h2>
	<p>cos라이브러리를 이용한 파일 업로드</p>
	<hr/>
	<form name="myform" method="post" action="${ctp}/upLoad1Ok.st" enctype="multipart/form-data"> <!-- multipart방식을 form안에 데이터가 문자만전송하는게아니라 파일을 전송하면 무조건 이 방식사용, -->
		<div>
		파일명 :
			<input type="file" name="fName" id="file" class="form-control-file border mb-2"/> <!-- -file border: 파일선택버튼 딱맞게 -->
			<input type="button" value="전송" onclick="fCheck()" class="btn btn-primary form-control"/>
		</div>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>