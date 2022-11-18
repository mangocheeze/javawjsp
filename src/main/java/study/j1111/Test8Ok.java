//class로 파일 만듦
package study.j1111;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atom/t7") //얘를 찾아감, 앞에 /는 content/경로 바로뒤에오는 /임
public class Test8Ok extends HttpServlet{
	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //request랑response글자 완성시키기
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //request랑response글자 완성시키기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); //문자를 입력받아온걸 숫자로 받아야하니까 형변환
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
	}
}
