package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import guest.GuestDAO;

public class MemMainCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid"); //String으로 받을건데 session은 객체니까 String으로 형변환
		
		//member테이블에있는거 가져와야하니까
		MemberDAO memDao = new MemberDAO();
		GuestDAO dao = new GuestDAO(); //방명록에 글쓴횟수 가져올때필요
		
		MemberVO vo = memDao.getLoginCheck(mid); // DAO가서 가져온걸 vo변수에 담음
		
		//레벨을 문자로 처리해서 넘겨준다
		String strLevel = "";
		if(vo.getLevel()== 0) strLevel ="관리자"; // vo에있는 level이 0 이면 strLevel 변수에 관리자를담음
		else if(vo.getLevel() == 1) strLevel = "준회원";
		else if(vo.getLevel() == 2) strLevel = "정회원";
		else if(vo.getLevel() == 3) strLevel = "우수회원";
		else if(vo.getLevel() == 4) strLevel = "운영자";
	
		//request에 저장
		request.setAttribute("point", vo.getPoint());
		request.setAttribute("lastDate", vo.getLastDate());
		request.setAttribute("todayCnt", vo.getTodayCnt());
		request.setAttribute("visitCnt", vo.getVisitCnt());
		request.setAttribute("strLevel", strLevel);
		
		//사용자가 방명록에 글쓴 횟수 가져오기..(숙제
		String name = vo.getName();
		int num = dao.getNumberGuest(name);
		request.setAttribute("cnt", num);
	}

}
