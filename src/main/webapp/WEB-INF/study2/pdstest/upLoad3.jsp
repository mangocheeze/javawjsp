<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>upLoad3.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		let cnt = 1;
		
		//파일박스 추가
		function fileBoxAppend() {
			cnt++;
			let fileBox = ""; //누적해야되니까 
			fileBox += '<div id="fBox'+cnt+'">'; //fBox 1, fBox2,...
    	fileBox += '<input type="file" name="fName'+cnt+'" id="file'+cnt+'" class="form-control-file border mb-2" style="float:left; width:85%"/>'; //float:left : 왼쪽으로배치
    	fileBox += '<input type="button" value="삭제" onclick="deleteBox('+cnt+')" class="btn btn-danger form-control ml-2 mb-2" style="width:10%"/>';
    	fileBox += '</div>';
    	$("#fileBoxAppend").append(fileBox); //파일박스 추가누르면 나오는곳
		}
		
		//파일박스추가후 삭제
		function deleteBox(cnt) { //cnt받음
			$("#fBox"+cnt).remove();
		}
		
		
		//파일전송
		function fCheck()	{
			let fName1 = $("#file1").val();
			let ext = fName1.substring(fName1.lastIndexOf(".")+1); //확장자 발췌
			let uExt1 = ext.toUpperCase();//대문자로변환
			let maxSize = 1024 * 1024 * 10; //업로드 가능한 최대파일의 용량은 10MByte로한다
			
			if(fName1.trim() == "") {
				alert("업로드할 파일을 선택하세요");
				return false;
			}
			//파일사이즈 체크 (1번만함 2,3은 나중에 반복문으로하기)
			let fileSize1 = document.getElementById("file1").files[0].size; //.files[0].size : 자바스크립트 명령어, 올려지는파일이 여러개라고 가정했을때 첫번째들어오는 파일이 0번째
			
			//확장자 체크. return이라 다시 if로체크			
			if (uExt1 != "JPG" && uExt1 != "GIF" && uExt1 != "PNG" && uExt1 != "ZIP" && uExt1 != "HWP" && uExt1 != "PPT" && uExt1 != "PPTX" ) {//실행용화일은 전송하지못하게함( =>내가 필요한 화일(여기쓴것들)만 전송하게함,바이러스때문에)
				alert("업로드 가능한 파일은 'JPG/GIF/PNG/ZIP/HWP/PPT/PPTX' 입니다"); //여기쓰여있는 7가지만 전송가능함
			// return false;
			}
			else if(fileSize1 > maxSize) {
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
	<h2>파일 업로드 테스트 3(멀티파일처리2)</h2>
	<p>cos라이브러리를 이용한 파일 업로드</p>
	<hr/>
	<form name="myform" method="post" action="${ctp}/upLoad2Ok.st" enctype="multipart/form-data"> <!-- enctype을 안쓰면 url방식 쓰면 multipart방식을 form안에 데이터가 문자만전송하는게아니라 파일을 전송하면 무조건 이 방식사용, -->
		<div>
			<input type="button" value="파일박스추가" onclick="fileBoxAppend()" class="btn btn-info btn-sm mb-2"/>
			<input type="file" name="fName1" id="file1" class="form-control-file border mb-2"/> <!-- -file border: 파일선택버튼 딱맞게 -->
		</div>
		<div id="fileBoxAppend"></div> <!-- 박스추가 -->
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-primary form-control"/>
		<input type="hidden" name="upLoadFlag" value="3"/> <!-- 3번넘어온애는 스위치로 upLoad3.jsp로보내게할거임 -->
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>