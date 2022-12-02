package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;

public class BoInputCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		//닉네임, 이메일, 홈페이지 넘겨야되는데닉네임은 세션에 들어가있으니까 이메일,홈페이지만넘기면됨
		request.setAttribute("email", vo.getEmail());
		request.setAttribute("homePage", vo.getHomePage());
	}

}
