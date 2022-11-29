package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;

public class MemPwdCheckOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null? "" : request.getParameter("pwd");
		
		
		//비밀번호를 암호화해서 받아야함
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getLoginCheck(mid); //아이디가지고 먼저 해당정보를 가져옴
		
		if(!vo.getPwd().equals(pwd)) { //vo에 있는 비밀번호하고 내가 적은 비밀번호하고 다르면
			request.setAttribute("msg", "passwordNo");
			request.setAttribute("url", request.getContextPath()+"/memPwdCheck.mem");
		}
		else { //vo에있는거랑 내가 입력한 비밀번호가 같으면
			request.setAttribute("msg", "passwordYes");
			request.setAttribute("url", request.getContextPath()+"/memUpdate.mem");
			
		}

	}

}
