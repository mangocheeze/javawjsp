package study.database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database/MemberList")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JusorokDAO dao = new JusorokDAO(); //앞에서 받는게없어도 이걸해줘야함
		
		ArrayList<JusorokVO> vos = dao.getMemberList(); //객체배열인 컬렉션 프레임워크 ArrayList사용- 1건이아니라서 (그래서 vo가아닌 vos로변수줌)
	
		request.setAttribute("vos", vos); //request에 담았으니까 무조건 RequestDispatcher 로 보내야함
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/memberList.jsp"); //회원전체조회한걸 새창에 띄움/루트가 webapp이됨
		dispatcher.forward(request, response);
	}

}
