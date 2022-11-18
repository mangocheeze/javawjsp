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

@WebServlet("/study/j1118_hw/LogOut")
public class LogOut extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이걸씀으로써 이제 세션을 쓸수있음
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		int check = (int)session.getAttribute("check"); //LoginOk.java에서 쓴 변수 (체크 유무)
		
		
		Cookie[] cookies = request.getCookies();
		
		//'아이디저장'체크가 안되어있을때 처리해줌
		if(check == 0) { //LoginOk.java 에 0일때 체크박스 체크 안한걸로 코드적어놈
			if(cookies != null) { //쿠키가 null이 아니면 (id,pwd가 쿠키에 저장이되있으면)
				for(int i=0; i<cookies.length; i++) {
					cookies[i].setPath("/");
	      	cookies[i].setMaxAge(0);			 //만료시간 0 쿠키삭제
	      	response.addCookie(cookies[i]);  //쿠키저장
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('"+mid+"님 로그아웃 되셨습니다.');");
		out.print("location.href='"+request.getContextPath()+"/study/1118_hw/login.jsp';");
		out.print("</script>");
	}
}
