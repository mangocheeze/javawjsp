package j1117;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet("/Green")  web.xml에서 하니까 주석처리*/
//어노테이션이 없어도 부른다는건 컨트롤러를 web.xml에 만들었다는 소리
public class Green extends HttpServlet{ //웹에서 실행하겠다
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// web.xml 에 설정해둔 변수/값을 가져온다
		String mid = getInitParameter("mid");
		String pwd = getInitParameter("pwd");
		String className = getInitParameter("className");
		
		System.out.println("아이디 : " + mid);
		System.out.println("비밀번호 : " + pwd);
		System.out.println("부서명 : " + className); //콘솔에 출력
		
		//웹에출력. 저장소에 담아서 가져가야지만 EL표기법에 나옴 (vo만 넘길수있는게 아니라 변수도 넘길수있음)
		request.setAttribute("mid", mid);
		request.setAttribute("pwd", pwd);
		request.setAttribute("className", className);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1117/init/green.jsp");
		dispatcher.forward(request, response);
	}
}
