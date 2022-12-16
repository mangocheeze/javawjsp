package study2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.api.CrimeDAO;
import study2.api.CrimeTotalVO;

public class StCrimeTotalListCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CrimeDAO dao = new CrimeDAO();
		
		ArrayList<CrimeTotalVO> totalVos = dao.getCrimeTotalList("T");
		request.setAttribute("totalVos", totalVos);
		
		ArrayList<CrimeTotalVO> avgVos = dao.getCrimeTotalList("A");
		request.setAttribute("avgVos", avgVos);
	}

}
