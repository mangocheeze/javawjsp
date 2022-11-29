package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;

public class MemUpdatePwdOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		String mid = (String) session.getAttribute("sMid");
		String oldPwd = request.getParameter("oldPwd")==null? "" : request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd")==null? "" : request.getParameter("newPwd");
		
		
		//비밀번호를 암호화해서 받아야함
		SecurityUtil security = new SecurityUtil();
		oldPwd = security.encryptSHA256(oldPwd);
		newPwd = security.encryptSHA256(newPwd);
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		if(!vo.getPwd().equals(oldPwd)) { //vo에 있는 비밀번호하고 내가 적은 기존비밀번호하고 다르면
			request.setAttribute("msg", "passwordNo");
			request.setAttribute("url", request.getContextPath()+"/memUpdatePwd.mem");
			return;
		}
		
		int res = dao.setMemUpdatePwdOk(mid,newPwd);
		
		if(res == 1) {
			request.setAttribute("msg", "passwordOk");
			request.setAttribute("url", request.getContextPath()+"/memMain.mem");
		}
		else {
			request.setAttribute("msg", "passwordOkNo");
			request.setAttribute("url", request.getContextPath()+"/memUpdatePwd.mem");
		}
	}

}
