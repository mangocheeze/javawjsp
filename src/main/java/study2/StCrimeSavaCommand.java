package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.api.CrimeDAO;
import study2.api.CrimeVO;

public class StCrimeSavaCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poilce = (request.getParameter("police")==null) ? "": request.getParameter("police"); 
		int year = (request.getParameter("year")==null || request.getParameter("year").equals("")) ? 0 : Integer.parseInt(request.getParameter("year")); //null값과 공백처리를 같이해줘야함
		int burglar = (request.getParameter("burglar")==null || request.getParameter("burglar").equals("")) ? 0 : Integer.parseInt(request.getParameter("burglar"));
		int murder = (request.getParameter("murder")==null || request.getParameter("murder").equals("")) ? 0 : Integer.parseInt(request.getParameter("murder")); 
		int theft = (request.getParameter("theft")==null || request.getParameter("theft").equals("")) ? 0 : Integer.parseInt(request.getParameter("theft")); 
		int violence = (request.getParameter("violence")==null || request.getParameter("violence").equals("")) ? 0 : Integer.parseInt(request.getParameter("violence"));

		CrimeVO vo = new CrimeVO();
		 
		vo.setYear(year);
		vo.setPolice(poilce);
		vo.setBurglar(burglar);
		vo.setMurder(murder);
		vo.setTheft(theft);
		vo.setViolence(violence);
		
		CrimeDAO dao = new CrimeDAO();
		
		dao.setCrimeInputOk(vo); //메세지 띄우면 보낼때마다 떠서 너무많이뜨니까 메세지 안띄우고끝내버리기
		
	
	
	
	
	}

}
