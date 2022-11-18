package study.j1116h;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/j1116h/Main","/M","/m"})
public class Main extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String sw = request.getParameter("sw")==null ? "main" : request.getParameter("sw"); //아무것도 안들어가면 main으로가라고해놓음
		
		String product = request.getParameter("product")==null ? "" : request.getParameter("product");
		int price = (request.getParameter("price").equals("") || request.getParameter("price")==null) ? 0 : Integer.parseInt(request.getParameter("price")); //null도비교,공백도비교 (이렇게 두개비교해도된다는걸 보여주기위해서씀)
		int su = request.getParameter("su").equals("") ? 0 : Integer.parseInt(request.getParameter("su"));
		int kumaek = price * su;
		
		ProductVO vo = new ProductVO(product, price, su, kumaek, sw); //들어온거로 만들어짐?
		
		//프론트 컨트롤러 흉내냄
		String viewPage = "/study/1116h2";
		if(sw.equals("input")) viewPage += "/input.jsp"; //누적한이유는 jsp의 위치를 위에 "/study/1116h" 에 누적시킴 
		else if(sw.equals("list")) viewPage += "/list.jsp";
		else if(sw.equals("photo")) viewPage += "/photo.jsp";
		else viewPage += "/main.jsp";
		
		request.setAttribute("vo", vo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); //sendRedirect사용불가 
	}
}
