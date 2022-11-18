package study.j1118_hw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/study/j1118_hw/LoginOk")
public class LoginOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		String idSave = request.getParameter("idSave")==null? "" : request.getParameter("idSave"); //체크박스 null값 체크
		
		PrintWriter out = response.getWriter(); //웹에 출력시킬수 있게하는 코드
		
		if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("hkd1234") && pwd.equals("1234"))) {
			
			//이걸 씀으로서 이제부터 세션에 쓸수있음
			HttpSession session = request.getSession();
      session.setAttribute("sMid", mid);

      //아이디가 admin이거나 hkd1234 일때 아이디를 쿠키에 저장시켜줌
      if(idSave.equals("on")) { //"on"은 체크가 되어있을때(체크박스기본값, 정해져있는코드임)
      	session.setAttribute("check", 1); //속성 1을 check변수에 담은걸 session에 저장시킴(저장시켜놓으면 다른 파일에서도 사용가능)
      	Cookie cookie = new Cookie("cMid", mid); //쿠키생성
      	cookie.setPath("/"); 				 //웹 어플리케이션의 모든 URL에서 전송가능하도록 설정 
      	cookie.setMaxAge(5*60);			 //5분저장설정
      	response.addCookie(cookie);  //쿠키저장
      }
      else {
      	session.setAttribute("check", 0);
      }
      
      //방문자 카운트
      int visitCnt = 0;
      
      if(session.getAttribute("sVisitCnt") == null) { //null이면
      	session.setAttribute("sVisitCnt", 1); //session에 저장/null이면 sVisitCnt변수에 1을 담음
      }
  		else { //null이 아니면
  			visitCnt= (int) session.getAttribute("sVisitCnt") + 1; 
  			//로그인을 성공하고 한번이라도 이 변수가(aVisitCnt) 생성되어있으면 들어옴(방문자수가 한번이라도 생기면)/(변수에들어가있는 값을읽어와서 거기다 1더한것 => 0부터나오게하기 싫어서),카운트는 숫자로 형변환
  			session.setAttribute("sVisitCnt", visitCnt ); //세션에저장
  		}
  		out.println("<script>");
  		out.println("alert('"+mid+"님 로그인 되셨습니다.');"); //mid로 써도되고 sMid로써도되는데 sMid 는 EL표기법으로해야함${mid}
  		out.println("location.href='"+request.getContextPath()+"/study/1118_hw/loginMember.jsp';"); //회원전용방으로 이동
  		out.println("</script>");
  		
  	}
  	else { //아이디 비밀번호가 위에 admin,1234 나 hkd1234,1234가 아닐경우
  		out.println("<script>");
  		out.println("alert('아이디와 비밀번호를 확인하세요!');");
  		out.println("history.back();");
  		out.println("</script>");
  	}
		
	}
}
