package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database/SearchMid")
public class SearchMid extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		JusorokDAO dao = new JusorokDAO();
		
		JusorokVO vo = dao.getMemberSearch(mid);
		
		PrintWriter out = response.getWriter();
		
		if(vo.getName() != null) {
			request.setAttribute("vo", vo);
			//저장소에 저장한걸 헤더에 싣는거임(정보를 요청해서 response정보를 보낸다는개념이다 -> 이걸 forward라고함)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1120_Database/memberSearch.jsp");
			dispatcher.forward(request, response); //request:저장소에 담은거. response:응답 
  	}
  	else {
  		out.println("<script>");
  		out.println("alert('찾고자 하는 아이디가 없습니다.!!');");
  		out.println("location.href='"+request.getContextPath()+"/study/1120_Database/member.jsp';");
  		out.println("</script>");
  	}
		
	}
}
