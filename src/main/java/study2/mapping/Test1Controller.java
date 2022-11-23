package study2.mapping;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/mapping/Test1") 
public class Test1Controller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/mapping/Test1 서블릿입니다."); //에러나서 찍어봄, 이게 콘솔에 뜨면 잘통과했다는거임
	
		//RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\study2\\mapping\\test1.jsp");  \를 /이것처럼 사용하겠다는게 \\   ,에러남???		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test1.jsp"); //test1.jsp를 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mapping/Test1Ok"); //Test1 컨트롤러가 Test1Ok 컨트롤러를 호출
		
		// 보통 / 이걸 많이씀 , ""에 이동하고자 하는 주소를적음 
		dispatcher.forward(request, response);
	}
}
