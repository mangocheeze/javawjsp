<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memJoin.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  <!-- API코드 -->
	<script src="${ctp}/js/woo.js"></script>  <!-- API코드파일 연결 -->
	<!-- <script>
		'use strict';
		
		const reg_name = /[a-zA-Z가-힣]{2,20}/g;
    const reg_mid = /^[a-z0-9_-]{4,20}$/; // 소문자 + 숫자 + 언더바/하이픈 허용 4~20자리
    const reg_pwd = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/; // 문자, 숫자 1개이상 포함, 8자리 이상
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; // 길이까지 확실한 검증
    const reg_tel = /^\d{2,3}-\d{3,4}-\d{4}$/; // 일반 전화 번호
    const reg_url = /^(https?:\/\/)?([a-z\d\.-]+)\.([a-z\.]{2,6})([\/\w\.-]*)*\/?$/; // URL 검사식
		
		//회원가입폼 체크후 서버로 전송(submit)
		
		function fCheck() {
			//폼의 유효성검사~~~
			
      let mid=document.getElementById("mid").value;
      let pwd=document.getElementById("pwd").value;
      let nickName=document.getElementById("nickName").value;
      let name=document.getElementById("name").value;
			
			
			//전화번호 하나로 묶어서 처리
			let tel1 = myform.tel1.value;
			let tel2 = myform.tel2.value;
			let tel3 = myform.tel3.value;
			
			
			//이메일을 하나로 묶어준다
			let email1 = myform.email1.value;
			let email2 = myform.email2.value;
			
			
			
			//전송전에 '주소'를 하나로 묶어서 전송처리
			let postcode = myform.postcode.value + " ";  //딱붙어나오는거 싫어서  + " ";
			let roadAddress = myform.roadAddress.value + " ";
			let detailAddress = myform.detailAddress.value + " ";
			let extraAddress = myform.extraAddress.value + " ";
			
			myform.address.value = postcode + "/" + roadAddress + "/" + detailAddress + "/" + extraAddress;
				
			if(mid.trim() == "") {
				alert("아이디를 입력해주세요");
				return;
			}
      else if(!mid.trim().match(reg_mid))   {
          alert("아이디는 영문 소문자 + 숫자 + 언더바/하이픈 허용 4~20자리만 가능합니다~");
          return;
      }
      if(pwd.trim()=="")  {
          alert("비밀번호를 입력해주세요!")
          return;
      }
      else if(!pwd.trim().match(reg_pwd))   {
          alert("비밀번호는 문자 및 숫자 1개이상 포함해야 하며  8자리 이상 입력해야 합니다.");
          return;
      }
      if(nickName.trim()=="")  {
          alert("닉네임을 입력해주세요!")
          return;
      }
      else if(!nickName.trim().match(reg_name))   {
          alert("닉네임은 한글 및 영문만 입력 가능합니다.");
          return;
      }
      if(name.trim()=="")  {
          alert("성명을 입력해주세요!")
          return;
      }
      else if(!name.trim().match(reg_name))   {
          alert("성명은 한글 및 영문만 2~20자 이내 입력 가능합니다.");
          return;
      }
      if(email1.trim()=="")  {
          alert("이메일을 입력해주세요!")
          return;
      }
      if(tel2.trim()==""||tel3.trim()=="")  {
          alert("전화번호를 입력해주세요!")
          return;
      }
      
      let email = email1 + "@" + email2;    
			let tel = tel1 + "-" + tel2 + "-" + tel3; //지역번호 - 전화번호 - 국번호,	

			myform.email.value = email;
			myform.tel.value = tel;
			
			
			
			//파일 전송전에 파일에 관한사항 체크..(파일명이 넘어올경우는 해당파일을 넘기고, 비었으면 'noimage.jpg'를 넣는다)
			let fName = myform.fName.value;
			let maxSize = 1024 * 1024 * 1;  //파일 최대사이즈 1MByte까지허용 , 1kbyte =2의 10승=1024 , 1mbyte = 2의 20승 =1024*1024
			let ext = fName.substring(fName.lastIndexOf(".")+1);//뒤에서부터 .만날때까지 그리고 거기서 +1한거부터 마지막까지를 잘라내 비교할거임(jpg만 잘라올거임)
			let uExt = ext.toUpperCase(); //위에서 잘라온걸 대문자로 바꿈(jpg랑 JPG둘다올수있어서 JPG로 통일시키려고)
			
			let submitFlag = 0; //스위치기법쓰려고...??????
			
			if(fName.trim() == "") { //공백이면
				myform.photo.value = "noimage"; //noimage.jpg로 안해도 됨(noimage란 단어가오면 noimage.jpg로 오게끔할거임)
				submitFlag = 1;
			}
			else { //공백이아니면 넘겨주는 파일에대한체크 (체크할거: 1.파일사이즈 2.확장자체크(jpg,gif,png 파일인지 체크))
				//파일사이즈비교
				let fileSize = document.getElementById("file").files[0].size;//myform으로 하면안됨(무조건 id로 체크), 0번째파일의 사이즈를 읽어옴
				
				if(fileSize > maxSize) {
					alert("업로드 파일의 크기는 1MByte를 초과할수 없습니다.");
					return false; //프로그램이 진행되면 안되니까 끊기
				}
				else if(uExt != "JPG" && uExt != "GIF" && uExt != "PNG") { //3개가 하나도 포함되지않으면 
					alert("업로드 가능한 파일은 'JPG/GIF/PNG'파일 입니다.");
					return false;
				} 
				else if(fName.indexOf(" ")!= -1) { // -1은 없다는건데 부정을했으니 => 공백이 포함됐으면
					alert("업로드 파일에는 공백을 포함할수 없습니다.");
					return false;				
				}
				else {
					submitFlag =1;				
				}
			}
			//전송전에 모든 체크가 끝나면 submitFlag가 1이 되도록 처리후 서버로 전송한다
			if(submitFlag == 1) { //정상처리 (내용이 묶여서 잘들어왔을때 1로옴)
				//아이디와 닉네임중복체크 버튼에 대한 체크처리 (버튼눌렀어야지만 회원가입)
				
				console.log(mid);
        console.log(pwd);
        console.log(nickName);
        console.log(name);
        console.log(email);
        console.log(birthday);
        console.log(address);
        console.log(homePage);
        console.log(job);
        console.log(hobby);
        console.log(content);
			
				alert("회원가입 성공");
				//myform.submit(); //전송
			}
			else { //비정상처리
				alert("회원가입 실패~~");
			}
		}
		
		//id 중복체크
		function idCheck() {
			let mid = myform.mid.value;
			let url = "${ctp}/memIdCheck.mem?mid="+mid; //새창에서 할거임(새창도 컨트롤러 통해서가야함)
			
			if(mid.trim() == "") {
				alert("아이디를 입력하세요!");
				myform.mid.focus();
			}
			else {
				window.open(url,"nWin","width=580px, height=250px");
			}
		}
		
		//닉네임 중복체크
		function nickCheck() {
			let nickName = myform.nickName.value;
			let url = "${ctp}/memNickCheck.mem?nickName="+nickName;
			
			if(nickName.trim() == "") {
				alert("닉네임을 입력하세요!");
			}
			else {
				window.open(url,"nWin","width=580px, height=250px");
			}
		}
		
	</script> -->
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<form name="myform" method="post" action="${ctp}/memJoinOk.mem" class="was-validated">
    <h2>회 원 가 입</h2>
    <br/>
    <div class="form-group">
      <label for="mid">아이디 : &nbsp; &nbsp;<input type="button" value="아이디 중복체크" class="btn btn-secondary btn-sm" onclick="idCheck()"/></label>
      <input type="text" class="form-control" name="mid" id="mid" placeholder="아이디를 입력하세요." required autofocus/>
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호 :</label>
      <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" required />
    </div>
    <div class="form-group">
      <label for="nickName">닉네임 : &nbsp; &nbsp;<input type="button" value="닉네임 중복체크" class="btn btn-secondary btn-sm" onclick="nickCheck()"/></label>
      <input type="text" class="form-control" id="nickName" placeholder="별명을 입력하세요." name="nickName" required />
    </div>
    <div class="form-group">
      <label for="name">성명 :</label>
      <input type="text" class="form-control" id="name" placeholder="성명을 입력하세요." name="name" required />
    </div>
    <div class="form-group">
      <label for="email1">Email address:</label>
				<div class="input-group mb-3">
				  <input type="text" class="form-control" placeholder="Email을 입력하세요." id="email1" name="email1" required />
				  <div class="input-group-append">
				    <select name="email2" class="custom-select">
					    <option value="naver.com" selected>naver.com</option>
					    <option value="hanmail.net">hanmail.net</option>
					    <option value="hotmail.com">hotmail.com</option>
					    <option value="gmail.com">gmail.com</option>
					    <option value="nate.com">nate.com</option>
					    <option value="yahoo.com">yahoo.com</option>
					  </select>
				  </div>
				</div>
	  </div>
    <div class="form-group">
      <div class="form-check-inline">
        <span class="input-group-text">성별 :</span> &nbsp; &nbsp;
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="gender" value="남자" checked>남자
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="gender" value="여자">여자
			  </label>
			</div>
    </div>
    <div class="form-group">
      <label for="birthday">생일</label>
      <input type="date" name="birthday"  value="<%=java.time.LocalDate.now() %>" class="form-control"/>
    </div>
    <div class="form-group">
      <div class="input-group mb-3">
	      <div class="input-group-prepend">
	        <span class="input-group-text">전화번호 :</span> &nbsp;&nbsp;
			      <select name="tel1" class="custom-select">
					    <option value="010" selected>010</option>
					    <option value="02">서울</option>
					    <option value="031">경기</option>
					    <option value="032">인천</option>
					    <option value="041">충남</option>
					    <option value="042">대전</option>
					    <option value="043">충북</option>
			        <option value="051">부산</option>
			        <option value="052">울산</option>
			        <option value="061">전북</option>
			        <option value="062">광주</option>
					  </select>-
	      </div>
	      <input type="text" name="tel2" size=4 maxlength=4 class="form-control"/>-
	      <input type="text" name="tel3" size=4 maxlength=4 class="form-control"/>
	    </div> 
    </div>
    <div class="form-group">
      <label for="address">주소</label>
			<input type="hidden" name="address" id="address"> <!-- hidden  -->
			<div class="input-group mb-1">
				<input type="text" name="postcode" id="sample6_postcode" placeholder="우편번호" class="form-control">
				<div class="input-group-append">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn btn-secondary">
				</div>
			</div>
			<input type="text" name="roadAddress" id="sample6_address" size="50" placeholder="주소" class="form-control mb-1"> <!-- 우편번호 API코드 -->
			<div class="input-group mb-1">
				<input type="text" name="detailAddress" id="sample6_detailAddress" placeholder="상세주소" class="form-control"> &nbsp;&nbsp;
				<div class="input-group-append">
					<input type="text" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목" class="form-control">
				</div>
			</div>
    </div>
    <div class="form-group">
	    <label for="homepage">Homepage address:</label>
	    <input type="text" class="form-control" name="homePage" value="http://" placeholder="홈페이지를 입력하세요." id="homePage"/>
	  </div>
    <div class="form-group">
      <label for="name">직업</label>
      <select class="form-control" id="job" name="job">
        <option>학생</option>
        <option>회사원</option>
        <option>공무원</option>
        <option>군인</option>
        <option>의사</option>
        <option>법조인</option>
        <option>세무인</option>
        <option>자영업</option>
        <option>기타</option>
      </select>
    </div>
    <div class="form-group">
      <div class="form-check-inline">
        <span class="input-group-text">취미</span> &nbsp; &nbsp;
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="등산" name="hobby"/>등산
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="낚시" name="hobby"/>낚시
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="수영" name="hobby"/>수영
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="독서" name="hobby"/>독서
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="영화감상" name="hobby"/>영화감상
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="바둑" name="hobby"/>바둑
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="축구" name="hobby"/>축구
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="기타" name="hobby" checked/>기타
			  </label>
			</div>
    </div>
    <div class="form-group">
      <label for="content">자기소개</label>
      <textarea rows="5" class="form-control" id="content" name="content" placeholder="자기소개를 입력하세요."></textarea>
    </div>
    <div class="form-group">
      <div class="form-check-inline">
        <span class="input-group-text">정보공개</span>  &nbsp; &nbsp; 
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="userInfor" value="공개" checked/>공개
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="userInfor" value="비공개"/>비공개
			  </label>
			</div>
    </div>
    <div  class="form-group">
      회원 사진(파일용량:2MByte이내) :
      <input type="file" name="fName" id="file" class="form-control-file border"/> <!-- 아무것도 파일선택안하면 noimage사진  -->
    </div>
    <button type="button" class="btn btn-secondary" onclick="fCheck()">회원가입</button> &nbsp;
    <button type="reset" class="btn btn-secondary">다시작성</button> &nbsp;
    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/memLogin.mem';">돌아가기</button>
 	
 		<input type="hidden" name="photo"/> <!-- hidden -->
 		<input type="hidden" name="tel"/> <!-- hidden -->
 		<input type="hidden" name="email"/> <!-- hidden -->
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>