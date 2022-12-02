<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ajax1.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		function idCheck() {
			//let mid = document.getElementById("mid").value; //id로가져오는방법
			//let mid = document.myform.mid.value; //form으로가져오는방법
			let mid = $("#mid").val();  //jQuery방법(지금까지 우리가 사용하던)
			
			if(mid.trim() == "") {
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			location.href = "${ctp}/idSearchTest?mid="+mid; //일반방식 ,디렉토리패턴 =>서블릿만들어야함,찾고자하는 id를 넘김(값을직접넘겨야함)
		}
		//------------------------------------------
		function idCheck2() {
			let mid = $("#mid").val();  //#mid = 폼 id가 mid
			
			if(mid.trim() == "") {
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			
			let query = { //query 변수명, 바음대로
					mid: mid
			}

			$.ajax({
				type : "get",  //get이나 post아무거나쓰기
				url : "${ctp}/idSearchTest2", //어디로 보낼건데  , "루트/컨트롤러"를 적어줌 ;이아니라 , 가 연결연산자
				//data : {mid : mid, idx : idx}, //{}로 묶어서 변수랑(서버에서넘겨주는변수) 값을 넣음(적게넣을때 이렇게사용) 
				data : query, //위에 방식말고 여러개 보낼땐(한개보낼때사용해도됨) 위에 let query = {} 로 써주고 여긴 변수명 query만 써주면됨
				
				/*contextType : "application/json",
				charset : "utf-8",*/  //window를 사용하면 생략가능
				
				/* ------이 위까지는 서버로 간거고 이 아래는 서버에서 갔다온거임 */
				
				success : function(res) {  //전송이 성곡적으로마쳤을때 실행 ()안에 받고자하는값 적어주면됨 ,기능으로 적어줌
	   			alert("서버에서 아이디검색을 성공적으로 마치고 돌아왔습니다. 검색된 성명은? " + res);
    			$("#demo").html(res);
				},
				error: function() { //문제가 있으면 여기서 처리
					alert("전송 실패~~~");
				//function{}안에는 자바스크립트명령사용
				}      
			});  
		}
		//---------------------------------------
	    function idCheck3() {
	    	let mid = $("#mid").val();
	    	
	    	if(mid.trim() == "") {
	    		alert("아이디를 입력하세요.");
	    		$("#mid").focus();
	    		return false;
	    	}
	    	
	    	//위에if가 정상적으로 처리됐으면 ajax하러옴
	    	$.ajax({
	    		type : "post",
	    		url  : "${ctp}/idSearchTest3",
	    		data : {mid: mid},  		 //서버로 전송할데이터
	    		success: function(res) { //변수 res맘대로줘도됨 /넘어온자료를 res로 담음
	    			$("#demo").html(res);   //demo에 출력
	    			let str = res.split("/"); // res에 담겨있는걸 슬래시(/)를 기준으로 방을 나눔
	    			$("#tMid").html(str[0]); // id tMid에 0번째방을 담음
	    			$("#name").html(str[1]); // id name에 1번째방을 담음
	    			$("#nickName").html(str[2]);
	    			$("#gender").html(str[3]);
	    			$("#point").html(str[4]);
	    		},
	    		error: function() {
	    			alert("전송 실패~~~");
	    		} 
	    	});
	    }
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2>AJax연습</h2>
	<hr/>
	<form name="myform">
		아이디 : <input type="text" name="id" id="mid" /> &nbsp;
    <input type="button" value="아이디일반검색1" onclick="idCheck()" class="btn btn-info"/> &nbsp;
    <input type="button" value="아이디검색2" onclick="idCheck2()" class="btn btn-info"/> &nbsp;
    <input type="button" value="아이디검색3" onclick="idCheck3()" class="btn btn-info"/> &nbsp;
		<br/>
		<div>출력결과 : <span id="demo">${name}</span></div>
		<hr/>
		<div>
			<div>아이디 : <span id="tMid"></span></div>  <!-- 위에서 mid써서 tMid로함 -->
			<div>성명 : <span id="name"></span></div>
			<div>닉네임 : <span id="nickName"></span></div>
			<div>성별 : <span id="gender"></span></div>
			<div>포인트 : <span id="point"></span></div>
		</div>
	</form>
	
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>