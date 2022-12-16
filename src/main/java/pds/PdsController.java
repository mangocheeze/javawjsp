package pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.pds")
public class PdsController extends HttpServlet{
	@Override
	// public 대신 protected 로 줘도됨 : 같은패키지안 ,
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdsInterface command = null;
		String viewPage = "/WEB-INF/pds";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		// 세션이 끊겼다면 작업의 진행을 중지시키고 홈으로 전송시켜준다 (그래야 중간에 에러가안남) -로그아웃되거나 작업하다 중간에 세션이 끊겼을때도 루트로 보냄
		HttpSession session = request.getSession(); //세션을 사용
		int level = session.getAttribute("sLevel") == null ? 99 : (int) session.getAttribute("sLevel"); 
		//세션에 level과, 아이디, 닉네임을 담아놨는데 세션에있는 레벨이 null이되면(세션이끊겼으면) 임의로 99로줌 그게아니라면 세션에 담겨있는 레벨을 줌(레벨이 회원이여야지만 게시판을 보이게설정해놨으니까 레벨이뭐인지가 중요함)
		
		if(level >= 4) { //위에서 세션이 끊겼으면 레벨이 99가 되니까 여기를 무조건 옴 => 우리회원이 아니거나, 정상적인 로그인상태가아니면
			RequestDispatcher dispatcher = request.getRequestDispatcher("/"); 
			dispatcher.forward(request, response);
			//우리 사이트의 루트로 보냄 (그럼 다시로그인해줘야함) - 지금 게시판은 애초에 로그인을 해줘야지만 보이는거니까 이렇게 맨위에 써줌 MemberController는 세션끊기면홈으로 보내는처리를 로그인이필요한부분부터 써줌
		}
		else if(com.equals("/pdsList")) {
			command = new PdsListCommand();
			command.execute(request, response);
			viewPage += "/pdsList.jsp";  
		}		
		else if(com.equals("/pdsInput")) {
			command = new PdsInputCommand();
			command.execute(request, response);
			viewPage += "/pdsInput.jsp";  
		}		
		else if(com.equals("/pdsInputOk")) {
			command = new PdsInputOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";  
		}
		else if(com.equals("/pdsDelete")) {
			command = new PdsDeleteCommand();
			command.execute(request, response);
			return;  
		}
		else if(com.equals("/pdsContent")) {
			command = new PdsContentCommand();
			command.execute(request, response);
			viewPage += "/pdsContent.jsp";
		}
		else if(com.equals("/pdsDownNum")) {
			command = new PdsDownNumCommand();
			command.execute(request, response);
			return;  //ajax니까 return
		}
		else if(com.equals("/pdsPwdCheck")) {
			command = new PdsPwdCheckCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";  
		}
		else if(com.equals("/pdsTotalDown")) {
			command = new PdsTotalDownCommand();
			command.execute(request, response);
			return;  
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
	
}
