package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GuListCommand implements GuestInterface {
	
	/* 페이징처리
	  1.페이지(pag)를 결정한다. 처음 접속시의 페이지는 무조건 1Page이다 : pag =1    //page는 예약어라 pag라고함 
	  2.한페이지의 분량을 결정한다(한 page건수) : PageSize = 5 (사용자(or 프로그래머)가 결정한다) 이곳에서는 한페이지 분량을 5로했다. pageSize = 5
	  3.총 레코드 건수를 구한다. totRecCnt => SQL함수중 count(*)을 사용한다
		4.총 페이지 건수를 구한다  totPage(총페이지건수) => totRecCnt(총레코드건수) % pageSize 값이 0이면 '몫', 0 이 아니면 '몫+1'
	 	5.현재페이지의 시작 인덱스 번호를 구한다 startIndexNo => (pag - 1) * pageSize
	 	6.현재 화면에 보여주는 시작 번호를 구한다. curScrStartNo => totRecCnt - startIndexN0
	 */
	
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서비스객체(command객체) 자료를 전체를 가져와야하는데 자기가 못가져오니까 DB한테 가져오라고하는거임

		GuestDAO dao = new GuestDAO();  //생성을해야지만 쓸수있음

		//1.페이지(pag)를 결정한다
		int pag = request.getParameter("pag")==null ? 1: Integer.parseInt(request.getParameter("pag")); //앞에서 page가 넘어옴
		
	  //2.한페이지의 분량을 결정한다(한 page건수)
		int pageSize = 5;  //사용자나 프로그래머가 정함 ( 사용자가 정할수있게 하는건 받아서해야되는데 지금은 그냥 5건으로 정해놈)
		
		//3.총 레코드 건수를 구한다
		int totRecCnt = dao.totRecCnt();
		//System.out.println("totRecCnt : " +totRecCnt ); 콘솔에 총레코드건수 찍어봄
		
		//4.총 페이지 건수를 구한다 
		int totPage = (totRecCnt % pageSize) == 0 ? totRecCnt  / pageSize : (totRecCnt/ pageSize) +1;
		
		//5.현재페이지의 시작 인덱스 번호를 구한다
		int startIndexNo = (pag - 1) * pageSize;
		
		//6.현재 화면에 보여주는 시작 번호를 구한다
		int curScrStartNo = totRecCnt - startIndexNo;
		
		
		//블록페이징처리 .....(3단계)  => 블록의 시작번호를 0번부터 처리함
		
		//1.블록의 크기를 결정한다(여기선 3으로지정)
		int blockSize = 3;
		
		//2.현재페이지가 위치하고있는 블록 번호를 구한다(예.1~3페이지는 0블록, 4~6페이지는1블록, 7~9페이지는 2블록)
//		int curBlock = (pag % blockSize) == 0 ? (pag % blockSize)-1 : (pag % blockSize); //나머지가 0일때 현재페이지 -1 그렇지않을땐(나머지가있을땐) 그냥 현재페이지가나옴
		int curBlock = (pag-1) / blockSize; //이게더 편함
		
		// 3. 마지막블록을 구한다
		//int lastBlock = (totPage % blockSize) == 0 ? (totPage / blockSize) -1 : (totPage / blockSize); //5페이지 나누기 블럭사이즈 3 = 몫이 1
		int lastBlock = (totPage - 1) / blockSize;//현재블록구하는거랑똑같음
		
		
//		ArrayList<GuestVO> vos = dao.getGuestList(); //vo에있는걸 여러개가져올거라 어레이리스트 , dao(DB랑연결해주는애) 에있는 GuestList메소드를 vos에 담겠다
		ArrayList<GuestVO> vos = dao.getGuestList(startIndexNo, pageSize); //이걸 안남기면 다가져오니까 필요한 두개 넘겨서 처리함
		
		
//		request.setAttribute("vos", vos); //request에 vos를 저장한내용을 다시컨트롤러로 보냄 , 컨트롤러가 일하라고 얘(command)객체를 불렀던거니까
		request.setAttribute("vos", vos); //이렇게하면 방명록리스트에 5건만 나오는거임 
		request.setAttribute("pag", pag); //현재페이지 이전페이지 하려고 넘겨야함
		request.setAttribute("totPage", totPage); //현재페이지 이전페이지 하려고 넘겨야함
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
	}

}
