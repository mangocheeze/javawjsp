//서블렛에선 파일의 경로를 절대 알수없음 (control역할인 WebServlet 을 통해서 가기때문에 )jsp는 파일경로가 다 드러남(url에)
package j1111;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/T2")
public class Test2 extends HttpServlet {
	//주소를 타고들어오는건 get방식(post방식외에는 다 get), post는 form태그에서 메소드타고가는방법 1개밖에 없음
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath()); //response: 응답을해서 알려주겠다/getWriter() :출력하겠다/append():()안에를 추가
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response); //doget 메소드호출 /외부에서 값을 넘기는데 get방식이나 post방식으로 넘길수있음/get방식으로 넘기면 위에 post로 넘기면 이 줄로 와서 doGet을 호출//get으로 넘기던 post로넘기던 무조건 get으로감
//	}

}
