package guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuInputOkCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name")==null?"" : request.getParameter("name");
		String email= request.getParameter("email")==null?"" : request.getParameter("email");
		String homePage= request.getParameter("homePage")==null?"" : request.getParameter("homePage");
		String content= request.getParameter("content")==null?"" : request.getParameter("content");
		String hostIp= request.getParameter("hostIp")==null?"" : request.getParameter("hostIp");
		
		//성명에는 태그(html코드) 사용못하게하기 (20자가 넘어가서(여기만해도 8글자) sql오류가뜸=>나중에 체크해줘야함)
		name= name.replace("<", "&lt;"); //lt : 레쓰덴(작다)
		name= name.replace(">", "&gt;"); //gt : 그레이트댄 (크다)
		
		GuestDAO dao = new GuestDAO();
		GuestVO vo = new GuestVO();
		
		vo.setName(name);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		
		int res = dao.setGuInput(vo); //vo를 넘기고 돌아오면 res로받음 , 돌아와서 1이 들어오면 정상적으로 잘처리가됨 0이들어오면 비정상적처리
		
		if(res == 1) { //숫자라 ==비교
			//이 두개를 쌍으로 보냄
			request.setAttribute("msg", "guInputOk"); //application,session은 계속 남아있어서 안되고 pageContext는 자기페이지밖에안되니까 request에 담아야함
			request.setAttribute("url", request.getContextPath()+"/guList.gu"); //location.href로 갈주소를 여기다 적음
		}
		else { //res가 1로오지않으면 비정상적으로 처리됐다는 뜻
			request.setAttribute("msg", "guInputNo"); //application,session은 계속 남아있어서 안되고 pageContext는 자기페이지밖에안되니까 request에 담아야함
			request.setAttribute("url", request.getContextPath()+"/guInput.gu"); //다시 입력해야되니까 guInput 컨트롤러로감
			
		}
		
	}

}
