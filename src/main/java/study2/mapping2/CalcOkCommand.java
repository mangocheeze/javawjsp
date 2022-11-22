package study2.mapping2;
//얘가 구현하는애 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcOkCommand implements MappingInterface { //인터페이스를 implements 검(상속이랑 유사)

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러는 교통정리만했을뿐 넘어온 request랑 response를 얘한테 넘겨주기만한거임
		//얘가 진짜 작업을 시작하는거임 
		
		//이작업을 서비스객체에서 하는거임 서비스객체이름이 excute???
		int su1 = request.getParameter("su1") == null ? 0 : Integer.parseInt(request.getParameter("su1")); //Test4Calc.java에선 이 코드를 쓸수없어서 여기서 값을넘김(이것마저도 서비스객체에 넘겨야함)
		int su2 = request.getParameter("su2") == null ? 0 : Integer.parseInt(request.getParameter("su2")); 
		String opt = request.getParameter("opt")==null ? "" : request.getParameter("opt");
		
		int res= 0;
		if(opt.equals("+")) res = su1 + su2;
		else if(opt.equals("-")) res = su1 - su2;
		else if(opt.equals("*")) res = su1 * su2;
		else if(opt.equals("/")) res = su1 / su2;
		else res = su1 % su2;
		
		request.setAttribute("su1", su1);
		request.setAttribute("su2", su2);
		request.setAttribute("opt", opt);
		request.setAttribute("res", res);
	}

}
