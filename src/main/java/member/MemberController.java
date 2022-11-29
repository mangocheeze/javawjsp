package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.mem")
public class MemberController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInterface command = null; //인터페이스 생성하게함 (여기서 선언)
		
		String viewPage = "/WEB-INF/member"; //viewPage 초기값설정
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		if(com.equals("/memLogin")) {
			viewPage += "/memLogin.jsp";
		}
		else if(com.equals("/memLoginOk")) {
			command = new MemLoginOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp"; //memLogin.jsp보내기전에 message로 먼저보냄
		}
		else if(com.equals("/memLogout")) { //새로운소식이왔거나, 답장이왔거나 처리
			command = new MemLogoutCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memMain")) { //로그인되면 여기로옴. 새로운소식이왔거나, 답장이왔거나 처리
			command = new MemMainCommand();
			command.execute(request, response);
			viewPage += "/memMain.jsp";
		}
		else if(com.equals("/memIdCheck")) { //아이디 중복처리
			command = new MemIdCheckCommand();
			command.execute(request, response);
			viewPage += "/memIdCheck.jsp";
		}
		else if(com.equals("/memNickCheck")) { //닉네임 중복처리
			command = new MemNickCheckCommand();
			command.execute(request, response);
			viewPage += "/memNickCheck.jsp";
		}
		else if(com.equals("/memJoin")) {
			viewPage += "/memJoin.jsp";
		}
		else if(com.equals("/memJoinOk")) {
			command = new MemJoinOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memList")) { //닉네임 중복처리
			command = new MemListCommand();
			command.execute(request, response);
			viewPage += "/memList.jsp";
		}
		else if(com.equals("/memInfor")) { //전체회원리스트에서 아이디 누를경우처리
			command = new MemInforCommand();
			command.execute(request, response);
			viewPage += "/memInfor.jsp";
		}
		else if(com.equals("/memUpdatePwd")) {
			viewPage += "/memUpdatePwd.jsp";
		}
		else if(com.equals("/memUpdatePwdOk")) { 
			command = new MemUpdatePwdOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memPwdCheck")) { //회원정보 변경시 비밀번호확인 
			viewPage += "/memPwdCheck.jsp";
		}
		else if(com.equals("/memPwdCheckOk")) { //내용이있음
			command = new MemPwdCheckOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memUpdate")) { //내용이있음
			command = new MemUpdateCommand();
			command.execute(request, response);
			viewPage += "/memUpdate.jsp";
		}
		else if(com.equals("/memUpdateOk")) { //내용이있음
			command = new MemUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
