package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoContentCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null? 0 : Integer.parseInt(request.getParameter("idx"));
		int pageSize = request.getParameter("pageSize")==null? 0 : Integer.parseInt(request.getParameter("pageSize"));
		int pag = request.getParameter("pag")==null? 0 : Integer.parseInt(request.getParameter("pag"));
		String flag = request.getParameter("flag")==null ? "" : request.getParameter("flag");
		String search = request.getParameter("search")==null ? "" : request.getParameter("search"); //옵션
		String searchString = request.getParameter("searchString")==null ? "" : request.getParameter("searchString"); //text
		
		BoardDAO dao = new BoardDAO();
		
		//조회수 증가하기 (들어갈때 증가시키기)
		// dao.setReadNumPlus(idx);
		
		// 글 조회수 1회 증가시키기.(조회수 중복방지처리 - 세션 사용 : 'board+고유번호'를 객체배열에 추가시킨다.)
		HttpSession session = request.getSession();
		ArrayList<String> contentIdx = (ArrayList) session.getAttribute("sContentIdx");
		if(contentIdx == null) {
			contentIdx = new ArrayList<String>();
		}
		String imsiContentIdx = "board" + idx;
		if(!contentIdx.contains(imsiContentIdx)) {
			dao.setReadNumPlus(idx); //처음들어왔으면 증가시킴
			contentIdx.add(imsiContentIdx); // board + idx를 추가
		}
		session.setAttribute("sContentIdx", contentIdx); //세션에 넣음
		
		
		BoardVO vo = dao.getBoContentSearch(idx);
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pag", pag);
		request.setAttribute("vo", vo);
		request.setAttribute("flag", flag);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
		
		
		//-----------
		
  	// 해당글에 좋아요 버튼을 클릭하였었다면 '좋아요세션'에 아이디를 저장시켜두었기에 찾아서 있다면 sSw값을 1로 보내어 하트색을 빨강색으로 변경유지하게한다.
		ArrayList<String> goodIdx = (ArrayList) session.getAttribute("sGoodIdx"); //있으면 읽어와라
		if(goodIdx == null) { // 없으면 만들어라
			goodIdx = new ArrayList<String>();
		}
		String imsiGoodIdx = "boardGood" + idx;
		if(goodIdx.contains(imsiGoodIdx)) { //contains () : 문자열 저장(포함)되어있나?  
			session.setAttribute("sSw", "1");
		}
		else { 
			session.setAttribute("sSw", "0");
		}
		
		//------------------
		
		//이전글과 다음글처리
		BoardVO preVo = dao.getPreNextSearch("pre", idx);  //이전글 vo (같은 dao에 스위치처럼 만들거임)
		BoardVO nextVo = dao.getPreNextSearch("next", idx);  //다음글 vo
		request.setAttribute("preVo", preVo);
		request.setAttribute("nextVo", nextVo);
	
		//------------------
		
		//입력된 댓글 가져오기
		ArrayList<BoardReplyVO> replyVos = dao.getBoReply(idx); //현재글의 idx를넘김 , 댓글이 한두개가아닐수있으니 arraylist
		request.setAttribute("replyVos", replyVos);
		
		
	}
}
