<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			let year = document.getElementById("year").value;
			let apiYear = "";
			
			if(year == 2015) apiYear = "uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			else if(year == 2016) apiYear = "uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			else if(year == 2017) apiYear = "uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			else if(year == 2018) apiYear = "uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			else if(year == 2019) apiYear = "uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			else if(year == 2020) apiYear = "uddi:fdde1218-987c-49ba-9326-8e3ba276141e";
			
	    let url = "https://api.odcloud.kr/api/15084592/v1/" + apiYear; 
	    	//대부분 endPoint임 -여긴 안돼서 api목록에서 년도별로 변수로 담아서 가져와서 썼음
			url += "?serviceKey="+API_KEY+"&page=1&perPage=100&returnType=json"; //데이터100건보여달라는거임/위에다 쓰기 너무길어서 이렇게씀 /serviceKey :K대문자로
			
			//앞의 지정한 URL을 호출한다(전송시까지 기다리기위해 async명령 사용. await 명령사용시는 함수명 앞에 꼭 async 기술한다.)
			const response = await fetch(url); //await(패치명령어)을 적으면 무조건 함수앞에다가 async적어줘야함
			
			//응답받은 데이터를 json데이터 형식으로 변환하여 data변수에 담는다.
			const res = await response.json(); //res는 변수이름 , await:전송시까지 잠시 기다려달라(대기하라), json으로 변환할때도 기다리게해야함
			
			console.log("res :" , res);  //콘솔에 찍어봄
			
			// json객체 타입을 html에 출력시켜주기위해 문자열형식으로 변경한다
			//let str = JSON.stringify(res);
			
			
/* 		//데이터형식을 배열로바꿈	 
			let str = "";
			for(let i=0; i<res.data.length; i++) {
				str += "경찰서 :" +res.data[i].경찰서 + "<br/>"; //필드명 경찰서를 출력시킬거임 
			}  */
			
			//맵형식으로 바꿈 ,arrow펑션 사용
 			let str = res.data.map((item,i) => [
 				(i+1) + ". "
				+ "발생년도:" + item.발생년도 + "년"
				+ ",경찰서:" + item.경찰서 + "건"
				+ ",강도:" + item.강도 + "건"
				+ ",살인:" + item.살인 + "건"
				+ ",절도:" + item.절도 + "건"
				+ ",폭력:" + item.폭력 + "건"
				+ "<br/>"
			]); //데이터(배열변수) 밑에있는 데이터안에 맵에서  /item에서 경찰서 필드를 가져와라
			
			
			//데모창
			$("#demo").html(str); //데모에 찍어봄 
		}
		
		
		//데이터베이스에 저장하기
		async function saveCrimeData() {
			let year = document.getElementById("year").value;
			let apiYear = "";
			
			if(year == 2015) apiYear = "uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			else if(year == 2016) apiYear = "uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			else if(year == 2017) apiYear = "uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			else if(year == 2018) apiYear = "uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			else if(year == 2019) apiYear = "uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			else if(year == 2020) apiYear = "uddi:fdde1218-987c-49ba-9326-8e3ba276141e";
			
	    let url = "https://api.odcloud.kr/api/15084592/v1/" + apiYear; 
	    	//대부분 endPoint임 -여긴 안돼서 api목록에서 년도별로 변수로 담아서 가져와서 썼음
			url += "?serviceKey="+API_KEY+"&page=1&perPage=100&returnType=json"; //데이터100건보여달라는거임/위에다 쓰기 너무길어서 이렇게씀 /serviceKey :K대문자로
			
			//앞의 지정한 URL을 호출한다(전송시까지 기다리기위해 async명령 사용. await 명령사용시는 함수명 앞에 꼭 async 기술한다.)
			const response = await fetch(url); //await(패치명령어)을 적으면 무조건 함수앞에다가 async적어줘야함
			
			//응답받은 데이터를 json데이터 형식으로 변환하여 data변수에 담는다.
			const res = await response.json(); //res는 변수이름 , await:전송시까지 잠시 기다려달라(대기하라), json으로 변환할때도 기다리게해야함
		
			let query = "";
			for(let i=0; i<res.data.length; i++) {
				if(res.data[i].경찰서 != null) {
					query = {
							year : year,
							police : res.data[i].경찰서,
							burglar : res.data[i].강도,
							murder : res.data[i].살인,
							theft : res.data[i].절도,
							violence : res.data[i].폭력
					}
					
					$.ajax({
						type : "post",
						url  : "${ctp}/stCrimeSava.st",
						data : query,
						error : function() {
							alert("전송오류");
						}
					});
				}
			}
			alert(year + "년도 자료가 DB에 저장되었습니다.");
		}
			
			
    // 데이터베이스의 범죄 자료 삭제하기
    function deleteCrimeData() {
    	let ans = confirm("DB의 자료를 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		type   : "post",
    		url    : "${ctp}/stCrimeDelete.st",
    		success:function(res) {
    			if(res == "1") {
    				alert("삭제완료!");
    				location.reload();
    			}
    			else {
    				alert("삭제 실패~~");
    			}
    		},
    		error : function() {
    			alert("전송 오류!");
    		}
    	});
    }
		
		//DB에 저장된 범죄 통계자료 출력하기
		function listCrimeData() {
			
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<div>
		<select name="year" id="year">
			<c:forEach var="i" begin="2015" end="2020">
				<option value="${i}">${i}</option>
			</c:forEach>	
		</select>
		<input type="button" value="강력범죄자료조회" onclick="getCrimeData()" class="btn btn-success"/>
		<input type="button" value="DB저장" onclick="saveCrimeData()" class="btn btn-primary"/>
		<input type="button" value="DB삭제" onclick="deleteCrimeData()" class="btn btn-secondary"/>
		<input type="button" value="DB출력" onclick="location.href='${ctp}/stCrimeList.st';" class="btn btn-secondary"/> <!-- 바로보냄 -->
		<input type="button" value="DB분석" onclick="location.href='${ctp}/stCrimeTotalList.st';" class="btn btn-warning"/>
	</div>
	<hr/>
	<div id="demo"></div>
	<hr/>
	<c:if test="${!empty vos}">
		<h3 class="text-center">범죄 통계현황(총 : ${crimeCount}건)</h3>
		<table class="table table-hover">
			<tr class="table table-dark text-dark">
				<th>번호</th>
				<th>발생년도</th>
				<th>관할 경찰서</th>
				<th>강도</th>
				<th>살인</th>
				<th>절도</th>
				<th>폭력</th>
			</tr>
			<c:forEach var='vo' items="${vos}" varStatus="st">
        <tr>
          <td>${st.count}</td>
          <td>${vo.year}</td>
          <td>${vo.police}</td>
          <td><fmt:formatNumber value="${vo.burglar}"/></td>
          <td><fmt:formatNumber value="${vo.murder}"/></td>
          <td><fmt:formatNumber value="${vo.theft}"/></td>
          <td><fmt:formatNumber value="${vo.violence}"/></td>
        </tr>
			</c:forEach>
			<tr><td colspan = "7" class="m-0 p-0"></td></tr>
		</table>
	</c:if><!-- vo가 있으면 출력 -->
	<c:if test ="${!empty totalVos}">
<h3 class="text-center">범죄 분석 현황</h3>
		<table class="table table-hover">
			<tr class="table table-dark text-dark">
				<th>구분</th>
				<th>발생년도</th>
				<th>강도</th> <!-- 년도별  -->
				<th>살인</th>
				<th>절도</th>
				<th>폭력</th>
			</tr>
			<c:forEach var='vo' items="${vos}" varStatus="st">
        <tr>
          <td>${vo.part}</td>
          <td>${vo.year}</td>
          <td><fmt:formatNumber value="${vo.totBurglar}"/></td>
          <td><fmt:formatNumber value="${vo.totMurder}"/></td>
          <td><fmt:formatNumber value="${vo.totTheft}"/></td>
          <td><fmt:formatNumber value="${vo.totViolence}"/></td>
        </tr>
      </c:forEach>
      <tr><td colspan="6"><hr/></td></tr>
      <c:forEach var="vo" items="${avgVos}" varStatus="st">
        <tr>
          <td>${vo.part}</td>
          <td>${vo.year}</td>
          <td><fmt:formatNumber value="${vo.totBurglar}"/></td>
          <td><fmt:formatNumber value="${vo.totMurder}"/></td>
          <td><fmt:formatNumber value="${vo.totTheft}"/></td>
          <td><fmt:formatNumber value="${vo.totViolence}"/></td>
        </tr>
      </c:forEach>
      <tr><td colspan="7" class="m-0 p-0"></td></tr>
    </table>
	</c:if>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>