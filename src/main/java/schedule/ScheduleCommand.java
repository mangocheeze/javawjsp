package schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleCommand implements ScheduleInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오늘 날짜처리(저장)
		Calendar calToday = Calendar.getInstance(); //메모리에 있는걸 불러씀
		int toYear = calToday.get(Calendar.YEAR);
		int toMonth = calToday.get(Calendar.MONTH);
		int toDay = calToday.get(Calendar.DATE); //()안에는 무조건 클래스로 꺼내야함

		//화면에 보여줄 해당 '년/월'을 셋팅
		Calendar calView = Calendar.getInstance(); //화면에 보여주는거
		int yy = request.getParameter("yy")==null ? calView.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("yy"));
		int mm = request.getParameter("mm")==null ? calView.get(Calendar.MONTH) : Integer.parseInt(request.getParameter("mm"));
		
		// 앞에서 넘어온 변수 (yy, mm )값이 '1월'이거나 '12월'이라면 아래와 같이 편집한다.(1월은 '0' 12월은 '11'로 넘어온다)
		// 달력에서 1월이나 12월에서 이전월 다음월 누르면 실행 
		if(mm < 0) { //0은 1월임 1월보다 작으면
			yy --; //작년
			mm = 11; //1월 보다 작으면 작년 12월이니까 11
		}
		if(mm > 11) { // 11은 12월,12월보다크면 1월
			yy ++; //내년
			mm = 0; //1월
		}
		
		//해당 '년/월' 의 1일을 기준으로 셋팅시켜준다.
		calView.set(yy, mm, 1);
		
		//앞에서 세팅한 해당 '년/월'의 1일에 해당하는 요일값의 숫자로 가져온다(일: 1, 월:2, 화:3, ...)
		int startWeek = calView.get(Calendar.DAY_OF_WEEK); //.DAY_OF_WEEK => 함수, 해당월의 첫번째 1의 요일을 숫자로 반환
		//System.out.println("해당월의 첫번째 1의 요일을 숫자로 반환 " + startWeek);
		int lastDay = calView.getActualMaximum(Calendar.DAY_OF_MONTH); //해당월의 가장큰(마지막) 날짜를 넣어줌
		//System.out.println("해당월의 마지막날짜 :" + lastDay);
		
		// 출력된 달력의 '앞쪽/뒤쪽' 의 빈공간에 해당월 '이전/다음'의 날짜를 채워보자..(준비)
		int prevYear = yy; //이전
		int prevMonth = (mm) - 1;
		int nextYear = yy; //다음
		int nextMonth = (mm) + 1;
		
		if(prevMonth == -1) {
			prevYear--;
			prevMonth = 11; //12월
		}
		if(nextMonth == 12) {
			nextYear++;
			nextMonth = 0; //1월
		}
		//현재월(ex.11월)의 이전월(ex.10월)의 마지막날짜를 구한다
		Calendar calPre = Calendar.getInstance();
		calPre.set(prevYear, prevMonth ,1);
		int preLastDay = calPre.getActualMaximum(Calendar.DAY_OF_MONTH); //전 월의 마지막날짜를 가져옴
		
		//다음월의 1일에 해당하는 요일의 숫자값을 가져온다
		Calendar calNext = Calendar.getInstance();
		calNext.set(nextYear, nextMonth ,1);
		int nextStartWeek = calNext.get(Calendar.DAY_OF_WEEK);
		
		
		// 화면에 보여줄 달력의 해당 내역을 저장소에 저장한다.
		request.setAttribute("yy", yy);
		request.setAttribute("mm", mm);
		request.setAttribute("startWeek", startWeek);
		request.setAttribute("lastDay", lastDay);
		
		// 오늘 날짜를 저장소에 담아서 넘겨준다.
		request.setAttribute("toYear", toYear); //yy하고 비교하면됨
		request.setAttribute("toMonth", toMonth); //mm하고 비교
		request.setAttribute("toDay", toDay);
		
		// 현재달의 '전월/다음월'의 날짜 표시를 위한 변수를 저장소에 저장한다.
		request.setAttribute("prevYear", prevYear);
		request.setAttribute("prevMonth", prevMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("preLastDay", preLastDay);
		request.setAttribute("nextStartWeek", nextStartWeek);
		
		
		/* 스케줄에 등록되어 있는 해당월의 일정들을 가져온다. */
		// 자신의 스케줄만 가져와야 하기에, 로그인된 회원의 아이디와 일치한 자료만 가져온다.
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		// 해당월을 가져오기위한 편집처리(2022-12)
		
		//2023-1  => 2023-01로 바꾸는 처리
		String ym = "";
		if((mm +1 ) < 10) {
			ym = yy + "-0" + (mm + 1);
		}
		else {
			ym = yy + "-" + (mm + 1);
		}
		
		ScheduleDAO dao = new ScheduleDAO();
		ArrayList<ScheduleVO> vos = dao.getScMenu(mid, ym , 0); //초기화면이라 0
	
		request.setAttribute("vos", vos); 
		
		
	}

}
