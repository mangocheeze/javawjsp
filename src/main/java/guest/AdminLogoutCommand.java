package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogoutCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		session.removeAttribute("sMid"); //끊는거임, 근데 이걸끝내면 회원가입했을때 같은 변수 sMid를 쓰니까 일반회원 아이디가 끊겨버림
		session.removeAttribute("sAMid"); //그래서 변수를 sAMid로 해줌

		request.setAttribute("msg", "adminLogoutOk");
		request.setAttribute("url", request.getContextPath()+"/adminLogin.gu"); 
	}

}
