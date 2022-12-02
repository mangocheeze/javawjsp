<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n");%> <!-- 엔터키라는 값을 페이지에 저장처리 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>boContent.jsp</title>
	<jsp:include page="/include/bs4.jsp"></jsp:include>
	<script>
		'use strict';
		function goodCheck() {
			//아무것도할게없으니까 ajax로 바로들어감
			$.ajax({
				type : "post",
				url  : "${ctp}/boGood.bo", //무조건 절대경로로쓰기
				data : {idx : ${vo.idx}}, //idx넘김, idx는 숫자라 그냥 써도됨 ,문자면 "" 꼭쓰기!!
	  		success:function(res) {
    			if(res == "0") alert("이미 좋아요 버튼을 클릭하셨습니다.");
    			else location.reload();
    		},
				error : function() {
					alert("전송 오류 ~~");
				}
			});
		}
		
		//따봉위 누르면 좋아요 +1처리(계속증가)
    function goodCheckPlus() {
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/boGoodPlusMinus.bo",
    		data  : {
    			idx : ${vo.idx},
    			goodCnt : 1 
    		}, /* 값을 +1로넘김 */
    		success:function() {
    			location.reload();
    		}
    	});
    }
    
    //따봉아래 누르면 좋아요 -1처리
    function goodCheckMinus() {
    	$.ajax({
    		type  : "post",
    		url   : "${ctp}/boGoodPlusMinus.bo",
    		data  : {
    			idx : ${vo.idx},
    			goodCnt : -1
    		}, /* 값을 -1로넘김 */
    		success:function() {
    			location.reload();
    		}
    	});
    }
    
    //게시글 삭제처리 ( ajax보다 일반처리가 더 좋음 )
    function boDelCheck() {
    	let ans = confirm("현 게시글을 삭제하시겠습니까?");
    	if(ans) location.href = "${ctp}/boDeleteOk.bo?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&mid=${vo.mid}";
    	
    }
    
    //댓글달기
    function replyCheck() {
    	let content = $("#content").val();  //jQuery방식
    	if(content.trim() == "") {
    		alert("댓글을 입력하세요");
    		$("#content").focus();
    		return false;
    	}
    	let query = {
    			boardIdx : ${vo.idx},
    			mid      : '${sMid}',
    			nickName : '${sNickName}',
    			content  : content,
    			hostIp   : '${pageContext.request.remoteAddr}'
    			
    	}
    	
    	$.ajax({
    		type : "post",
    		url  : "${ctp}/boReplyInput.bo",
    		data : query,
    		success:function(res) { //결과를 res변수에 담음
    			if(res == "1") { //문자라 "1"
    				alert("댓글이 입력되었습니다");
    				location.reload();
    			}
    			else {
    				alert("댓글 입력 실패~~");
    			}
    		},
    		error : function() {
    			alert("전송 오류!!");
    		}
    	});
    }
    
    //댓글삭제
		function replyDelCheck(idx) { //변수 idx로 받음
    	let ans = confirm("현재 댓글을 삭제하시겠습니까?");
			if(!ans) return false;
			
			$.ajax({
				type : "post",
				url  : "${ctp}/boReplyDeleteOk.bo",
				data : {idx : idx},
				success : function(res) {
					if(res == "1") {
						alert("댓글이 삭제되었습니다.");
						location.reload();
					}
					else {
						alert("댓글 삭제 실패 ~~");
					}
				},
				error  :function() {
					alert("전송 오류~~");
				}
			});
    } 
   
    
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2 class="text-center">글 내 용 보 기</h2>
	<br/>
	<table class="table table-borderless">
		<tr>
			<td class ="text-right">hostIp : ${vo.hostIp}</td>
		</tr>
	</table>
	<table class="table table-bordered">
		<tr>
			<th>글쓴이${vo.idx}</th>
			<td>${vo.nickName}</td>
			<th>글쓴날짜</th>
			<td>${fn:substring(vo.wDate,0,fn:length(vo.wDate)-2)}</td> <!-- .0을 잘라냄 -->
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${vo.title}</td>
		</tr>
		<tr>
			<th>전자메일</th>
			<td>${vo.email}</td>
			<th>조회수</th>
			<td>${vo.readNum}</td>
		</tr>
   <tr>
      <th>홈페이지</th>
      <td>${vo.homePage}</td>
      <th>좋아요</th>
      <td><a href="javascript:goodCheck()">
            <c:if test="${sSw == '1'}"><font color="red">❤</font></c:if>
            <c:if test="${sSw != '1'}">❤</c:if>
          </a>
          ${vo.good} ,
          <a href="javascript:goodCheckPlus()">👍</a> <!-- 계속증가, 계속감소 -->
          <a href="javascript:goodCheckMinus()">👎</a>
      </td>
    </tr>
    <tr>
      <th>글내용</th>
      <td colspan="3" style="height:220px">${fn:replace(vo.content, newLine , "<br/>")}</td> <!-- 글 줄바꿈 처리(엔터키를 <br/>로바꿈) -->
    </tr>
    <tr>
      <td colspan="4" class="text-center">
      	<c:if test="${flag =='search'}"><input type="button" value="돌아가기" onclick="location.href='${ctp}/boSearch.bo?search=${search}&searchString=${searchString}&pageSize=${pageSize}&pag=${pag}';" class="btn btn-secondary"/></c:if>
      	<c:if test="${flag !='search'}">
      		<input type="button" value="돌아가기" onclick="location.href='${ctp}/boList.bo?pageSize=${pageSize}&pag=${pag}';" class="btn btn-secondary"/>
	        <c:if test="${sMid == vo.mid || sLevel == 0}"> <!--글이 내꺼이거나 관리자이거나, 이 글이 내꺼인지 아는방법 : 로그인한사람의 세션아이디와 현재글의 아이디(vo에있음)가 같으면 글쓴사람임 -->
	        	<input type="button" value="수정하기" onclick="location.href='${ctp}/boUpdate.bo?idx=${vo.idx}&pageSize=${pageSize}&pag=${pag}';" class="btn btn-success"/> <!-- 페이지사이즈넘기는이유: 5페이지씩보고있었으면 다시와도 5페이지씩 넘기고싶어서, pag보내는이유: 수정하고 돌아와도 페이지유지하고싶어서 -->
	        	<input type="button" value="삭제하기" onclick="boDelCheck()" class="btn btn-danger"/> <!-- 삭제는 한번더 물어봐야좋으니까 함수로 물어봄, 현재글을 삭제할거라 idx안넘겨도됨(idx가 하나만있으니까) -->
	        </c:if>
        </c:if>
      </td>
    </tr>
	</table>
	
	<c:if test="${flag !='search'}">
		<!-- 이전글 / 다음글 처리 ( -->
		<table class="table table-borderless">
			<tr>
				<td>
					<c:if test="${preVo.preIdx != 0}">
						👈 <a href="${ctp}/boContent.bo?idx=${preVo.preIdx}&pageSize=${pageSize}&pag=${pag}">이전글 : ${preVo.preTitle}</a> <br/>
					</c:if>
					<c:if test="${nextVo.nextIdx != 0 }">
						👉<a href="${ctp}/boContent.bo?idx=${nextVo.nextIdx}&pageSize=${pageSize}&pag=${pag}">다음글 : ${nextVo.nextTitle}</a> 
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</div>
<!-- 댓글보여주는창 -->
<div class="container">
	<table class="table table-hover text-center">
		<tr>
			<th>작성자</th>
			<th>댓글내용</th>
			<th>작성일자</th>
			<th>접속IP</th>
		</tr>
		<c:forEach var="replyVo" items="${replyVos}">
			<tr>
				<td>${replyVo.nickName}
					<c:if test="${sMid == replyVo.mid || sLevel == 0}">
						(<a href="javascript:replyDelCheck(${replyVo.idx})" style="color:red" title="삭제하기" >삭제</a>) <!-- 삭제(ajax사용)-댓글의 고유번호를 넘겨줘야함(부모글말고) -->
					</c:if>	
				</td>
				<td>${replyVo.content}</td> <!-- <br/>처리해줘야함-엔터처리 -->
				<td>${replyVo.wDate}</td>
				<td>${replyVo.hostIp}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 댓글 입력창 -->
<%-- 	<form name="replyForm" method="post" action="${ctp}/boReplyInput.bo"> --%>
	<form name="replyForm">
		<table class="table text-center">
			<tr>
				<td style= "width:85%" class="text-left">
					글내용 : 
					<textarea rows="4"  name= "content" id="content" class="form-control"></textarea>
				</td>
				<td style= "width:15%">
					<br/>
					<p>작성 : ${sNickName}</p>
					<p>
						<input type="button" value="댓글달기" onclick="replyCheck()" class="btn btn-info btn-sm"/>
					</p>
				</td>
			</tr>
		</table>
		<%-- <input type="hidden" name="boardIdx" value="${vo.idx}"/> <!-- 원본글의 idx(value)는 댓글의 원본글 idx(name)로 들어감 -->
		<input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/>
		<input type="hidden" name="mid" value="${sMid}"/>
		<input type="hidden" name="nickName" value="${sNickName}"/> 
		ajax로 할거라 필요없어짐--%>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>