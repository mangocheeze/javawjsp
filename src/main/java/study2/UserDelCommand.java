package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.ajax.UserDAO;
import study2.ajax.UserVO;

public class UserDelCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		UserDAO  dao = new UserDAO(); //mid는 멤버아이디라 멤버DAO
		
		String res = dao.setUserDel(mid); //이게 정상적이라면 당연히 지워지게됨,혹시나 안지워졌을땐 다른변수를주게함 1:지워진거 0:안지워진거
		
		response.getWriter().write(res); //숫자로 넘겨도 어짜피 문자로가니까 상관없는데 가끔가다가 400번에러날때도 있어서 String 으로 받음
	

	}

}
