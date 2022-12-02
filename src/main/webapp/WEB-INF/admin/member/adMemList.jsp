<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adMemList.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict';
  	
  	function midSearch()	{
  		let mid = myform.mid.value;
  		if(mid.trim() == "") {
  			alert("아이디를 입력하세요!");
  			myform.mid.focus();
  		}
  		else {
  			myform.submit();
  		}
  	}
  	
    function delCheck(idx) {
    	let ans = confirm("탈퇴처리 시키겠습니까?");
    	if(ans) location.href='${ctp}/adMemberDel.ad?pag=${pag}&idx='+idx;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2 class="text-center">전체 회원 리스트</h2>
  <br/>
  <form name="myform" method="post" action="${ctp}/adMemberSearch.ad">
  	<div class="row">
  	  <div class="col form-inline">
  	    <input type="text" name="mid" class="form-control" autofocus />&nbsp;
  	    <input type="button" value="아이디개별검색" onclick="midSearch();" class="btn btn-secondary" />
  	  </div>
  	  <div class="col text-right"><button type="button" onclick="location.href='${ctp}/adMemList.ad';" class="btn btn-success">전체검색</button></div> <!-- 어떤페이지가던 다시 돌아옴??? -->
  	</div>
  </form>
  <table class="table table-hover text-center">
    <tr class="table-dark text-dark">
      <th>번호</th> <!-- 나중에 바꾸기??? -->
      <th>아이디</th>
      <th>별명</th>
      <th>성명</th>
      <th>최초가입일</th> <!-- 관리자는 이것도 한눈에 보게하는게좋음 -->
      <th>마지막접속일</th>
      <th>등급</th>  <!-- 현재등급보여줌 -->
      <th>탈퇴유무</th>  <!-- DB에서 바로 날리지않고 우선 신청만받음, 한달후에 관리자가 지우게하던,관리작 바로지우게하면됨 -->
    </tr>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${vo.idx}</td>
        <td><a href="${ctp}/adMemInfor.ad?mid=${vo.mid}&pag=${pag}">${vo.mid}</a></td> <!-- 아이디에 링크걸기,전체회원중 아이디를 클릭한회원만 회원정보 상세보기 보이게함 /memInfor에 id랑 페이지를 같이넘김(상세보기하고 돌아오기하면 갔던페이지그대로가게함)-->
        <td>${vo.nickName}</td>
				<td>${vo.name}<c:if test="${sLevel == 0 && vo.userInfor == '비공개'}"><font color='red'>(비공개)</font></c:if></td> <!-- 관리자나 비공개면 (비공개)라고 써있음,회원리스트에서 관리자는 회원들이 비공개면 비공개로보이게하기 -->
        <td>${vo.startDate}</td>
        <td>${vo.lastDate}</td>
        <td>
        	<form name="levelForm" method="post" action="${ctp}/adMemberLevel.ad">
        		<select name="level" onchange="javascript:alert('회원정보를 변경하시려면, 등급변경버튼을 클릭하세요');"> <!--  onchange: 콤보상자,내용이 바뀌니까 ,콤보상자바꾸면 메세지가 뜨게함,javascript: 자바스크립트를 사용할거야 -->
        			<option value="0" <c:if test="${vo.level==0}">selected</c:if>>관리자</option>  <!-- 넘어온 level이 0이랑같으면 관리자 -->
        			<option value="1" <c:if test="${vo.level==1}">selected</c:if>>준회원</option> 
        			<option value="2" <c:if test="${vo.level==2}">selected</c:if>>정회원</option>  
        			<option value="3" <c:if test="${vo.level==3}">selected</c:if>>우수회원</option>  <!-- 운영자도 4로 추가해도됨 -->
        		</select>
        		<input type="submit" value="등급변경" class="btn btn-warning btn-sm"/>
        		<input type="hidden" name="idx" value="${vo.idx}"/> <!-- 변경할때 고유번호 보내야되니까 hidden으로 고유한번호인 idx나 mid넘기면됨(idx가좋음) -->
        	</form>
        </td>
        <td>
        	<c:if test="${vo.userDel=='OK'}"><a href="javascript:delCheck(${vo.idx})"><font color="red">탈퇴신청</font></a></c:if> <!-- Ok면 탈퇴신청함 -->
        	<c:if test="${vo.userDel!='OK'}">활동중</c:if>
        </td>
      </tr>
    </c:forEach>
    <tr><td colspan="8" class="m-0 p-0"></td></tr>
  </table>
</div>
<br/>
<!-- 블록 페이지 시작 -->
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${pag > 1}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=1">첫페이지</a></li>
    </c:if>
    <c:if test="${curBlock > 0}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${(curBlock-1)*blockSize + 1}">이전블록</a></li>
    </c:if>
    <c:forEach var="i" begin="${(curBlock)*blockSize + 1}" end="${(curBlock)*blockSize + blockSize}" varStatus="st">
      <c:if test="${i <= totPage && i == pag}">
    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/adMemList.ad?pag=${i}">${i}</a></li>
    	</c:if>
      <c:if test="${i <= totPage && i != pag}">
    		<li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${i}">${i}</a></li>
    	</c:if>
    </c:forEach>
    <c:if test="${curBlock < lastBlock}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${(curBlock+1)*blockSize + 1}">다음블록</a></li>
    </c:if>
    <c:if test="${pag < totPage}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/adMemList.ad?pag=${totPage}">마지막페이지</a></li>
    </c:if>
  </ul>
</div>
<!-- 블록 페이지 끝 -->
<p><br/></p>
</body>
</html>