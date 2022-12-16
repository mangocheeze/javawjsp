<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>pdsContent.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
		$(window).scroll(function(){
			if($(this).scrollTop() > 100 ){ /* 스크롤탑이 100px보다 크면 서서히 화살표가 보임 */
	      $("#topBtn").addClass("on"); 
	    }
	    else{ /* 100px보다 작아지면 on 클래스를 지움 */
	      $("#topBtn").removeClass("on");
	    }
			
			$("#topBtn").click(function() { //top버튼을 클릭했을때
			  window.scrollTo({ top: 0, behavior: "smooth" });  //페이지에서 특정 위치로 스크롤 이동시키기 위해서는 window.scrollTo 메서드를 사용 ,ex.top :500 을쓰게되면 위에서부터 500만큼띄운다는뜻
			});
		});
		
	    // 다운로드 회수 증가
	    function downNumCheck(idx) {
	    	$.ajax({
	    		type  : "post",
	    		url   : "${ctp}/pdsDownNum.pds",
	    		data  : {idx : idx},
	    		success:function() {
	    			location.reload();
	    		},
	    		error : function() {
	    			alert("전송오류!!");
	    		}
	    	});
	    }
  </script>
  <style> /* css */
    th {
      background-color: #eee;
    }
		h6 {
		  position: fixed;
		  right: 1rem; /* rem : 그전에 em을 상속받음(여기선 2em =16px) */
		  bottom: -50px;
		  opacity: 0; /* 불투명도 */
		  transition: 0.7s ease; /* 0.7초정도 천천히 변화를주겠다 -풀다운메뉴에서 해봤음 */
		}
		.on {
		  opacity: 0.8;
		  cursor: pointer;
		  bottom: 0;
		}
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">개별 자료 상세 내역</h2>
  <br/>
  <table class="table table-bordered text-center">
    <tr>
      <th>올린이</th>
      <td>${vo.nickName}</td>
      <th>올린날짜</th>
      <td>${fn:substring(vo.fDate,0,fn:length(vo.fDate)-2)}</td> <!-- 0에서부터 전체길이-2한거까지 잘라옴(.? 두개빼야함) -->
    </tr>
    <tr>
      <th>파일명</th>
      <td>
        <c:set var="fNames" value="${fn:split(vo.fName,'/')}"/>
        <c:set var="fSNames" value="${fn:split(vo.fSName,'/')}"/>
        <c:forEach var="fName" items="${fNames}" varStatus="st">
          <a href="${ctp}/data/pds/${fSNames[st.index]}" download="${fName}" onclick="downNumCheck(${vo.idx})">${fName}</a><br/>
        </c:forEach>
      </td>
      <th>다운횟수</th>
      <td>${vo.downNum}</td>
    </tr>
    <tr>
      <th>분류</th>
      <td>${vo.part}</td>
      <th>파일크기</th>
      <td><fmt:formatNumber value="${vo.fSize / 1024}" pattern="#,##0"/>KByte</td> <!--  -->
    </tr>
    <tr>
      <th>제목</th>
      <td colspan="3" class="text-left">${vo.title}</td>
    </tr>
    <tr>
      <th>상세내역</th>
      <td colspan="3" style="height:280px;" class="text-left">${fn:replace(vo.content, newLine, '<br/>')}</td>
    </tr>
  </table>
  <div class="text-center">
    <input type="button" value="돌아가기" onclick="location.href='${ctp}/pdsList.pds?pag=${pag}&part=${part}';" class="btn btn-secondary"/>
  </div>
  <hr/>
  <div class="text-center">
	  <c:set var="fSNames" value="${fn:split(vo.fSName,'/')}"/> <!-- fSNames: 실제서버에 저장된 파일들이름 -->
	  <c:forEach var="fSName" items="${fSNames}" varStatus="st"> <!-- 개별자료 상세내역에서 파일명: 업로드된이름 / 파일위에써놓은 파일명 : 서버에 올라간 이름 -->
	    ${st.count}. ${fSName}<br/>
	    <c:set var="fSNameLen" value="${fn:length(fSName)}"/>
	  	<c:set var="ext" value="${fn:substring(fSName,fSNameLen-3,fSNameLen)}"/>
	  	<c:set var="extUpper" value="${fn:toUpperCase(ext)}"/>
	  	<c:if test="${extUpper=='JPG' || extUpper=='GIF' || extUpper=='PNG'}">
	  		<img src="${ctp}/data/pds/${fSName}" width="85%"/>
	  	</c:if>
	  	<hr/>
	  </c:forEach>
  </div>
</div>
<!-- <h6 id="topBtn" class="">맨위로</h6> --> <!-- 맨위로 글자 -->
<h6 id="topBtn"><img src="${ctp}/images/arrowTop.gif"/></h6> <!-- 화살표 이미지 -->
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>