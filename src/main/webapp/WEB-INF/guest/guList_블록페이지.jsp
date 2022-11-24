<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %> <!--  newLine을 현재페이지에저장하라 \n을 newLine변수에 담음. textarea 쓴곳에서(여기선방문소감) 엔터쳐서 글쓰면 그대로 나오게 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>guList.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<style>
		th{
			text-align : center;
			background-color: #ccc;
		}
	</style>
	<script>
		//삭제하기전에 물어보는 메세지
		'use strict';
		function delCheck(idx) { //delCheck에서 idx를 넘겼으니까
			let ans = confirm("정말로 삭제하시겠습니까?");
			//if(ans) location.href="${ctp}/guDelete.gu?idx=${vo.idx}";  //
			if(ans) location.href="${ctp}/guDelete.gu?idx="+idx;  //idx=에는 서버값을 주면안됨 ???????
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	
	<!-- 이 부분이 view임 -->
	<h2 class="text-center">방 명 록 리 스 트</h2>
	<br/>
		<table class="table table-borderless mb-0"> <!-- forEach바깥으로뺌 (안에넣으면 맨날 나오니까) --> <!-- 가장바깥껍데기 -->
			<tr>
				<td>
					<c:if test="${sAMid != 'admin'}"><a href="${ctp}/adminLogin.gu" class="btn btn-sm btn-secondary">관리자</a></c:if> <!-- 현재 세션 id가 admin이 아닐땐 버튼 이름 관리자(현재 관리자가아니니까 관리자로 로그인할수있게끔)  -->
					<c:if test="${sAMid == 'admin'}"><a href="${ctp}/adminLogout.gu" class="btn btn-sm btn-secondary">관리자 로그아웃</a></c:if> <!-- 현재 관리자로 로그인했었다면 버튼이름 관리자 로그아웃 -->
				</td> 
				<td style ="text-align:right;"><a href="${ctp}/guest/guInput.gu" class="btn btn-sm btn-secondary">글쓰기</a></td> 
			</tr>
		</table>
		<table class="table table-borderless m-0 p-0">
			<tr>
				<td class="text-right">
				<!-- 페이지처리 -->
				<!--첫페이지 /이전페이지 / (현페이지번호/총페이지수) /다음페이지 /마지막페이지 -->
					<c:if test="${pag > 1}">
						[<a href="${ctp}/guList.gu?pag=1">첫페이지</a>] <!-- 이전페이지는 1페이지에 없으니까 1페이지보다 클경우에만 현재페이지에 -1  -->
						[<a href="${ctp}/guList.gu?pag=${pag-1}">이전페이지</a>] <!-- 이전페이지는 1페이지에 없으니까 1페이지보다 클경우에만 현재페이지에 -1  -->
					</c:if>
					${pag} / ${totPage}
					<c:if test="${pag < totPage}">
						[<a href="${ctp}/guList.gu?pag=${pag+1}">다음페이지</a>] <!-- 다음페이지 : 넘어온페이지가 총페이지보다 작을때만 현재페이지 +1 해서뜨게함  -->
						[<a href="${ctp}/guList.gu?pag=${totPage}">마지막페이지</a>] <!-- 다음페이지 : 넘어온페이지가 총페이지보다 작을때만 현재페이지 +1 해서뜨게함  -->
					</c:if>
				</td> 
			</tr>
		</table>
	<c:set var="curScrStartNo" value="${curScrStartNo}"/> <!-- 전체건수를 curScrStartNo에 넣음 -->
	<c:forEach var="vo" items="${vos}" varStatus="st">  <!-- 이게있어야 리스트에 vo에있던정보들이뜸 -->
		<!-- 방명록은 게시판과다름( 게시판은 글제목만뜨고 눌러야 내용보임, 방명록은 이름하고내용이바로뜸-통으로 띄워야함) -->
		<table class="table table-borderless mb-0"> <!-- 테이블안에 테이블 -->
			<tr>
				<%-- <td>방문번호 : ${vo.idx} <!-- 고유번호로 할거면 vo에있는 고유번호 --> --%>
				<td>방문번호 : ${curScrStartNo} 
					<c:if test="${sAMid == 'admin'}"><a href="javascript:delCheck(${vo.idx})" class="btn btn-sm btn-danger">삭제</a></c:if> <!-- ?idx=${vo.idx}": url에 idx값도 같이보내는거임 삭제하면 방문번호도 삭제해야되니가 -->
				</td>
				<td style ="text-align:right;">방문IP : ${vo.hostIp}</td>  <!-- vo.hostIp 는 vo에있는 필드명 -->
			</tr>
		</table>
		<table class="table table-bordered"> <!-- 내용있는애??? -->
			<tr>
				<th style="width:20%;">성명</th>
				<td style="width:25%;">${vo.name}</td> <!-- 그냥 name이 아니라 DB에 저장된 vo에담겨있는 name -->
				<th style="width:20%;">방문일자</th>
				<td style="width:35%;">${fn:substring(vo.visitDate,0,19)}</td> <!-- vo.visitDate를 0번째부터 19번앞에까지자르기 -->
			</tr>
			<tr>
				<th>전자우편</th>
				<td colspan="3">
					<c:if test="${fn:length(vo.email) <= 4}">- 없음 -</c:if>
					<c:if test="${fn:length(vo.email) > 4}"><a href="${vo.email}" target="_blank">${vo.email}</a></c:if>
				</td> <!-- td(열)3개를 하나로합침 -->
			</tr>
			<tr>
				<th>홈페이지</th>
				<td colspan="3">
					<c:if test="${fn:length(vo.homePage) <= 8}">- 없음 -</c:if> <!-- 홈페이지주소는 최소 8글자니까 (http:// 7글자 + 홈페이지주소 최소 1글자) 8보다 작으면 홈페이지에 쓴게 없는거 -->
					 <c:if test="${fn:length(vo.homePage) > 8}"><a href="${vo.homePage}" target="_blank">${vo.homePage}</a></c:if>
				</td>
			</tr>
			<tr>
				<th>방문소감</th>
				<td colspan="3">${fn:replace(vo.content, newLine, '<br/>')}</td> <!-- fn:replace:해당문자열에 지정한문자를 다른문자열로 변환함 vo.content에 newLine을 <br/>로 치환(바꿔줘라)해라 -->
			</tr>
		</table>
		<br/>
		<c:set var="curScrStartNo" value="${curScrStartNo-1}"/> <!-- 삭제를 하면 idx가 1개씩 줄어들어야하니까 전체 no에 -1함 -->
	</c:forEach>
	<br/>
	<!-- 블록페이지처리 -->
	<!-- 첫페이지 /이전블록 / 1(페이지) 2(페이지) 3(페이지) /다음블록/ 마지막페이지 -->
	<!-- 첫페이지 /이전블록 / 4(페이지) 5(페이지) 6(페이지) /다음블록/ 마지막페이지 -->
	<div class="text-center">
		<c:if test="${pag > 1}">[<a href="${ctp}/guList.gu?pag=1">첫페이지</a>]</c:if>
		<c:if test="${curBlock > 0}">[<a href="${ctp}/guList.gu?pag=${(curBlock-1)*blockSize + 1}">이전블록</a>]</c:if> <!-- 나의 현재블록위치가 0보다 크면 이전블록이있으니까 실행(0블록빼곤 다실행됨) -->
		<c:forEach var="i" begin="${(curBlock)*blockSize + 1}" end="${(curBlock)*blockSize + blockSize}" varStatus="st"> <!-- 시작은 첫번째는 0블럭이면 시작페이지가 1이됨  -->
			<c:if test="${i <= totPage}"><!-- 마지막페이지가 보이면 다음블록은 안보이게하기 -->
				[<a href="${ctp}/guList.gu?pag=${i}">${i}</a>]
			</c:if> 
		</c:forEach>
		<c:if test="${curBlock < lastBlock}">[<a href="${ctp}/guList.gu?pag=${(curBlock+1)*blockSize + 1}">다음블록</a>]</c:if> <!-- 다음블록누르면 그블록의 첫번째페이지가 나오게함 ex. 내가 0블럭에 있으면 다음블록누르면 페이지4가나오게  -->
		<c:if test="${pag < totPage}">[<a href="${ctp}/guList.gu?pag=${totPage}">마지막페이지</a>]</c:if>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>