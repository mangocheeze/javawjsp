package study.el_jstl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/study/el_jstl/El2")
public class El2 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] hobbys = request.getParameterValues("hobby"); //취미는 체크박스니까 여러개가 올수있어서 배열에 담음
		
		request.setAttribute("hobbys", hobbys); //request 저장소에 배열 저장함
		
		//페이지를 이동시킴
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1121_EL_JSTL/el2.jsp");
		dispatcher.forward(request, response); //해당주소에대해 요청하고 응답하라
	}
	
	
	
}
