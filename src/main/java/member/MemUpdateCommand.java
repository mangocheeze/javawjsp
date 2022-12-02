package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemUpdateCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getLoginCheck(mid);
		
		/*memUpdate.jsp 폼에 출력을 위한 분리작업처리 (이메일,전화번호, 주소 , 취미)*/
		//email분리(@)
		String[] email = vo.getEmail().split("@");
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);
		
		//전화번호분리(-)
		String[] tel = vo.getTel().split("-");
		request.setAttribute("tel1", tel[0]);
		request.setAttribute("tel2", tel[1].trim());
		request.setAttribute("tel3", tel[2].trim());
		
		//주소분리(/)
		String[] address = vo.getAddress().split("/");
		request.setAttribute("postcode", address[0].trim()); //우편번호
		request.setAttribute("roadAddress", address[1].trim()); //
		request.setAttribute("detailAddress", address[2].trim());
		request.setAttribute("extraAddress", address[3].trim());
		
		// 취미는 통째로 넘긴다.
		request.setAttribute("hobby", vo.getHobby());
		
		//생일(년-월-일) : 앞에서부터 10자리를 넘긴다
		request.setAttribute("birthday", vo.getBirthday().substring(0,10));
		
		//나머지 내용들을 vo에 담아서 전송한다.
		request.setAttribute("vo", vo);
		

	}

}
