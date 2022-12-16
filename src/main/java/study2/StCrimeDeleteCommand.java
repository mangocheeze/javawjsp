package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.api.CrimeDAO;

public class StCrimeDeleteCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CrimeDAO dao = new CrimeDAO();
		
		String res = dao.setCrimeDeleteOk(); //메세지 띄울거라 res로 받아옴
		
		response.getWriter().write(res);//바로 넘겨버림
		
	}

}
