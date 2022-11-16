package j1111;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/hwOk")
public class HwOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		
		String name= request.getParameter("name");
		int age= Integer.parseInt(request.getParameter("age"));
		String birthday= request.getParameter("birthday");
		String phone= request.getParameter("phone");
		String email= request.getParameter("email");
		String mid= request.getParameter("mid");
		String pwd= request.getParameter("pwd");
		String gender= request.getParameter("gender");
		String[] hobbys= request.getParameterValues("hobby");
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("생년월일 : " + birthday);
		System.out.println("아이디 : " + mid);
		System.out.println("비밀번호 : " + pwd);
		System.out.println("전화번호 : " + phone);
		System.out.println("이메일 : " + email);
		System.out.println("성별 : " + gender);
		System.out.println("취미 : " + hobbys);
		for(String hobby : hobbys) { 
			System.out.println(hobby + "/");
		}
		
	}
}
