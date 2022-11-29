package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemListCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nav.jsp에서 정회원이상만 볼수있게 해놓음 , 정보 공개해놓은 회원만 보이게함 (단, 관리자는 다보이게해야함)
		HttpSession session = request.getSession();
		int level = (int) session.getAttribute("sLevel"); //객체를 int로 받을거라 int로형변환
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberVO> vos = dao.getMemList(level); 
		//숙제 getMemList페이징 처리
		request.setAttribute("vos", vos);
	}

}
