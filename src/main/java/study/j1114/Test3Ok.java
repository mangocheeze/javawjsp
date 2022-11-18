package study.j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1114_test3Ok")
public class Test3Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] products = request.getParameterValues("product");
		
		String strProduct = "";
//		for(String product : products) {
//			strProduct += product.trim() + "/";
//		}

		//등록할상품이 공백이 있을때 /가 공백것도 나오는문제 해결
		for(int i=0; i<products.length; i++) {
			if(products[i].trim() != "") {
				strProduct += products[i].trim() + "/";
			}
		}
		PrintWriter out = response.getWriter();

		if(!strProduct.equals("")) { //공백이 들어갔을때
			strProduct = strProduct.substring(0,strProduct.length()-1);
		}
		else {
			out.println("<script>");
			out.println("alert('1개 이상의 상품은 등록하셔야 합니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.println("<p>등록하신 상품은?</p>");
		out.println("<p>"+strProduct+"</p>");
		out.println("<script>");
		out.println("alert('상품 등록 완료!');");
		out.println("</script>");
		out.println("<p><a href='"+request.getContextPath()+"/study/1114/test3.jsp'>돌아가기</a></p>");
	}
}
