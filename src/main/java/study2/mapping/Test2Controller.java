package study2.mapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/mapping/Test2") 
public class Test2Controller extends HttpServlet{ //HttpServlet은 내장객체라 다른곳에서 파일을 안만들어놔도 생성할수있다
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/mapping/Test2 서블릿입니다."); //에러나서 찍어봄, 이게 콘솔에 뜨면 잘통과했다는거임
		
		Test2Ok t2 = new Test2Ok(); //Test2Ok.java ( commad객체를 생성)
		//다른파일에 작성된 어떤값을(메소드...등) 불러오기위해선 생성을 해야함, int su=100;처럼 객체 타입은 클래스이름이되고 new로 생성
		t2.message();  //t2에 있는 메세지를 메소드를 띄우게함? message()메소드를 불러오려면 변수. 으로 불러옴
		
		
		Test2OkOk t2Ok = new Test2OkOk(); //Test2OkOk.java ( commad객체를 생성)
		t2Ok.message();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test1.jsp"); //jsp를 호출
		dispatcher.forward(request, response);
		
	}
}
