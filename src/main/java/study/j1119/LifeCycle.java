package study.j1119;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1119/LifeCycle")
public class LifeCycle extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("----이곳은 init() 메소드 입니다 ----");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----이곳은 service() 메소드 입니다 ----");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//필터에서 한글처리해서 한글처리안해도됨
		
		System.out.println("1.title : " +title);
		System.out.println("1.content : " +content);
		
		//forword를 이용한 전송
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1119LifeCycle/test1Res.jsp"); //여기의 루트(/)는 webapp
		dispatcher.forward(request, response);
	}
	
	@Override  //test1.jsp에서 post방식으로 폼 넘겨서 doPost가 필요 , doGet()은 필요없음)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----이곳은 doPost() 메소드 입니다 ----");
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				//필터에서 한글처리해서 한글처리안해도됨
				
				System.out.println("2.title : " +title);
				System.out.println("2.content : " +content);
				
				//Get방식으로의 전송
				response.sendRedirect(request.getContextPath()+"/study/1119LifeCycle/test1Res2.jsp?title="+title+"&content="+content); //get방식은 그냥 넘기면 안되고 값을 같이 넘겨야됨???
	}
	
	@Override
	public void destroy() {
		System.out.println("----이곳은 destory() 메소드 입니다 ----");
		
	}
	
}
