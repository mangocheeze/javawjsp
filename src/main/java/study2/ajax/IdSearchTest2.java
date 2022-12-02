package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

@SuppressWarnings("serial")
@WebServlet("/idSearchTest2")
public class IdSearchTest2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO(); //mid는 멤버(회원)아이디라 멤버DAO 사용
		
		MemberVO vo = dao.getLoginCheck(mid); //vo로 받음
		
		//PrintWriter out = response.getWriter();
	
		String name="";
		if(vo == null) {
			name="찾는 자료가 없습니다.";
		}
		else {
			name= vo.getName(); //vo에있는 name을 읽어와서 name변수에넣음
		}
		
		response.getWriter().write(name); //response.getWriter() :현재창에다가 write("내용"); :내용을쓰겠다
	
		
	}
}
