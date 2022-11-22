<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>el3.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>EL을 통한 배열처리2</h2>
	<hr/>
<%
	/* num1 */
	int[] num1 = new int[5];
	for(int i=0; i<num1.length; i++) {
		num1[i] = (i+1)*10; //10,20,30,40..으로 출력하고싶어서 *10
		// i가 0부터시작하니까 num1배열 0번째일때 = (0+1)*10 해서 10이나옴
	}
	pageContext.setAttribute("num1", num1);//현재페이지에서 담을거니까 pageContext저장소에담음
	
	
	/* num2 */
	int[] num2 = {10,2,3,4,50}; //num1과 0,4번 배열만 같음
	pageContext.setAttribute("num2", num2);

%>
	<h4>num1배열에 담긴값을 forEach를 통한출력</h4>
	<c:forEach var="num" items="${num1}" varStatus="st">  <%--var: 변수이름  items : 배열또는 집합체이름 varStatus :forEach문이 반복할때 관리되는 상태값을 사용할수있게하는속성(인덱스 번호 줄때사용,st는 이름마음대로준거)--%>
		<%-- ${st.count}. ${num}<br/> 번호출력 --%>
		${st.count}(num1[${st.index}]). ${num}<br/> <%--인덱스번호까지출력 --%>
	</c:forEach>
	<br/>
	<hr/>
	
	<h4>EL에서의 연산처리(연산자 사용)</h4>
	1.num1[0] == num2[0] : ${num1[0] == num2[0]}<br/> <%--num1번에 0번째배열과 num2에 0번째배열이 같으니? /결과는 ${}안에 넣어야함 /true나옴 --%>
	2.num1[1] == num2[1] : ${num1[1] == num2[1]}<br/> <%-- false나옴 --%>
	3.num1[1] eq num2[1] : ${num1[1] eq num2[1]}<br/> <%-- ==대신 eq써도됨 --%>
	4.num1[1] != num2[1] : ${num1[1] != num2[1]}<br/>
	5.num1[1] ne num2[1] : ${num1[1] ne num2[1]}<br/> <%-- !=대신 ne(not equal)써도됨 --%>
	6.num1[1] > num2[1] : ${num1[1] > num2[1]}<br/>
	7.num1[1] gt num2[1] : ${num1[1] gt num2[1]}<br/> <%-- >대신 gt써도됨 --%>
	8.num1[1] < num2[1] : ${num1[1] < num2[1]}<br/> 
	9.num1[1] lt num2[1] : ${num1[1] lt num2[1]}<br/>  <%-- <대신 lt써도됨 --%>
	10.num1[1] >= num2[1] : ${num1[1] >= num2[1]}<br/> <%--크거나 같다 --%>
	11.num1[1] ge num2[1] : ${num1[1] ge num2[1]}<br/> <%-- >= 대신 ge써도됨 --%>
	12.num1[1] <= num2[1] : ${num1[1] <= num2[1]}<br/> <%--작거나같다 --%>
	13.num1[1] le num2[1] : ${num1[1] le num2[1]}<br/> <%-- <= 대신 le써도됨 --%>
	<hr/>
	
<%
	ArrayList<String> arrVos = new ArrayList<>();
	arrVos.add("홍길동"); //arraylist에선 먼저쓴 순서에따라 알아서 이게 0번째 배열이됨
	arrVos.add("김말숙");
	arrVos.add("이기자");
	arrVos.add("소나무");
	arrVos.add("고인돌");
	
	pageContext.setAttribute("arrVos", arrVos); //담음
%>
	<h3>EL표기법을 통한 객체 (ArrayList) 출력</h3>
	arrVos : ${arrVos}<br/>
	arrVos[2] = ${arrVos[2]}<br/> <!-- 인덱스2번째 배열 이기자 출력됨 -->
	<c:forEach var="vo" items="${arrVos}" varStatus="st">
		${st.count}. ${vo}<br/>  <!-- 1.홍길동 이런식으로배열쭉나옴 -->
	</c:forEach>
	<hr/>
	
<%
	HashMap<String,String> mapVos = new HashMap<>();
	mapVos.put("성명","홍길순"); //put이 add처럼 저장한다는말
	mapVos.put("나이","25");
	mapVos.put("주소","서울");
	
	pageContext.setAttribute("mapVos", mapVos);
%>
	<h3>EL표기법으로 객체(map) 출력</h3>
	mapVos: ${mapVos}<br/> <!-- map형식은 순서가 관계없음(key로 가져옴),ArrayList는 순서가중요(인덱스번호로가져옴) -->
	mapVos["성명"]: ${mapVos["성명"]}<br/>  <!-- key로 가져옴 -->
	<hr/>
	
<%
	/* null값 처리 */
	pageContext.setAttribute("var1", "널값처리"); //문자
	pageContext.setAttribute("var2", ""); 			//공백 ""
	pageContext.setAttribute("var3", null); 		//null
%>
	<h4>Null값의 처리</h4>
	var1 : ${var1}<br/>  <!-- 출력됨 -->
	var2 : ${var2}<br/>  <!-- 출력안됨 -->
	var3 : ${var3}<br/>  <!-- 출력안됨 -->
	
	<!-- ""일경우 true -->
	""비교1 : ${var1 == ""}<br/> <!-- 문자가 들어있어서 공백이 아니니까 false -->
	""비교2 : ${var2 == ""}<br/> <!-- ""는 공백이라 true -->
	""비교3 : ${var3 == ""}<br/> <!-- null이라는걸 문자처리해서 false -->
	
	<!-- null일경우 true -->
	null 비교1 : ${var1 == null}<br/> <!-- false -->
	null 비교2 : ${var2 == null}<br/> <!-- false -->
	null 비교3 : ${var3 == null}<br/> <!-- true -->
	
	<!-- empty : 객체값이 비어있을경우 true -->
	연산자 : empty, !empty (공백("")과 null을 같은 값으로 취급한다)<br/>
	"" 와 empty 비교1 : ${empty var1}<br/> <!-- false -->
	"" 와 empty 비교2 : ${empty var2}<br/> <!-- true -->
	"" 와 empty 비교3 : ${empty var3}<br/> <!-- true -->
	
	<!-- empty가 아니니까 객체값이 비어있지않을경우 true -->
	null 과 !empty 비교1 : ${!empty var1}<br/> 
	null 과 !empty 비교2 : ${!empty var2}<br/> 
	null 과 !empty 비교3 : ${!empty var3}<br/>
</div>
<p><br/></p>
</body>
</html>