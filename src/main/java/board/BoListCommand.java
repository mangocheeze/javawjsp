package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberVO;

public class BoListCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		// 페이징처리 준비 시작
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		//int pageSize = 5; //기본 페이지 글 5개씩 (5로고정)
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize")); //이렇게 변수로 해야 페이지 선택한대로 바뀜 ,정수로 바꿈
		int totRecCnt = dao.totRecCnt(); //전체건수(글 갯수)
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) +1 ; //전체글수 % 페이지사이즈(5건,10건,,,) 이 0이면 전체글수 / 페이지사이즈 를 보여줌(페이지사이즈보다 작을때 0이나옴-> 그냥 페이지없이 글그대로보여주면됨)
		int startIndexNo = (pag - 1) * pageSize;
		int curScrStartNo = totRecCnt - startIndexNo; //회원중간에 삭제했을때 인덱스 비어있지않게하기(구멍나있지않게)
		
		// 블록페이징처리.....(3단계) -> 블록의 시작번호를 0번부터 처리했다.
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;
		
		ArrayList<BoardVO> vos = dao.getBoList(startIndexNo, pageSize); //시작하는거부터 한페이지분량만큼
		
		
		request.setAttribute("vos", vos);
		request.setAttribute("pag", pag);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);		

	}

}
