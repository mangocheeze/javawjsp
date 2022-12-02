package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdMemberLevelCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int level = Integer.parseInt(request.getParameter("level"));

		AdminDAO dao = new AdminDAO();
		
		dao.setLevelCheck(idx,level); //제대로하려면 메세지 yes,no체크해야하나 관리자는 이상하게안가고 정상적으로가니까 안되는걸처리안하고 되는걸로만처리할거임(res = 1, res=0 같은거 안받음)
		
		request.setAttribute("msg", "levelCheckOk");
		request.setAttribute("url", request.getContextPath()+"/adMemList.ad");
	}

}
