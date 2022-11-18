package study.storage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//여기서 쿠키는 내장객체 아님, import에 올려야함
//필터만들어놔서 한글처리 안해도됨
@WebServlet("/study/storage/T2_LoginOk")
public class T2_LoginOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ServletContext application = request.getServletContext();
		
	  String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
	  String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
	  
	  PrintWriter out = response.getWriter();
	  
	  if((mid.equals("admin") && pwd.equals("1234")) || (mid.equals("hkd1234") && pwd.equals("1234"))) {
	  //아이디를 쿠키에저장시켜줄거임
	  	Cookie cookie = new Cookie("cMid", mid);//쿠키생성  //위에서 받은 변수mid를 cMid에 담음
	  	cookie.setPath("/");				// 웹어플리케이션의 모든 URL에서 전송가능하도록 설정 (쿠키하려면 이거 쓰면됨)-루트(/)부터 설정하겠다
	  	cookie.setMaxAge(5*60);			// 5분 저장설정
	  	response.addCookie(cookie);	// 쿠키저장
	  	
	  	 //이걸쓰면 이제부터 세션에 쓸수있음 세션은 HttpSession에 들어가있음
	  	HttpSession session = request.getSession();	// 세션객체 생성	//이거외워야함. session쓰려면 이걸써야지 쓸수있음
	  	session.setAttribute("sMid", mid);					// 로그인 성공한 사용자의 아이디를 세션으로 저장시킨다.
	  	
	  	
	  	out.println("<script>");
	  	out.println("alert('"+mid+"님 로그인 되셨습니다.');");
	  	out.println("location.href='"+request.getContextPath()+"/study/1118_storage_server/t2_LoginMember.jsp';");
	  	out.println("</script>");
	  }
	  else {
	  	out.println("<script>");
	  	out.println("alert('아이디/비밀번호를 확인하세요.');");
	  	out.println("history.back();");
	  	out.println("</script>");
	  }
	}
}
