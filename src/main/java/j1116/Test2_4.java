package j1116;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1116/Test2/4")
public class Test2_4 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String hakbun = request.getParameter("hakbun")==null ? "" : request.getParameter("hakbun");
		int kor = request.getParameter("kor")==null ? 0 : Integer.parseInt(request.getParameter("kor")); //앞은 문자로 오니까 그냥 비교 뒤는 국어점수라 형변환해줌 
		int eng = request.getParameter("eng")==null ? 0 : Integer.parseInt(request.getParameter("eng"));  
		int mat = request.getParameter("mat")==null ? 0 : Integer.parseInt(request.getParameter("mat"));  
		int soc = request.getParameter("soc")==null ? 0 : Integer.parseInt(request.getParameter("soc"));  
		int sci = request.getParameter("sci")==null ? 0 : Integer.parseInt(request.getParameter("sci"));  

		int tot = kor + eng + mat + soc + sci;
		double avg = tot / 5.0; //평균
		int intAvg = (int)(avg / 10.0);  //평균을 10으로나누기 (100점을 10으로 나누면 10) 그리고 이건 double로 나오니까 int로 형변환
		String grade;
		
		switch (intAvg) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "F";
		}
		
		//VO에 값 저장하기
		Test2VO vo = new Test2VO(name,hakbun,kor,eng,mat,soc,sci,tot,avg,grade); //vo생성하면서 vo에담음
		System.out.println("vo : " + vo);
		
		//서버에서 get방식으로의 전송방법
		//response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?hakbun="+hakbun+"&name="+name+"&kor="+kor+"&eng="+eng+"&mat="+mat+"&soc="+soc+"&sci="+sci+"&tot="+tot+"&avg="+avg+"&grade="+grade);  
		//response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok3.jsp?vo="+vo); //vo에담음(get방식-url타고 넘어가는건 타입무조건 String이됨) //jsp파일로 보낼거임 ,forward 는 아무처리안해도 값을 가지고가는데 sendRedirect는 처리를 해야 보내짐
		
		//저장소(request)를 활용한 forwarding (직진해서 간다)
		request.setAttribute("vo", vo); //setAttribute(객체변수를 담아서갈 변수,객체변수(vo같은거)):request객체에 값을 저장 /헷갈리니까 이름 같이줌 /=>request라는 저장소에 저장함
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/study/1116/test2Ok4_2.jsp"); //getRequestDispatcher()안에는 forwarding할 주소를적음(request에 담은걸 보낼주소)//getRequestDispatcher:???
		//dispatcher.forward(request, response); //request를 가지고 응답(response)해줌  /여기까지가 WAS가하는일 
		
		//위에 두줄을 한줄로 쓰는법, view를 변수에담아서 짧게만들기
		String viewPage = "/study/1116/test2Ok4_2.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
