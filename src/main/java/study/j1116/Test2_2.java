package study.j1116;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/j1116/Test2/2")
public class Test2_2 extends HttpServlet{
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
		char grade;
		
		switch (intAvg) {
			case 10:
			case 9:
				grade = 'A';
				break;
			case 8:
				grade = 'B';
				break;
			case 7:
				grade = 'C';
				break;
			case 6:
				grade = 'D';
				break;
			default:
				grade = 'F';
		}
		
		//아래는 브라우저에 처리 결과를 찍는작업
		PrintWriter out = response.getWriter();
		
		/*
		out.print("<h2>성 적 확 인</h2>");
		out.print("학번 : "+hakbun+"<br/>");
		out.print("성명 : "+name+"<br/>");
		out.print("국어 : "+kor+"<br/>");
		out.print("영어 : "+eng+"<br/>");
		out.print("수학 : "+mat+"<br/>");
		out.print("사회 : "+soc+"<br/>");
		out.print("과학 : "+sci+"<br/>");
		out.print("총점 : "+tot+"<br/>");
		out.print("평균 : "+avg+"<br/>");
		out.print("학점 : "+grade+"<br/>");
		out.print("<hr/>");
		out.print("<p><a href='"+request.getContextPath()+"/study/1116/test2.jsp'>돌아가기</a></p>");
		*/
		
		//서버에서 get방식으로의 전송방법 (이것도 하나하나쓰는거-좋은방식아님)
		response.sendRedirect(request.getContextPath()+"/study/1116/test2Ok.jsp?hakbun="+hakbun+"&name="+name+"&kor="+kor+"&eng="+eng+"&mat="+mat+"&soc="+soc+"&sci="+sci+"&tot="+tot+"&avg="+avg+"&grade="+grade);  
		//jsp파일로 보낼거임
		//forward 는 아무처리안해도 값을 가지고가는데 sendRedirect는 처리를 해야 보내짐
	}
}
