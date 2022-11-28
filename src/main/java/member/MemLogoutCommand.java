package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemLogoutCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nickName = (String) session.getAttribute("sNickName"); //~님 로그아웃되셨습니다하려고 세션에 있는 닉네임을 읽어와서 nickName변수에담음
		
		session.invalidate(); //세션날라감
		
		request.setAttribute("msg", "memLogoutOk");
		request.setAttribute("url", request.getContextPath()+"/memLogin.mem");
		request.setAttribute("val", nickName);

	}

}
