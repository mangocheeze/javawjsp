package j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1114_hwOk")
public class hwOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //웹에 출력
		
		String[] pName = request.getParameterValues("pName"); //상품명
		String[] pPrice = request.getParameterValues("pPrice"); //가격
		String[] pNum = request.getParameterValues("pNum"); //수량
		
		int[] tot = new int[pName.length]; //배열의 길이는 상품명의 길이만큼
		
		int res =0;
		
		for(int i=0; i< pName.length; i++) {
			tot[i] = Integer.parseInt(pPrice[i]) * Integer.parseInt(pNum[i]); //한상품에대해 가격*수량= 총가격구하기
			res += tot[i]; //한상품에대한 총가격 누적시킴
		}
		out.println("<div class='container'>");
		out.println("<p>등록하신 상품에 대한 출력결과입니다</p>");
		for(int i=0; i<pName.length; i++) {
			out.println("<p>상품이름 : "+pName[i]+" |상품가격:"+pPrice[i]+" |상품수량 : "+pNum[i]+"</p>");
		}
		out.println("<p>총가격은 "+res+" 원입니다</p>");
		out.println("</div>");
	}
}
