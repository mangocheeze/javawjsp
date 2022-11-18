package study.j1117;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Green" , initParams = {@WebInitParam(name="mid",value="admin"), @WebInitParam(name="pwd",value="1234") , @WebInitParam(name="className",value="총무과")}) 
//urlPatterns :명령어/ ""이게 url패턴이야 /initParams: 변수와 값, 여러개를쓰면 {}쓰기/초기값을 주는거임
public class GreenInit extends HttpServlet{ //웹에서 실행하겠다
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = getInitParameter("mid"); //위에준 초기매개변수를 가져와서 mid에 넣음
		String pwd = getInitParameter("pwd");
		String className = getInitParameter("className");
		
		System.out.println("아이디 : " + mid);
		System.out.println("비밀번호 : " + pwd);
		System.out.println("부서명 : " + className); //콘솔에 출력
		
		// 저장소에 담아서 가져가야지만 EL표기법에 나옴 (vo만 넘길수있는게 아니라 변수도 넘길수있음)
		request.setAttribute("mid", mid);
		request.setAttribute("pwd", pwd);
		request.setAttribute("className", className);
		//위에 저장소에 담은걸 웹에출력
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1117/init/green.jsp");
		dispatcher.forward(request, response);
	}
}
