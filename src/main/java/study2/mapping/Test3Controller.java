package study2.mapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/mapping/Test3")
@WebServlet("*.do") //확장자패턴 , 확장자 .do만 맞으면 내게로와라
//@WebServlet({"*.do","*.calc"}) //확장자패턴 .do 도 하고 calc도 하고 여러가지올수있음
public class Test3Controller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer url = request.getRequestURL(); //이걸로 너 어디서온거야 하고 찾음
		System.out.println("url :" + url);

		String uri = request.getRequestURI(); //도메인빠지고 context루트부터뜸 , 우린 앞으로 uri로 작업할거임(도메인관계없이 파일만든 경로부터하려고)
		System.out.println("uri :" + uri);
		
		//뒤에 .do만꺼내려고함 - substring으로 발취하고 indexof로 찾아냄, 뒤에서부터 /부터 가져와라 해서 last indexof
		//String com = uri.substring(uri.lastIndexOf("/")); //com은 마음대로 uri에있는거 찾아냄
		//System.out.println("com:" + com);

		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf(".")); // 뒤에서부터 /가있는 위치부터 . 앞까지
		System.out.println("com:" + com); //com이름은 마음대로줌

		PrintWriter out = response.getWriter();
		
		String viewPage = "/WEB-INF/study2/mapping"; //viewPage : jsp주소를 담고있는 변수
		//String viewPage = "/WEB-INF"; //이렇게만 적으면 파일명 신경안써도됨
		
//		if(com.equals("/Test3_1")) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/Test3_1"); 
//			dispatcher.forward(request, response);
//		}
		if(com.equals("/Test3_1")) { //com에 들어있는게 /Test3_1 이면
			viewPage += "/test3_1.jsp";
		}
		else if(com.equals("/Test3_2")) {
			viewPage += "/test3_2.jsp";
		}
		else if(com.equals("/Test3_3")) {
			viewPage += "/test3_3.jsp";
		}
		else if(com.equals("/Test4") || com.equals("/test4")) { //대문자나 소문자나 둘다 가능
			viewPage += "/test4.jsp"; //test4.jsp를 호출함
		}
		else if(com.equals("/Test4Ok")) { //test4에서 불러옴
			int su1 = request.getParameter("su1") == null ? 0 : Integer.parseInt(request.getParameter("su1")); //Test4Calc.java에선 이 코드를 쓸수없어서 여기서 값을넘김(이것마저도 서비스객체에 넘겨야함)
			int su2 = request.getParameter("su2") == null ? 0 : Integer.parseInt(request.getParameter("su2")); 
			String opt = request.getParameter("opt")==null ? "" : request.getParameter("opt");
			
			Test4Calc t4 = new Test4Calc();  //Test4Calc class 파일을 생성
			int res = t4.getCalc(su1, su2, opt); //Test4Calc.java호출한건 int res 로담음
			
			request.setAttribute("su1", su1);
			request.setAttribute("su2", su2);
			request.setAttribute("opt", opt);
			request.setAttribute("res", res);
			
			viewPage += "/test4Ok.jsp"; //test4Ok.jsp 뷰로 감
		}
		else {
//			out.println("<script>");
//			out.println("alert('잘못된 경로입니다');");
			//out.println("location.href='/WEB-INF/study2/mapping/test3.jsp';"); //get방식은 안감 . 무조건 컨트롤러로 가야함 서블릿에서의 (resposeRedirect랑 똑같은거임 dispatcher.forward랑?? 두개를 같이쓸수없어서)
//			out.println("locationo.href='"+request.getContextPath()+"mapping/Test3';"); //
//			out.print("</script>");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/study2/mapping/test3.jsp"); 
//			dispatcher.forward(request, response);
			viewPage += "/test3.jsp";
		}
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); 
		dispatcher.forward(request, response);
		

	}
}
