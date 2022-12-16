package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoDeleteOkCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 본인 이외에 다른사람이 idx알아내서 주소타고 들어가서 글을 삭제처리할수도있음(그걸 못하게하기)
		//정상적인 경로를 통해서 삭제하지 않았을경우를 대비해서 세션아이디와 현재 글의 아이디를 비교해서 같으면 삭제, 그렇지 않으면 홈으로 돌려보낸다.
		HttpSession session = request.getSession();
		String sMid = (String) session.getAttribute("sMid"); //세션에있는 아이디(로그인한 아이디)
		int sLevel = (int) session.getAttribute("sLevel");
		String mid = request.getParameter("mid"); //vo에있는 아이디
		
		if(sLevel != 0) { //관리자가 아닐때
			if(!sMid.equals(mid)) { //로그인한 아이디와 게시글아이디가 다르면
				request.setAttribute("msg", "userCheckNo");
				request.setAttribute("url", request.getContextPath()+"/"); //루트로 보냄
				return;				
			}
		}
		
		
		//null값처리
		int idx = request.getParameter("idx")==null? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null? 0 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null? 0 : Integer.parseInt(request.getParameter("pageSize"));
		
		BoardDAO dao = new BoardDAO(); //삭제는 무조건 DB처리해야되니까 dao생성
		
		
		// 현재글의 댓글이 있는지 확인후 삭제처리한다.
		ArrayList<BoardReplyVO> vos = dao.getBoReply(idx);
		if(vos.size() != 0) {
			request.setAttribute("msg", "boReplyDataOk");
			request.setAttribute("url", request.getContextPath()+"/boContent.bo?idx="+idx+"&pag="+pag+"&pageSize="+pageSize);
			return;
		}

		// 현재글의 댓글이 없다면 삭제처리한다.
		int res = dao.setBoDeleteOk(idx);

		
		if(res == 1) {
			request.setAttribute("msg", "boDeleteOk");
			request.setAttribute("url", request.getContextPath()+"/boList.bo?pag="+pag+"&pageSize="+pageSize); //삭제가 됐으면 게시글 리스트로감
		}
		else {
			request.setAttribute("msg", "boDeleteNo");
			request.setAttribute("url", request.getContextPath()+"/boContent.bo?idx="+idx+"&pag="+pag+"&pageSize="+pageSize); //삭제가 안됐으면 다시 글 내용보기
		}
	}

}
