package study2.mapping2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.calc")
public class CalcController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인터페이스 만듦 (이름은 마음대로)
		MappingInterface command = null; //command 변수 타입 MappingInterface 으로 인터페이스를 만들겠다 (*MappingInterface.java 를 만듦)
		
		String viewPage = "/WEB-INF/study2/mapping2"; //선언해줌 , 여기까진 공통부분
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));  //com 변수는 바꿀수있음 , /부터 .앞에까지(뒤에서부터)
		
		if(com.equals("/Calc")) { //Calc.calc로 오는건 얘한테 걸림 (view 호출)
			viewPage += "/calc.jsp"; //.jsp생략가능? , 위에 공통부분에서 마지막에 / 가없으니까 여기서 써주기
		}
		else if(com.equals("/CalcOk")) { //CalcOk.calc로 오는건 얘한테 걸림 (계산하는애 호출)
			
			//선언
			command = new CalcOkCommand(); //클래스를 만듦 이클래스는 인터페이스의 지배를 받아서처리 , 그래야 인터페이스가 정의해놓은 메소드를 사용할수있음
			// 이 클래스가 인터페이스를 implement 거는거임 , 그래서 excute를 다가져와서 만들어줌 얘가 구현하는 애
			//여기서 인터페이스 안쓰고 ()안에 request, response에 넣어서 안하는이유: 그렇게해도되나 요즘 추세가 업무분담을 하기위해서 인터페이스를 통해 해줌
			
			//사용
			command.excute(request, response); //받은걸 그대로 excute에게 토스함
			
			viewPage += "/calcOk.jsp"; 
		}
		else if(com.equals("/JuList")) { //JuList.calc로 오는건 얘한테 걸림 (회원리스트 호출)
			command = new JuListCommand(); 
			command.excute(request, response);  //JuListCommand.java에서 여기까지 끝낸거임
			viewPage += "/juList.jsp"; 
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); 
		dispatcher.forward(request, response);
	}
}
