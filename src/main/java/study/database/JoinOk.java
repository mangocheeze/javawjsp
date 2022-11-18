package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database/JoinOk")
public class JoinOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null? "" : request.getParameter("pwd");
		String name = request.getParameter("name")== null? "" : request.getParameter("name");
		
		JusorokVO vo = new JusorokVO();
		//vo에저장
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		
		JusorokDAO dao = new JusorokDAO();
		
		//아이디 중복체크...
		
		//중복체크후 정상 자료일경우 DB에 저장처리
		int res = dao.setJoinOk(vo);
		
		PrintWriter out = response.getWriter();
		
		if(res == 1) { //res가 1이면 정상처리
			//이런 방식으로 써도됨 (아래꺼랑 똑같은내용)
			out.println("<script>"
								+ "alert('"+mid+"님 회원가입을 환영합니다');"
								+ "location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';"
								+ "</script>");
		}
		else { //res = 1이 아니면 0이 넘어오니까 회원가입실패
			out.println("<script>");
			out.println("alert('회원가입 실패~ 다시 회원가입해주세요');");
//			out.println("history.back();"); 여기선 이렇게 쓰면 절대안됨 무한로프돔
			out.println("location.href='"+request.getContextPath()+"/study/1120_Database/join.jsp';"); //다시 회원가입창으로감 get방식은 무조건 앞에다가 따당
			out.println("</script>");
		}
		
	}
}
