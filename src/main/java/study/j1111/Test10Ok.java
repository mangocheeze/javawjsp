package study.j1111;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test10Ok")
public class Test10Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");//response : 응답할때 /한글로 입력하고 전송눌렀을때 한글깨지는거 처리
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); //문자를 입력받아온걸 숫자로 받아야하니까 형변환
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
	}
}
