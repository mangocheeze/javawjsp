package j1117;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//'공통변수할당값가져오기' 버튼 누르면 회사명,홈페이지 주소 출력

@WebServlet("/GlobalGreen")
public class GlobalGreen extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//web.xml 에 설정해둔 공통변수의 값들을 읽어온다
		String logoName = getServletContext().getInitParameter("logoName");
		String homeAddress =getServletContext().getInitParameter("homeAddress");
		
		System.out.println("lofoName : " + logoName); //회사명
		System.out.println("homeAddress : " + homeAddress); //홈페이지주소
		
		//저장소 request에 담아서 웹에 출력함
		request.setAttribute("logoName", logoName);
		request.setAttribute("homeAddress", homeAddress);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1117/init/green.jsp");
		dispatcher.forward(request, response);
	}
}
