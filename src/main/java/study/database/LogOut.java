package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//jsp로 로그아웃해도되는데 우린 지금 서블릿으로 하고있어서 서블릿으로 로그아웃만듦

@WebServlet("/database/LogOut")
public class LogOut extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JusorokDAO dao = new JusorokDAO();
		
		dao.logout();
		
		//여기선 HttpServlet를 상속받아서 request가 세션을 생성을 할수있음/~님 로그아웃되었습니다 하려고
		HttpSession session = request.getSession(); //이걸써야 session사용가능
		String name= (String) session.getAttribute("sName"); //session.getAttribute("sName")얘는 객체타입인데 변수(name)로 받고싶어서 강제로 형변환해줌
		session.invalidate();
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+name+"님 로그아웃되었습니다.!!');");
		out.println("location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';");  
		out.println("</script>");
	}
}
