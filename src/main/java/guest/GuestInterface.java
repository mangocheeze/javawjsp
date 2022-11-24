package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스

public interface GuestInterface {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; //return 타입이 없으니 void /에러가 안나보이지만 나중에 남 그래서 예외처리 해줌 (throws ServletException, IOException)
	//인터페이스를 만드는이유는 하나의 인터페이스로 여러개의 command객체를 관리하겠다는 뜻인데
	// 이렇게 여기서 request, response 를 정의해놓는거임. 이렇게 해놓으면 다른 command 객체에서 implement로 상속만 걸어놓으면 request 랑 response를 정의하지않고도 바로쓸수있음
	//request랑 response는 값을 담아서 요청하고 응답하는거임
}
