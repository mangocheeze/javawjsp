package study.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/MemberMain")
public class MemberMain extends HttpServlet{
	//얘가 왔다갔다하는 서블릿 역할을 함???
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디를 세션에 넣어놨기때문에 아이디를 꺼냄- 내가 로그인한상태면 아이디를 계속 쓸수있음
		HttpSession session = request.getSession();
		
		String mid =(String) session.getAttribute("sMid"); //세션에 있는 아이디 받음
		
		JusorokDAO dao = new JusorokDAO(); //생성
		
		JusorokVO vo= dao.getMemberSearch(mid); //아이디가 중복되어있으면 안됨(아이디중복체크가 필수가됨)

		request.setAttribute("point",vo.getPoint()); // point를 vo에서 불러온걸 request에담음
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/member.jsp"); //여긴 루트가 webapp (컨트롤러를 통해서 보냄???)
		dispatcher.forward(request, response); //forward로 실어서 보냄
	
	
	}
}
