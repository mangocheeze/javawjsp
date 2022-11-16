<!-- 구매자와 상품분류를 등록후 필요한 만큼의 상품을 추가후 '상품명/가격/수량'을 등록후 등록된 제품과 
각각의 가격과 총가격을 출력하시오  -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>hw.jsp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script>
  	'use strict';
  	
  	let cnt = 0;
  	
  	//상품추가
  	$(function(){  		
	  	$('.addProduct').click(function(){
	  		cnt++;
	  		$('#demo').append('<div id="info'+cnt+'"> 상품명<input type="text" name="pName" id="pName'+cnt+'" required /> 가격 <input type="number" name="pPrice" id="pPrice'+cnt+'" required /> 수량<input type="number" name="pNum" id="pNum'+cnt+'" required /> <input type="button" value="삭제" onclick="dCheck('+cnt+')"></div>') //demo에 추가
	  	});
  	});
  	
  	//삭제처리
  	function dCheck(cnt) {
 			let info = "info" + cnt;
 			let num = document.getElementById(info);
 			num.parentNode.removeChild(num);
  	}
  	
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>상품 등록하기</h2>
	<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_hwOk">
		<p>구매자 <input type="text" name="consumer" id="consumer" autofocus required/></p>
		<div>
		상품분류
		<select name="menu" id="menu">
			<option value="아우터">아우터</option>
			<option value="상의">상의</option>
			<option value="바지">바지</option>
			<option value="원피스">원피스</option>
			<option value="스커트">스커트</option>
		</select>
		</div>
		<br/>
		<p><input type="button" value="상품추가하기" id="addProduct" class="btn btn-primary addProduct"/></p>
		<div id="demo"></div>
		<input type="submit" value="상품등록" id="productReg" class= "btn btn-success"/>
	</form>
</div>
<p><br/></p>
</body>
</html>