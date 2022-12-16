package study2.calendar;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class Calendar1Command implements StudyInterface {

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
		
		
		// 화면에 보여줄 달력의 해당 내역을 저장소에 저장한다.
		request.setAttribute("yy", yy);
		request.setAttribute("mm", mm);
		request.setAttribute("startWeek", startWeek);
		request.setAttribute("lastDay", lastDay);
		
		// 오늘 날짜를 저장소에 담아서 넘겨준다.
		request.setAttribute("toYear", toYear); //yy하고 비교하면됨
		request.setAttribute("toMonth", toMonth); //mm하고 비교
		request.setAttribute("toDay", toDay);
	}

}
