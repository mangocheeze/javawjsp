package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.bo")
public class BoardController extends HttpServlet{
	@Override
	// public 대신 protected 로 줘도됨 : 같은패키지안 ,
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;
		String viewPage = "/WEB-INF/board";
		
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
		else if(com.equals("/boList")) {
			command = new BoListCommand();
			command.execute(request, response);
			viewPage += "/boList.jsp";  
		}		
		else if(com.equals("/boInput")) {
			command = new BoInputCommand();
			command.execute(request, response);
			viewPage += "/boInput.jsp";  
		}		
		else if(com.equals("/boInputOk")) {
			command = new BoInputOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";  
		}		
		else if(com.equals("/boContent")) {
			command = new BoContentCommand();
			command.execute(request, response);
			viewPage += "/boContent.jsp";  
		}		
		else if(com.equals("/boGood")) {
			command = new BoGoodCommand();
			command.execute(request, response);
			return; //돌아가는게있으면 동기식이됨 , 돌아가는게 없어야 비동기식  
		}		
		else if(com.equals("/boGoodPlusMinus")) {
			command = new BoGoodPlusMinusCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boDeleteOk")) {
			command = new BoDeleteOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";  
		}
		else if(com.equals("/boUpdate")) {
			command = new BoUpdateCommand();
			command.execute(request, response);
			viewPage += "/boUpdate.jsp";  
		}
		else if(com.equals("/boUpdateOk")) {
			command = new BoUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";  
		}
		else if(com.equals("/boSearch")) {
			command = new BoSearchCommand();
			command.execute(request, response);
			viewPage += "/boSearch.jsp"; //리스트와 성격이조금다름  
		}
		else if(com.equals("/boReplyInput")) { //댓글작성
			command = new BoReplyInputCommand();
			command.execute(request, response);
			return;  //ajax라서 
		}
		else if(com.equals("/boReplyDeleteOk")) {
			command = new BoReplyDeleteOkCommand();
			command.execute(request, response);
			return;
		}
		

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
	
}
