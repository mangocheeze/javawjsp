<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>userList.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<script>
	function userSearch(mid) { //개별조회
		$.ajax({ //$.ajax({}) : jQuery함수 (외워야함!!)
			type : "post",
			url  : "${ctp}/userSearch.st",
			data : {mid : mid},
			//----위에 까지가 서버에 전송 아래부턴 서버에서 돌아오는부분
			success : function(res) { //서버에서 준 결과를 res라는 변수에 담음, 성공적으로 처리했을경우에만 수행
				//폼에 뿌려줌
  			let str = res.split("/"); // 결과를 슬래시를 기준으로 방을나눔
  			$("#mid").val(str[1]); //0번방인 인덱스 번호는 폼에 없으니까 1번방부터
  			$("#name").val(str[2]);
  			$("#age").val(str[3]);
  			$("#address").val(str[4]);
			},
			error : function() { //실패했을경우 수행
				alert("전송실패~~")
			}
		});  
	}
	function userDel(mid) { //삭제
  	let ans = confirm("선택된 회원을 삭제처리 하시겠습니까?");
  	if(!ans) return false;
  	
  	
		$.ajax({ 
			type : "post",
			url  : "${ctp}/userDel.st",
			data : {mid : mid},
			//----위에 까지가 서버에 전송 아래부턴 서버에서 돌아오는부분
			success : function(res) { //서버에서 준 결과를 res변수에 담음,성공적으로 처리했을경우에만 수행
				if(res == "1"){
					alert("삭제완료!!");
					location.reload(); //reload : 새로고침
				}
				else {
					alert("삭제실패~~")
				}
			},
			error : function() { //실패했을경우 수행
				alert("전송실패~~")
			}
		});  
	}
/* 	
    function userInput() { //유저등록
    	let mid = document.getElementById("mid").value.tirm();
    	let name = document.getElementById("name").value.tirm();
    	let age = document.getElementById("age").value.tirm();
    	let address = document.getElementById("address").value.tirm();
    	
    	if(mid == "" || name == "" || age == "" || address) {
    		alert("텍스트박스의 내용을 모두 입력하신후 전송버튼을 클릭해 주세요~");
    		document.getElementById("mid").focus();
    		return false;
    	}
    	
    	let query = {
    			mid  : mid,
    			name : name,
    			age  : age,
    			address : address
    	}
    	
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/userInput.st",
    		data  : query,
    		success:function(res) {
    			if(res == "1") {
    				alert("등록되었습니다.");
    				location.reload();
    			}
    			else alert("등록 실패~~");
    		},
    		error : function() {
    			alert("전송 오류~~");
    		}
    	});
    } */
</script>
<p><br/></p>
<div class="container">
	<h2>AJax 연습</h2>
	<hr/>
	<form>
		<table class="table table-bordered">
			<tr>
				<td colspan="2" class="text-center"><h3>User '가입/수정' 하기 폼</h3></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="mid" id="mid" class="form-control"/></td>
			</tr>
			<tr>
				<th>성명</th>
				<td><input type="text" name="name" id="name" class="form-control"/></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age" id="age" class="form-control"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" id="address" class="form-control"/></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="button" value="유저등록" onclick="userInput()" class="btn btn-success"/> <!-- ajax는 type submit안됨 -->
					<input type="reset" value="다시입력" class="btn btn-info"/>
					<input type="button" value="User수정" onclick="userUpdate()" class="btn btn-warning"/>
					<input type="button" value="전체보기" onclick="location.href='${ctp}/userList.st';" class="btn btn-primary"/>
					
			</tr>			
		</table>
	</form>
	<hr/>
	<h3>전체리스트</h3>
	<br/>
	<table class="table table-hover text-center">
		<tr class ="table-dark text-dark">
			<th>번호</th><th>아이디</th><th>성명</th><th>나이</th><th>주소</th><th>비고</th>
		</tr>
		<c:forEach var="vo" items="${vos}"> <!-- var :사용할 변수명 , items : 배열명 -->
			<tr>
				<td>${vo.idx}</td>
				<td>${vo.mid}</td>
				<td>${vo.name}</td>
				<td>${vo.age}</td>
				<td>${vo.address}</td>
				<td>
					<a href="javascript:userSearch('${vo.mid}')" class="btn btn-success">개별조회</a> <!-- javascript:userSearch=> 자바스크립트 함수가 실행됨 ,문자니까 '${vo.mid}' -->
					<a href="javascript:userDel('${vo.mid}')" class="btn btn-danger">삭제</a>
				</td>
			</tr>
		</c:forEach>
		<tr><td colspan="6" class="m-0 p-0"></td></tr>
	</table>
	
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>