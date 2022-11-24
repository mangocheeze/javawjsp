package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;

public class PassOk2Command implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");

		SecurityUtil security = new SecurityUtil(); //conn패키지에있는 SecurityUtil.java 쓰려고생성
		String shaPwd = security.encryptSHA256(pwd); //String으로 보냈는데 우리도pwd String으로 해서 괜찮음
		
		request.setAttribute("mid", mid);
		request.setAttribute("pwd", pwd);
		request.setAttribute("shaPwd", shaPwd);
	}

}
