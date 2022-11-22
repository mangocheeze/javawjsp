<%@page import="study.database.JusorokVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="study.database.JusorokDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 이거 올려줘야됨 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jstl4.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>Function 라이브러리</h2>
	<p>사용법 : $ { fn:함수명(변수/값...)}</p>
	<hr/>
<%			
							 //0         1         2
							 //01234567890123456789012
	String atom = "Welcome to My Homepage!";
	pageContext.setAttribute("atom", atom);  /* atom을 변수 atom에 담음 */
	pageContext.setAttribute("atom2", atom); /* atom을 변수 atom2에 담음(atom이랑 같은값 출력됨 - 아래서 중간에 atom값 사라져서 추가함) */
%>
	<p>
		<%-- 자바에서의방식(표현식?) /EL표기법  (서로똑같이 나와야함)--%>
		atom 변수의 값 : <%=atom %> / ${atom} <br/> 
		
		1.atom 변수 값의 길이: <%=atom.length() %> / ${fn:length(atom)}<br/> <%-- 다른건 {c:}이런식으로 쓰는데 function은 ${fn:}를 씀 --%>
		<hr/>
		2.대문자변환(toUpperCase()) : <%=atom.toUpperCase() %> / ${fn:toUpperCase(atom)}<br/>
		<hr/>
		3.소문자변환(toLowerCase()) : <%=atom.toLowerCase() %> / ${fn:toLowerCase(atom)}<br/>
		<hr/>
		4-1.문자열추출1(substring()) : <%=atom.substring(0,3) %> / ${fn:substring(atom,0,3)}<br/> <%--atom의 0번째부터3번째앞 --%>
		
		4-2-1.문자열추출2-1(substring()) : <%=atom.substring(2) %> /<%--  ${fn:substring(atom,2)}<br/> --%>  <!-- 자바에서 2만쓰면 2번째부터 끝까지인데 EL표기법으로 이렇게쓰면 에러남 -->
																															${fn:substring(atom,2,fn:length(atom))}<br/> <!-- 그래서 2번째부터 길이까지 -->
		
		4-2-2.문자열추출2-2(substring()) : <%=atom.substring(2) %> /${fn:substring(atom,2,-1)}<br/> <%-- 0은 첫번째를뜻하고 -1은 마지막을뜻함 2번째부터 마지막까지 --%> -->
		<hr/>
		5.특정문자열의 위치값찾기(indexOf()) : <%=atom.indexOf("o") %> / ${fn:indexOf(atom,'o')}<br/>  <%-- 'o'라는 글자를 찾아달라 --%>
		
		5-2.atom변수에 기억되어 있는 'o'문자의 위치를 모두 출력하고 전체개수를 출력하시오?<br/> <%--누적하란소리임 --%>
		<c:set var="cnt" value="0"/>
		<c:forEach var="i" begin="0" end="${fn:length(atom)-1}">
<%-- 	<c:if test="${fn:substring(atom,0,1)== 'o'}">
			<c:if test="${fn:substring(atom,1,2)== 'o'}"> --%>
			<c:if test="${fn:substring(atom,i,i+1)== 'o'}">
				문자 'o'의 위치는 <b>${i}</b> 입니다<br/>
			</c:if>
		</c:forEach>
		<p>문자 'o'의 전체 개수는 ? <b>${cnt}</b>입니다 <br/>
		<br/>
		
		
		<h2>atom : ${atom}</h2>
		<form name="myform">
			5-3.atom변수에 기억되어있는 문자를 입력받아 그 문자가 두번째로 기억되어 있는 위치값을 출력하시오
					단, 두번째 기억값이 없으면 1번째 기억위치를 출력하고 '두번째 값 없음'을 함께 출력하시오.<br/>
					<!-- string만 이용 -->
			<input type="text" name="find" class="form-control mb-3"/>
			<input type="submit" value="결과출력" class="btn btn-success form-control mb-3"/>
        <c:set var="cnt" value="0"/>
        <c:set var="find" value="${param.find}"/>
        <c:set var="num1" value="0"/>
        <c:set var="num2" value="0"/>
        <c:forEach var="i" begin="0" end="${fn:length(atom)-1}">
            <c:if test="${fn:substring(atom,i,i+1) == find}">
                <c:set var="cnt" value="${cnt +1}"/>
                <c:if test="${cnt == 1}">
                    <c:set var="num1" value="${i}"/>
                </c:if>
                <c:if test="${cnt == 2}">
                    <c:set var="num2" value="${i}"/>
                </c:if>
            </c:if>
        </c:forEach>
        <c:choose>
            <c:when test="${num2 !=0}">입력한 문자 ${find} 의 2번째 위치는 ${num2} 입니다.</c:when>
            <c:when test="${num1 !=0}">입력한 문자 ${find} 의 1번째 위치는 ${num1} 이며 두번째 값은 없습니다.</c:when>
        </c:choose>
        <br/>
		</form>
			<!-- 
			5-3.atom변수에 기억되어 있는 문자를 입력받아 그 문자가 두번째로 기억되어 있는 위치값을 출력하시오.
      단, 두번째 기억값이 없으면 1번째 기억위치를 출력하고 '두번째 값 없음'을 함께 출력하시오.<br/>
      - (1) atom 변수에 기억된 문자중 'o'의 위치를 찾아서 변수에 기억시켜둔다. :
      <c:set var="position1" value="${fn:indexOf(atom,'o')}"/> <font color='red'><b>${position1}</b></font><br/>
      
      - (2) substring()을 사용한, 첫번째로 만나는 'o'의 문자 뒤의 모든값을 변수에 담는방법 :
      < c :set var="str" value="$ {fn:substring(atom,fn:indexOf(atom,'o')+1,fn:length(atom))}"/> =>
      <c:set var="str1" value="${fn:substring(atom,fn:indexOf(atom,'o')+1,fn:length(atom))}"/>
      <font color='red'><b>${str1}</b></font><br/>
      
      - (3) 이어서 저장되어 있는 값중에서 'o'문자의 위치를 변수에 담는다. : 
      < c :set var="position2" value="$ {fn:indexOf(str1,'o')}"/> =>
      <c:set var="position2" value="${fn:indexOf(str1,'o')}"/>
      <font color='red'><b>${position2}</b></font><br/>
      
      - (4) 처음에 저장된 위치와 2번째 저장된 위치를 더하고 +1 시키면 2번째 'o'의 위치가 된다. : 
      <font color='red'><b>${position1 + position2 + 1}</b></font><br/><br/>
			 -->
		
		<hr/>		
		6. 문자열추출(substringBefore() / substringAfter())<br/>
				문자 'o'앞의 문자열 출력 : ${fn:substringBefore(atom, 'o')}<br/>
				문자 'o'뒤의 문자열 출력 : ${fn:substringAfter(atom, 'o')}<br/>
		
		
		<h2>atom : ${atom}</h2>
		<form name="myform">
		<!-- substringBefore, substringAfter 이용 -->
			6-2.atom변수에 기억되어있는 문자를 입력받아 그 문자가 두번째로 기억되어 있는 위치값을 출력하시오
					단, 두번째 기억값이 없으면 1번째 기억위치를 출력하고 '두번째 값 없음'을 함께 출력하시오.
		  <input type="text" name="find" class="form-control mb-3"/>
      <input type="submit" value="결과출력" class="btn btn-success form-control mb-3"/>
      <c:set var="find" value="${param.find}"/>
      <c:set var="af" value="${fn:substringAfter(atom,find)}"/>
      <c:set var="bf" value="${fn:substringBefore(atom,find)}"/>
      <c:set var="afio" value="${fn:indexOf(af,find)}"/>
      <c:choose>
          <c:when test="${afio==-1}">입력한 문자 ${find} 의 1번째 위치는 ${fn:length(bf)} 이며 두번째 값은 없습니다.</c:when>
          <c:otherwise>입력한 문자 ${find} 의 2번째 위치는 ${afio+1+fn:length(bf)} 입니다.</c:otherwise>
      </c:choose>
      <br/>
      <br/>	
		</form>
		
		
		<hr/>		
		7.문자열 분리(split(변수, 분리할문자) --> 결과를 변수에 담아줘야한다) <br/>
			atom변수의 문자중 'o'문자를 기준으로 분리하자?<br/> <%-- 분리하면 무조건 두개이상으로 분리되니까 배열임, split은 무조건 결과를변수에 담아줘야함 --%>
			<c:set var="atoms" value="${fn:split(atom,'o')}"/> <%--atom변수의 문자중 'o'를기준으로 자른다 --%>
			<c:forEach var="atom" items="${atoms}" varStatus="st">
				${st.count}.${atom}<br/>
			</c:forEach>
			atoms의 전체길이? ${fn:length(atoms)}<br/>
			예) jusorok테이블의 전체 건수는?
<%
			JusorokDAO dao = new JusorokDAO();
			ArrayList<JusorokVO> vos = dao.getMemberList();
			request.setAttribute("vos", vos); //pageContext저장소로 해도관계없음			
%>
			${fn:length(vos)} 건<br/>
		<hr/>	
		8.문자열 치환 (replace())<br/>
			문제: atom2변수안의 'My'를 'Your'로 변경하시오 :
			<%=atom.replace("My","Your") %> / ${fn:replace(atom2, 'My','Your')}<br/>
		<hr/>
		9.특정문자 포함유무? contains()<br/>
			atom2변수에 'o'를 포함하고 있느냐? ${fn:contains(atom2, 'o')} <br/>
			atom2변수에 'My'를 포함하고 있느냐? ${fn:contains(atom2, 'My')} <br/>
			atom2변수에 'Your'를 포함하고 있느냐? ${fn:contains(atom2, 'Your')} <br/>
	</p>
</div>
<p><br/></p>
</body>
</html>