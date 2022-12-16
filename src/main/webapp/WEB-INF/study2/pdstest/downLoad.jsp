<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>downLoad.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		
		function fileDelete(file) {
			let ans = confirm("선택한 파일을 삭제하시겠습니까?");
			if(!ans) return false;
			
			$.ajax({
				type : "post",
				url  : "${ctp}/fileDelete.st",
				data : {file : file},
				success : function(res) {
					if(res == "1") {
						alert("파일이 삭제되었습니다.");
					}
					else {
						alert("파일 삭제 실패~~");
					}
				},
				error : function() {
					alert("전송 실패~~");
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>서버에 저장된 파일 목록</h2>
	<p>${ctp}/data/pdstest/*.*</p>
	<hr/>
	<table class="table table-hover text-center">
		<tr>
			<th>번호</th>
			<th>파일명</th>
			<th>파일형식</th>
			<th>비고</th>
		</tr>
		<c:forEach var="file" items="${files}" varStatus="st">
			<tr>
				<td>${st.count}</td>
				<td>
					<a href="${ctp}/data/pdstest/${file}" download="${file}">${file}</a><br/>  <!-- 퍼블리셔 방식 ,download= :다운로드할수있게해줌,추가로 자바코드써줘야함(올린이름으로 다운로드가안되고 같은이름파일 두번올리면 두번째는 파일이름1로올라감-파일이름1로 다운이받아짐)  -->
					<a href="${ctp}/javaDown.st?file=${file}">자바다운로드</a> <!-- 옛날 백엔드방식 -->
				</td>
				<td>
					<c:set var="fNameArr" value="${fn:split(file,'.')}"/> <!-- .하나있다는가정하에 .을기준으로 분리함 , 파일형식(확장자) -->
					<c:set var="extName" value="${fn:toLowerCase(fNameArr[fn:length(fNameArr)-1])}"/>  <!--[fn:length(fNameArr)-1]:인덱스번호 / fNameArr 배열에 들어가있는 마지막꺼를 소문자로 꺼내려고함 -->
					<c:if test="${extName =='zip'}">압축파일</c:if> <!-- 확장자비교  -->
					<c:if test="${extName =='ppt' || extName == 'pptx'}">파워포인트파일</c:if> <!-- 확장자비교  -->
					<c:if test="${extName =='jpg' || extName == 'gif' || extName == 'png'}">
						<img src ="${ctp}/data/pdstest/${file}" width="150px" >
					</c:if> <!-- 확장자비교  -->
					: (${extName})
				</td>  
				<td>
					<a href="" class="btn btn-success btn-sm">다운로드</a> /
					<a href="javascript:fileDelete('${file}')" class="btn btn-danger btn-sm">삭제(ajax)</a> 
				</td>
			</tr>
		</c:forEach>
		<tr><td colspan="4" class="m-0 p-0"></td></tr>
	</table>
	<br/>
	<div>
		<a href = "${ctp}/upLoad4.st" class="btn btn-success form-control">돌아가기</a>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>