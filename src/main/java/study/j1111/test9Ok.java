package study.j1111;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/test9Ok")
public class test9Ok extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //이게 service객체(get과 post방식
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); //문자를 입력받아온걸 숫자로 받아야하니까 형변환
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
	}

}
