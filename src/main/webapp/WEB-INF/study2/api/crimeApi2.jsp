<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>crimeApi.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		const API_KEY = 'v19psdWSqR4DKdaBdr56XY9CkCMx5Y6sSAz8FhKX%2BTtOCPL4ZLQUbyjGdOI%2Bwn0U5EZXgHVLbuAWLm7DJQMtfw%3D%3D';  //인증키. 마이페이지-데이터들어가기-일반인증키(encoding)를 인증키설정하기
	
		//외부 데이터를 가져오는 방식? jQuery 방식 / fetch 방식
		
		async function getCrimeData()	{
	    	let url = "https://api.odcloud.kr/api/15084592/v1/uddi:21ec6fa1-a033-413b-b049-8433f5b446ff"; 
	    	//대부분 endPoint임 -여긴 안돼서 api목록에서 년도별로 가져와서 썼음
			url += "?serviceKey="+API_KEY+"&page=1&perPage=100&returnType=json"; //100건보여달라는거임/위에다 쓰기 너무길어서 이렇게씀 /serviceKey :K대문자로
			
			//앞의 지정한 URL을 호출한다(전송시까지 기다리기위해 async명령 사용. await 명령사용시는 함수명 앞에 꼭 async 기술한다.)
			const response = await fetch(url); //await(패치명령어)을 적으면 무조건 함수앞에다가 async적어줘야함
			
			//응답받은 데이터를 json데이터 형식으로 변환하여 data변수에 담는다.
			const data = await response.json();
			
			console.log("data :" , data);  //콘솔에 찍어봄
			
			$("#demo").html(data); //데모에 찍어봄
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<div>
		<input type="button" value="강력범죄자료조회" onclick="getCrimeData()" class="btn btn-success"/>
	</div>
	<hr/>
	<div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>