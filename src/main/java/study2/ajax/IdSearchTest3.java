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
@WebServlet("/idSearchTest3")
public class IdSearchTest3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO(); //mid는 멤버아이디라 멤버DAO
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		String name="";
		String nickName ="";
		String gender = "";
		int point = 0; 
		
		if(vo == null) {
			name="찾는 자료가 없습니다.";
		}
		else {
			//값을 다 vo에서 읽어서 넣어줘야함
			name= vo.getName();
			nickName= vo.getNickName();
			gender = vo.getGender();
			point = vo.getPoint();
		}
		
		String str = mid + "/" + name + "/"  + nickName + "/"  + gender + "/"  + point ;
		
		response.getWriter().write(str); //현재창에다가 str을 쓰겠다
	
		
	}
}
