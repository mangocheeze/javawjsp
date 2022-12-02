package study2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.st")
public class StudyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudyInterface command = null;
		String viewPage = "/WEB-INF/study2";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		// 세션이 끊겼다면 작업의 진행을 중지시키고 홈으로 전송시켜준다 (그래야 중간에 에러가안남) -로그아웃되거나 작업하다 중간에 루트가 끊겼을때도 루트로 보냄
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel") == null ? 99 : (int) session.getAttribute("sLevel"); //임의로 99로줌
		
		if(level >= 4) { //우리회원도아니고, 정상적인게 아니면
			RequestDispatcher dispatcher = request.getRequestDispatcher("/"); //우리 사이트의 루트로 보냄
			dispatcher.forward(request, response);
		}

		else if(com.equals("/pass")) {
			viewPage += "/password/pass.jsp";  //밑에 password 밑에 pass.jsp 를 호출
		}		
		else if(com.equals("/passOk1")) {
			command = new PassOkCommand();
			command.execute(request, response);
			viewPage += "/password/pass.jsp"; //여기 콘솔에다가 출력하고 끝냄
		}
		else if(com.equals("/passOk2")) {
			command = new PassOk2Command();
			command.execute(request, response);
			viewPage += "/password/passOk2.jsp"; 
		}
		else if(com.equals("/ajax1")) {
			viewPage += "/ajax/ajax1.jsp"; 
		}
		else if(com.equals("/userList")) {
			command = new UserListCommand();
			command.execute(request, response);
			viewPage += "/ajax/userList.jsp"; 
		}
		else if(com.equals("/userSearch")) { //userList.jsp 개별조회 누르면 옴
			command = new UserSearchCommand();
			command.execute(request, response);
//			viewPage += "/ajax/userList.jsp"; //새로 불러버리니까 동기식(다른작업하다가 화면이 불러지니까) - 이러면안됨
			return; //작업을 끊어버림
		}
		else if(com.equals("/userDel")) { //삭제
			command = new UserDelCommand();
			command.execute(request, response);
			return; //작업을 끊어버림
		}
/*		else if(com.equals("/userInput")) { //유저등록
			command = new UserInputCommand();
			command.execute(request, response);
			return;
		}*/

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
	
}
