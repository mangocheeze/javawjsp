package study2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import study2.calendar.Calendar1Command;
import study2.calendar.Calendar2Command;
import study2.password.PassOk2Command;
import study2.password.PassOkCommand;
import study2.pdstest.DownLoadCommand;
import study2.pdstest.FileDeleteCommand;
import study2.pdstest.JavaDownCommand;
import study2.pdstest.UpLoad1OkCommand;
import study2.pdstest.UpLoad2OkCommand;
import study2.pdstest.UserListCommand;
import study2.pdstest.UserSearchCommand;

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
		else if(com.equals("/upLoad1")) { 
			viewPage += "/pdstest/upLoad1.jsp"; 
		}
		else if(com.equals("/upLoad1Ok")) {
			command = new UpLoad1OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp"; 
		}
		else if(com.equals("/upLoad2")) { 
			viewPage += "/pdstest/upLoad2.jsp"; 
		}
		else if(com.equals("/upLoad2Ok")) {
			command = new UpLoad2OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp"; 
		}
		else if(com.equals("/upLoad3")) { 
			viewPage += "/pdstest/upLoad3.jsp"; 
		}
		else if(com.equals("/upLoad4")) { 
			viewPage += "/pdstest/upLoad4.jsp"; 
		}
		else if(com.equals("/downLoad")) { //퍼블리셔방식
			command = new DownLoadCommand();
			command.execute(request, response);
			viewPage += "/pdstest/downLoad.jsp"; 
		}
		else if(com.equals("/javaDown")) { //옛날 백엔드 방식(ajax처리랑 똑같음)
			command = new JavaDownCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/fileDelete")) {
			command = new FileDeleteCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/calendar1")) {
			command = new Calendar1Command();
			command.execute(request, response);
			viewPage += "/calendar/calendar1.jsp"; 
		}
		else if(com.equals("/calendar2")) {
			command = new Calendar2Command();
			command.execute(request, response);
			viewPage += "/calendar/calendar2.jsp"; 
		}
		else if(com.equals("/stApi")) {
			viewPage += "/api/stApi.jsp"; 
		}
		else if(com.equals("/crimeApi")) {
			viewPage += "/api/crimeApi.jsp"; 
		}
		else if(com.equals("/stCrimeSava")) {
			command = new StCrimeSavaCommand();
			command.execute(request, response);
			return; 
		}
		else if(com.equals("/stCrimeDelete")) {
			command = new StCrimeDeleteCommand();
			command.execute(request, response);
			return; 
		}
		else if(com.equals("/stCrimeList")) {
			command = new StCrimeListCommand();
			command.execute(request, response);
			viewPage += "/api/crimeApi.jsp"; 
		}
		


		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
	
}
