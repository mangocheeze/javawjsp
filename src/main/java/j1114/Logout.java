package j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//원래 로그아웃창은 서블릿에서 만드는거임. logout.jsp 만든이유는 jsp에도 만들수있다는걸 보여준거임
//test2Res.jsp에서 로그아웃 눌렀을때의 뜨는화면

@WebServlet("/j1114_Logout")
public class Logout extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글처리
		response.setContentType("text/html; charset=utf-8");
		
		String name = request.getParameter("name");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('"+name+"님 로그아웃되셨습니다.');");
		out.println("location.href='"+request.getContextPath()+"/study/1114/test2.jsp';"); //로그아웃하면 다시 로그인창으로가게끔함
		out.println("</script>");
	}
}
