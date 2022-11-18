package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/LoginOk")
public class LoginOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid"); //삼항연산자
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd"); //삼항연산자
		
		JusorokDAO dao = new JusorokDAO(); //생성해야됨
		
		//로그인 체크
		JusorokVO vo = dao.loginCheck(mid, pwd);		
		//String name = dao.loginCheck(mid,pwd);
		
		HttpSession session = request.getSession(); //이걸써야 세션 사용가능
		PrintWriter out = response.getWriter();
		
		//보유중인 포인트나 방문일자 하려고 vo에서 가지고왔는데 앞으로의 작업에서 회원정보조회나,수정을 만들게되면 
		//거기로 갔다가 여기로 다시돌아오는걸 반복하는데 그럼 매번 데이터베이스에서 찾아서 여기에 넘겨야하는 불편함
		//그래서 얘만 불러주는 서블릿을 만들거임 그래서 서블릿을 불러서 걔한테 던져주면 서블릿은 데이터베이스에 갔다가 여기에 오게함
		if(vo.getName() != null) { //vo에 있는 이름이 null이아니면 로그인된거임
			//회원 인증후 처리(1:자주 사용하는 자료를 세션에 담는다 2:방문포인트를 10점씩 부가한다(단,1일 방문포인트는 50점까지만 허용함)3:최종방문 날짜를 현재 날짜시간으로 변경한다)
			
			//1:자주 사용하는 자료를 세션에 담는다 
			//얘네들은 세션에 있어서 아무때나 꺼낼수있음
			session.setAttribute("sMid", mid); //vo.getMid()로써도되고 안써도되고(이미 아이디 넘겨서)//아이디 저장할건데 세션을 이용(어플리케이션 저장소는 서버끝날때까지 생명주기가 있으니까 별로안좋음)
			session.setAttribute("sName", vo.getName()); //vo에 있는 이름을 읽어서 session에 담음
			session.setAttribute("sLastDate", vo.getLastDate().substring(0,19)); //2022-11-18 15:30:25.0에서 .0빼고 출력시키려고함 (19번째 앞에까지)
			
			//2:방문포인트 증가하기(10점씩,1일 최대50점까지)//3:최종방문 날짜업데이트
			//지금은 한번에 해줌 숙제할땐 따로따로해주기
			int visitPoint = vo.getPoint() + 10;
			dao.setVisitUpdate(visitPoint,mid); //아이디도 같이보내야됨(아무나 포인트줄순없으니)
			
			
			
			out.println("<script>");
			out.println("alert('"+vo.getName()+"님 로그인 되었습니다');");
			out.println("location.href='"+request.getContextPath()+"/database/MemberMain';"); //MemberMain창으로 보냄
			out.println("</script>");
			
			//위에 주석처리한거로 보내도 괜찮은데 response.sendRedurect 써보려고 함
			//response.sendRedirect(request.getContextPath()+"/database/MemberMain"); //서블릿으로 갈거임//얘가 대장임 /얘는 서블릿으로 바로보낼때 쓰는게 좋음
			
		}
		else {
			//회원 인증 실패시 처리
			out.println("<script>");
			out.println("alert('로그인 실패!!');");
			out.println("location.href='"+request.getContextPath()+"/study/1120_Database/login.jsp';");  //다시 로그인창으로보냄
			//location이니까 get방식사용-get방식이니까 따당/jsp로 보낼건데 지금은 컨트롤러 통해서 보내지않음-보안?에 안들어가있어서(다음주에배울거는 무조건 컨트롤러통해서가게할거임)그래서 컨트롤러 호출해서사용
			//historyback은 아무대나 쓰면안됨 (jsp -> jsp는 써도됨 근데 서블릿을 거쳐서오면 사용할수없음)
			//여기 쓰는 이게 reponse.sendRedirector? 랑 같은말임 (웹에서의 방식)
			out.println("</script>");
		}
		
	}
}
