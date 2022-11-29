package guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.gu") //gu 확장자는 다 나에게 와라
public class GuestController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestInterface command = null; //인터페이스를 생성해야함 /command객체를 부름-그러려면 request,response를 만들어야되는데 이걸 인터페이스에서 미리정의해줌
		//이렇게 전역변수로 선언해놓으면 아래쪽에서 생성만해서 쓰면됨(command를 여러번 사용가능해짐)
		
		String viewPage = "/WEB-INF/guest";  //WEB-INF아래에 guest 뒤에 들어올경로는 아래에 if문에따라 바뀜,이코드는 jsp를 호출할때는 전혀문제가없으나 서블릿으로 호출할때는 문제가생김 (경로를 지워줘야함)
	
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));  //uri의 / 부터 . 앞에까지 잘라냄
		
		//방명록의 초기페이지 List만드는거임
		if(com.equals("/guList")) {  //guList : 방명록 리스트 , 서비스객체필요(할일을 시켜야되니까)(서비스단에는 command객체가있어야함)
			command = new GuListCommand(); //얘는 생성만하고/GuestInterface를 구현하는 구현(실행)클래스 생성
			command.execute(request, response); //일은 얘가하는거임 /command객체의 있는 execute메소드의 request와 response 를 같이 보냄
			
			//갔다가 돌아옴
			viewPage += "/guList.jsp"; //돌아와서 guList.jsp를 호출
		}
		else if(com.equals("/guInput")) { 
			//guList.jsp에서 글쓰기 누르면 guInput.gu부름 guInput:방명록에 글씀 , 글쓰기 누르는건 처리를따로하지않고 글쓰는 guInput.jsp로 가야되니까 command객체필요없음
			//누구나와서 글을쓰면 DB에올려야함(누구나라는건 form만띄우면되지 서비스객체를 만들필요없음)

//			command = new GuListCommand();  서비스객체가 필요없으니까 이것도 필요없음
//			command.execute(request, response); 
			viewPage += "/guInput.jsp"; 
		}
		else if(com.equals("/guInputOk")) { //앞에서 넘겼던것들을 받아서 DB에 저장하는 역할  
			command = new GuInputOkCommand(); //GuListCommand: 서비스단으로 일을 시켜야함
			command.execute(request, response); 
			
			//갔다가 돌아옴
			//viewPage = request.getContextPath()+"/guList.gu";  //guList라는 컨트롤러로 보내야함(보내기전에 자료를 읽어와야하니까)
			//이렇게 방명록등록버튼누르면 바로 보내지말고 대화식으로 사용자와 소통을하는게 더좋음(ex.글이 등록되었습니다)
			//왜 ? 방명록리스트에서 글쓰고 새로고침하면 계속 글쓴게 반복적으로 올려짐(그래서 메세지컨트롤러가 필요)
			
			//viewPage = request.getContextPath()+"/include/message.jsp";  //response면이렇게 앞에 써줘야함
			viewPage = "/include/message.jsp";  //RequestDispatcher라서 앞에꺼 뺌 /루트(webapp)밑에 include밑에 message.jsp를 부름
		}
		else if(com.equals("/adminLogin")) { //guList.jsp에서 관리자 누르면 
		viewPage += "/adminLogin.jsp"; 
		}
		else if(com.equals("/adminLoginOk")) { 
		command = new AdminLoginOkCommand(); 
		command.execute(request, response); 
		viewPage = "/include/message.jsp"; //message.jsp로보냄 , +=하면안됨 경로가안맞아짐
		}
		else if(com.equals("/adminLogout")) { 
			command = new AdminLogoutCommand(); 
			command.execute(request, response); 
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/guDelete")) { 
			command = new GuDeleteCommand(); 
			command.execute(request, response); 
			viewPage = "/include/message.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //viewPage즉 jsp로 보냄
		dispatcher.forward(request, response);
	}
	
}
