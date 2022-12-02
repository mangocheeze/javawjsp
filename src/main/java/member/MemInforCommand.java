package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminInterface;

public class MemInforCommand implements MemberInterface, AdminInterface { //다중인터페이스구현

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		int pag = request.getParameter("pag")=="" ? 1 : Integer.parseInt(request.getParameter("pag")); //페이지도받음.페이지가 넘어오지않았으면 1페이지, 넘어왔으면 integer처리해서 페이지
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getLoginCheck(mid);
		
		String strLevel = "";
		if(vo.getLevel()== 0) strLevel ="관리자"; // vo에있는 level이 0 이면 strLevel 변수에 관리자를담음
		else if(vo.getLevel() == 1) strLevel = "준회원";
		else if(vo.getLevel() == 2) strLevel = "정회원";
		else if(vo.getLevel() == 3) strLevel = "우수회원";
		else if(vo.getLevel() == 4) strLevel = "운영자";
		
		request.setAttribute("pag", pag);
		request.setAttribute("strLevel", strLevel);
		request.setAttribute("vo", vo);

	}

}
