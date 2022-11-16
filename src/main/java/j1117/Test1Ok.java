package j1117;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1117/Test1Ok")
public class Test1Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//원래 null값 비교해야되는데 시간없어서못함(원래해야함)
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String job = request.getParameter("job");
		String address = request.getParameter("address"); 
		
		/* 이건 vo에담아서 웹출력하는법을 안배웠을때 썼던거, 이렇게안하고 vo에 넣어서하는방법씀(vo에 담으면 이곳에서뿐만아니라 여기저기서 쓸수있으니까)
		  PrintWriter out = response.getWriter(); //웹에출력
		  
		  out.print("<p>성명 : " + name + "</p>"); out.print("<p>성별 : " + gender +
		  "</p>"); out.print("<p>나이 : " + age + "</p>"); out.print("<p>직업 : " + job +
		  "</p>"); out.print("<p>주소 : " + address + "</p>");
		  out.print("<p><a href='"+request.getContextPath()+
		  "/study/1117/jspaction/test1.jsp'>돌아가기</a></p>");
		 */
		
		//정상처리된 Data를 vo에 넣어준다
		Test1VO vo = new Test1VO();
		
		vo.setName(name);
		vo.setGender(gender);
		vo.setAge(age);
		vo.setJob(job);
		vo.setAddress(address);
		
		request.setAttribute("vo", vo);
		 
		request.getRequestDispatcher("/study/1117/jspaction/test1Ok.jsp").forward(request, response);
	}
}
