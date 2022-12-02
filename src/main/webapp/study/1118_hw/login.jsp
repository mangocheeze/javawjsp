<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	String mid=""; //전역변수를 줄거임 아래다가 뿌려야하니까
	
	if(cookies != null) {	
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {//쿠키들중 쿠키명이 cMid(쿠키에있는 아이디랑)랑 비교해서 같은걸가져올거임(cMid를 가져올거야)
				mid = cookies[i].getValue(); //근데 cMid가아니라 거기들어있는 값을 가져와야함 그걸 변수 mid에 담음
				break; //빠져나감
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login.jsp</title>
	<jsp:include page="../../include/bs4.jsp"></jsp:include>
</head>
<body>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="${pageContext.request.contextPath}/study/j1118_hw/LoginOk">
  	<table class="table table-bordered text-center">
  	  <tr>
  	    <td colspan="2"><h3>로 그 인</h3></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">아이디</th>
  	  	<td><input type="text" name="mid" value="<%=mid%>" class="form-control" autofocus required /></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">비밀번호</th>
  	  	<td><input type="password" name="pwd" class="form-control"  required /></td>
  	  </tr>
  	  <tr>
  	  	<td colspan="2"><input type="checkbox" name="idSave" checked/>아이디 저장</td>
  	  </tr>
  	  <tr>
  	    <td colspan="2">
  	      <button type="submit" class="btn btn-success">로그인</button> &nbsp;&nbsp;
  	      <button type="reset" class="btn btn-warning">다시입력</button>
  	    </td>
  	  </tr>
  	</table>
  </form>
</div>
<p><br/></p>
</body>
</html>