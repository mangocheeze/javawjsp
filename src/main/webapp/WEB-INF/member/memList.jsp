<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memList.jsp</title>
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
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">전체 회원 리스트</h2>
  <br/>
  <form name="myform" method="post" action="${ctp}/memMemberSearch.mem">
  	<div class="row">
  	  <div class="col form-inline">
  	    <input type="text" name="mid" class="form-control" autofocus />&nbsp;
  	    <input type="button" value="아이디개별검색" onclick="midSearch();" class="btn btn-secondary" />
  	  </div>
  	  <div class="col text-right"><button type="button" onclick="location.href='${ctp}/memList.mem';" class="btn btn-success">전체검색</button></div> <!-- 어떤페이지가던 다시 돌아옴??? -->
  	</div>
  </form>
  <table class="table table-hover text-center">
    <tr class="table-dark text-dark">
      <th>번호</th>
      <th>아이디</th>
      <th>별명</th>
      <th>성명</th>
      <th>성별</th>
    </tr>
    <c:set var="curScrStartNo" value="${curScrStartNo}"/>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${curScrStartNo}</td>
        <td><a href="${ctp}/memInfor.mem?mid=${vo.mid}&pag=${pag}">${vo.mid}</a></td><!-- 아이디에 링크걸기,전체회원중 아이디를 클릭한회원만 회원정보 상세보기 보이게함 /memInfor에 id랑 페이지를 같이넘김(상세보기하고 돌아오기하면 갔던페이지그대로가게함)-->
        <td>${vo.nickName}</td>
				<td>${vo.name}<c:if test="${sLevel == 0 && vo.userInfor == '비공개'}"><font color='red'>(비공개)</font></c:if></td> <!-- 관리자나 비공개면 (비공개)라고 써있음,회원리스트에서 관리자는 회원들이 비공개면 비공개로보이게하기 -->
        <td>${vo.gender}</td>
      </tr>
	    <c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
    </c:forEach>
    <tr><td colspan="5" class="m-0 p-0"></td></tr>
  </table>
</div>
<br/>
<!-- 블록 페이지 시작 -->
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${pag > 1}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=1">첫페이지</a></li>
    </c:if>
    <c:if test="${curBlock > 0}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=${(curBlock-1)*blockSize + 1}">이전블록</a></li>
    </c:if>
    <c:forEach var="i" begin="${(curBlock)*blockSize + 1}" end="${(curBlock)*blockSize + blockSize}" varStatus="st">
      <c:if test="${i <= totPage && i == pag}">
    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=${i}">${i}</a></li>
    	</c:if>
      <c:if test="${i <= totPage && i != pag}">
    		<li class="page-item"><a class="page-link text-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=${i}">${i}</a></li>
    	</c:if>
    </c:forEach>
    <c:if test="${curBlock < lastBlock}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=${(curBlock+1)*blockSize + 1}">다음블록</a></li>
    </c:if>
    <c:if test="${pag < totPage}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/memList.mem?mid=${mid}&pag=${totPage}">마지막페이지</a></li>
    </c:if>
  </ul>
</div>
<!-- 블록 페이지 끝 -->
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>