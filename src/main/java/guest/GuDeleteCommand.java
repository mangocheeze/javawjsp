package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 삭제처리
public class GuDeleteCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")=="" ? 0 : Integer.parseInt(request.getParameter("idx")); //해커들이 null로 장난칠수도있으니
		
		
		GuestDAO dao = new GuestDAO();
		
		if(idx != 0) { //넘어온 idx가 0과 같지않으면 수행
			int res = dao.setGuDelete(idx); //아무나 지우면안됨 ,url에다가 고유번호 같이보내야함
			if(res == 1) { //숫자라 ==비교
				//이 두개를 쌍으로 보냄
				request.setAttribute("msg", "guDeleteOk"); 
			}
			else { //res가 1로오지않으면 비정상적으로 처리됐다는 뜻
				request.setAttribute("msg", "guDeleteNo");
			}
			request.setAttribute("url", request.getContextPath()+"/guList.gu"); 
			
		}
	}

}
