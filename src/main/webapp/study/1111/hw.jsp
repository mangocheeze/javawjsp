<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test11.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script>
    'use strict';
		
		function fCheck() {
			let nameCheck =/[a-zA-Z가-힣]{2,20}/g;
			let midCheck = /[a-zA-Z0-9_]/g; 
			let phoneCheck =/[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/g;
			let emailCheck =/[a-zA-Z0-9_]+@[a-zA-Z0-9]+\.[a-zA-Z]+/g;
			
			
			let name = myform.name.value;
			let age = myform.age.value;
			let birthday = myform.birthday.value;
			let mid = myform.mid.value;
			let pwd = myform.pwd.value;
			let phone = myform.phone.value;
			let email = myform.email.value;
			let gender = myform.gender.value;
			let hobby = myform.hobby.value;
			
			
			if(name == "") {
				alert("성명을 입력하세요?");
				myform.name.focus();
				return false;
			}
	    else if(!name.match(nameCheck)){
	        alert("성명은 한글또는 영문으로만 2~20자 이내작성 가능합니다");
	        return false;
	      }
	    else if(age == ""){
	    	alert("나이를 입력하세요")
	    	myform.age.focus();
				return false;
	    }
	    else if(birthday == ""){
	    	alert("생년월일을 입력하세요")
	    	myform.birthday.focus();
				return false;
	    }
	    else if(phone == ""){
	    	alert("전화번호를 입력하세요")
	    	myform.phone.focus();
				return false;
	    }
	    else if(!phone.match(phoneCheck)){
	      alert("전화번호는 형식에 맞게 작성해주세요");
	      return false;
	    }
	    else if(email == ""){
	    	alert("이메일을 입력하세요")
	    	myform.email.focus();
				return false;
	    }
	    else if(!email.match(emailCheck)){
	      alert("이메일형식이 맞지 않습니다");
	      return false;
	    }
	    else if(mid == ""){
	    	alert("아이디를 입력하세요")
	    	myform.mid.focus();
				return false;
	    }
	    else if(!mid.match(midCheck)){
	      alert("아이디는 영문,숫자,밑줄만 사용가능합니다");
	      return false;
	    }
	    else if(pwd == ""){
	    	alert("비밀번호를 입력하세요")
	    	myform.pwd.focus();
				return false;
	    }
			else {
				myform.submit();
			}		
		}	
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원가입폼 전송하기</h2>
	<form name="myform" method="post" action="hwOk.jsp">
	<!-- <form name="myform" method="post" action="../../hwOk"> -->
		성명: <input type="text" name="name" placeholder="성명을 입력하세요" class="form-control"/>
		나이: <input type="number" name="age" class="form-control"/>
		생년월일: <input type="date" name="birthday" class="form-control"/>
		전화번호: <input type="text" name="phone" placeholder="010-0000-0000" class="form-control"/>
		이메일: <input type="email" name="email" placeholder="asdf@naver.com" class="form-control"/>
		아이디: <input type="text" name="mid" placeholder="아이디를 입력하세요" class="form-control"/>
		비밀번호: <input type="password" name="pwd" placeholder="비밀번호를 입력하세요" class="form-control"/>
		<div>
		성별: <input type="radio" name="gender" value="남자" checked />남자
				 <input type="radio" name="gender" value="여자" />여자
		</div>
		<div>
		취미:
			<input type="checkbox" name="hobby" value="미술" checked>미술
			<input type="checkbox" name="hobby" value="요리">요리
			<input type="checkbox" name="hobby" value="등산">등산
			<input type="checkbox" name="hobby" value="독서">독서
			<input type="checkbox" name="hobby" value="음악감상">음악감상
			<input type="checkbox" name="hobby" value="기타">기타
		</div>
		<div>
			<input type="reset" value="다시입력" class="btn btn-danger"/>&nbsp
			<input type="button" value="전송" onclick= "fCheck()" class="btn btn-success"/> 
		</div>
	</form>
</div>
<p><br/></p>
</body>
</html>