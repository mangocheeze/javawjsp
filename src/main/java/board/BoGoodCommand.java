package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoGoodCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		
		// 좋아요수 증가처리하기
		String sw = "0"; // 처음 0으로 셋팅하고, 처음 좋아요 버튼을 누르면 '1'을 돌려준다., 이미 '좋아요'를 한번 눌렀으면 '0'으로 sw값을 보내준다.
		HttpSession session = request.getSession();
		ArrayList<String> goodIdx = (ArrayList) session.getAttribute("sGoodIdx"); //객체배열 , 그전에있나없나 확인, 세션에 넣은 arraylist이름을 sGoodIdx라고 임의로줌 얘가없으면
		if(goodIdx == null) { //세션에 없다면
			goodIdx = new ArrayList<String>(); //만들어주세요
		}
		String imsiGoodIdx = "boardGood" + idx; //idx만 넘기면절대안됨, 좋아요가 게시판에있을수도있고 자료실에도있을수있음(다른테이블에서도 idx가중복이되니까 어디에있는 idx인지명확하게줘야함) - board에있는 좋아요의 idx다라고 함
		
		if(!goodIdx.contains(imsiGoodIdx)) { //세션 sGoodIdx에 imsiGoodIdx가 포함되어있는지 확인
			dao.setBoGood(idx);
			goodIdx.add(imsiGoodIdx);
			sw = "1";	// 처음으로 좋아요 버튼을 클릭하였기에 '1'을 반환
		}
		session.setAttribute("sGoodIdx", goodIdx);
		
		response.getWriter().write(sw);	// '좋아요'를 이미 눌렸을때 이곳으로 들어오면 처음설정값인 sw는 '0'을 반환, 처음 눌렀으면 '1'을 반환~~
	}

}
