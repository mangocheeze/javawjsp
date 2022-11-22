package study2.mapping;

public class Test4Calc {
	public int getCalc(int su1, int su2, String opt) { //전전에서 su1 su2 연산자 넘김 , void가 있으면 값을 못보내고 그냥 출력시키니까 int로 바꿈
		//int su1 = request.getParameter("su1") == null ? 0 : Integer.parseInt(request.getParameter("su1")); //커멘드 객체로 만드니까 서블릿으로 선언이 안된 일반클래스는 request 사용불가
		int res= 0;
		if(opt.equals("+")) res = su1 + su2;
		else if(opt.equals("-")) res = su1 - su2;
		else if(opt.equals("*")) res = su1 * su2;
		else if(opt.equals("/")) res = su1 / su2;
		else res = su1 % su2;
		
		return res;
	}
}
