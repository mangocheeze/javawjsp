package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스
public interface GuestInterface {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; //return 타입이 없으니 void /에러가 안나보이지만 나중에 남 그래서 예외처리 해줌 (throws ServletException, IOException)
	// 이렇게 여기서 request, response 해놓으면 다른데서 안적어도돼서??? 편함
}
