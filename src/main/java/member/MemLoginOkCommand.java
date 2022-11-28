package member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력받은거 null값 처리 꼭하기
		String mid = request.getParameter("mid") == null? "": request.getParameter("mid");
		String pwd = request.getParameter("pwd") == null? "": request.getParameter("pwd");
		String idCheck = request.getParameter("idCheck") == null? "": request.getParameter("idCheck");
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getLoginCheck(mid); //dao에 사용자가 입력한 mid를 가지고가서 LoginCheck를 읽어옴 거기서 자료가있으면 vo로넘긴걸 vo에담음
		//id와 pwd두개다 넘기는건 로그인할때만쓰니까, 하나 만들어놓고 다른곳에서도 계속사용하고싶어서 mid만 넘김

		if(vo == null || !pwd.equals(vo.getPwd())) { //vo가 null이면 자료가 없는거임, null이거나 pwd가 vo에있는 pwd와 같지 않으면
			request.setAttribute("msg", "loginNo"); //loginNo메세지처리
			request.setAttribute("url", request.getContextPath()+"/memLogin.mem"); //자료가없으면 메세지처리후 다시 로그인하러감
			return; //계속 진행되면 안되니까 끊어주기
		}

		
		//로그인 성공시 처리할 부분(1.주요필드를 세션에 저장, 2.오늘방문횟수처리, 3.방문수와 방문포인트증가,  4.쿠키에 아이디저장유무)
		//1.주요필드를 세션에저장( 꼭 필요한것만 넣기)
		HttpSession session = request.getSession(); //이걸써야 세션사용가능
		
		//세션에저장
		session.setAttribute("sMid", mid); //입력받은 mid를 sMid에 담음
		session.setAttribute("sNickName", vo.getNickName()); //닉네임저장이유: 화면에다 계속 뿌리고다닐거라(다른건 다 비공개여도 닉네임은 꼭 공개하게끔했어서)
		session.setAttribute("sLevel", vo.getLevel()); //등급별로 할수있는 화면이 달라지니까
		
		//2.오늘방문횟수 ~ 3.방문수와 방문포인트증가
		Date now = new Date(); //Date생성(오늘) util에있는걸로 import걸기!
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜형식으로 생성
		String strNow = sdf.format(now); //오늘을 날짜형식으로해서 strNow변수에담음 (strNow = 오늘날짜)
		
		//오늘 처음 방문시는 오늘 방문카운트(todayCnt)를 0으로 셋팅한다
		if(!vo.getLastDate().substring(0,10).equals(strNow)) { //vo에있는 최종(마지막)방문날짜를 0번째부터 10번째앞 자리까지 꺼낸거하고 strNow(오늘날짜)와 같지 않을경우 오늘처음방문했다는뜻
			dao.setTodayCntUpdate(mid); //dao에 mid를 가지고가서 오늘 방문카운트 0 으로셋팅처리한걸 저장
			vo.setTodayCnt(0); //첫방문이였을경우 오늘방문횟수를 0으로 세팅
		}
		
		//재방문시
		int nowTodayPoint = 0;
		if(vo.getTodayCnt() >= 5) { //vo에있는 오늘 방문한횟수가 5보다 크거나 같으면
			nowTodayPoint = vo.getPoint(); //기존걸 그냥 업데이트(더이상 포인트늘어나지않게함)
		}
		else { //5보다 작을경우
			nowTodayPoint = vo.getPoint() + 10; //기존거에다가 +10하고 넘겨주자
		}
		
		//오늘 재방문이라면 '총방문수','오늘방문수','포인트' 누적처리
		//dao.setMemTotalUpdate(mid);
		dao.setMemTotalUpdate(mid, nowTodayPoint); //dao에 누적처리한걸 저장(아이디와, 위에서 받은포인트)가지고감
		
		//4.쿠키에 아이디저장유무
		Cookie cookieMid = new Cookie("cMid", mid ); //쿠키생성
		if(idCheck.equals("on")) { //idCheck가 on으로 넘어왔으면 아이디저장에 체크함 => 쿠키를 저장
			cookieMid.setMaxAge(60*60*24*7); //쿠키저장만료시간을 일주일로줌
		}
		else { //on이 아니라면
			cookieMid.setMaxAge(0); //쿠키저장만료시간을 0으로줌(쿠키에저장x)
		}
		response.addCookie(cookieMid); //response에 금방만들었던 쿠키를 저장
		
		//저장소 request에 저장
		request.setAttribute("msg", "loginOk");
		request.setAttribute("url", request.getContextPath()+"/memMain.mem");
		request.setAttribute("val", vo.getNickName());
		
		
    
	}

}
