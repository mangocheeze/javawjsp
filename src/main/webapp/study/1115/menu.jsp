<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 1번째방법)
	<div class="text-center">
		<a href="main.jsp">홈으로</a> |  
		<a href="guest.jsp">방명록</a> | 방명록, 게시판같은경우 서블릿에서 jsp로 보내야함 지금은 jsp로가는거라 jsp만듦
		<a href="board.jsp">게시판</a> |
		<a href="pds.jsp">자료실</a> |  여기까지가 프로젝트에서 무조건 기본으로 만들어야함
		<a href="photo.jsp">포토갤러리</a>
	</div>
		 -->

<!-- 2번째방법 사용) main은 떠나지않음 -->
		 
	<div class="text-center">
		<a href="main.jsp">홈으로</a> |  
		<a href="main.jsp?sw=guest">방명록</a> |  <!-- main은 안바뀌고 매개변수를 줌 -->
		<a href="main.jsp?sw=board">게시판</a> |
		<a href="main.jsp?sw=pds">자료실</a> | 
		<a href="main.jsp?sw=photo">포토갤러리</a>
	</div>
	<!-- .jsp?변수명=이동할파일이름  -->
	<!-- sw는 내가 마음대로 줘도됨 sw= 뒤에오는건 내가만든 파일이름을 적어주기 -->