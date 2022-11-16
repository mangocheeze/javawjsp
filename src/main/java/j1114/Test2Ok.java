package j1114;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j1114_Test2Ok")
public class Test2Ok extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//test2.jsp 입력창에서 받아온값 관리자인지 확인처리
		
		//null값 처리
		/* 방법1
		String mid ="";
		if(request.getParameter("mid") != null) { //mid가 null이 아니면 
			mid = request.getParameter("mid"); //mid에 읽어온걸 넣어라
		}
		else {
			String mid=""; //null이면 공백으로 넣어라
		}
		*/
		System.out.println("전송방식 : " + request.getMethod()); //여기선 post방식으로 나옴(test2.jsp에서 post방식으로 넘겼으니까)
		
		//2번째방법 삼항연산자 (이걸추천) 
		//(조건문 ? 참일경우실행할표현식 : 조건문이 거짓일경우실행하는표현식)
		String mid = request.getParameter("mid") == null ? "" :request.getParameter("mid"); //넘어온id가 null이랑 같으면 공백을 넣어주고 그게 아니면 읽어온 id를 넣어줘라.변수선언하면서 바로 받음
		String pwd = request.getParameter("pwd") == null ? "" :request.getParameter("pwd"); 
		String name = request.getParameter("name") == null ? "" :request.getParameter("name"); 
		String hostIp = request.getParameter("hostIp"); //무조건 넘어오기때문에 null값 체크안해도됨 
		
//		String mid = request.getParameter("mid");  //변수로 받는이유: 그냥쓰면 너무 길어지니까
//		String pwd = request.getParameter("pwd");
		
		mid = mid.trim();  //mid 입력칸의 공백을 잘라서 mid에 넣음(id에 공백을 입력했을경우 공백이있어도 상관없게하려고)
		
		PrintWriter out = response.getWriter(); //jsp라면 alert명령 바로쓰면되는데 servlet이니까 웹에 출력시키는 이 코드가 필요
		
		if(mid.equals("admin") && pwd.equals("1234")) { //변수의값을 넣고서 비교//&& 둘다 참이여야함
			out.println("<script>"); //ln붙여도되고 안붙여도됨
			out.println("alert('관리자 인증성공!!!');");
//			out.println("location.href='"+request.getContextPath()+"/study/1114/test2Res.jsp?mid="+mid+"';"); //test2Res.jsp 파일로 감 //get방식에다가 값을넣어서보내려면 화일명 과 변수명사이는?쓰기/(?)랑 변수명,값을 무조건써야함
			out.println("location.href='"+request.getContextPath()+"/study/1114/test2Res.jsp?mid="+mid+"&name="+name+"&hostIp="+hostIp+"';"); //test2Res.jsp파일로 mid와 name값을 가지고감
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('관리자 인증실패	~~~');");
			out.println("history.back();"); //뒤로가기
			out.println("</script>");
			
		}
	}
}
