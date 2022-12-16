package guest;

import java.io.IOException;
import java.util.regex.Pattern;

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
		
		//이메일 유효성검사(정규식체크)
		String regEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		
		if(Pattern.matches(regEmail, email)) {
			vo.setEmail(email);
		}
		else {
			vo.setEmail("");
		}
		vo.setName(name);
//		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		
		int res = dao.setGuInput(vo); //dao에 위에서 담은 vo를 가져가서 DB에가서 저장시키고오라고한뒤 여기로돌아와서 처리유무를 res로받음 , 돌아와서 1이 들어오면 정상적으로 잘처리가됨 0이들어오면 비정상적처리
		
		if(res == 1) { //숫자라 ==비교
			//이 두개를 쌍으로 보냄
			request.setAttribute("msg", "guInputOk"); //application,session은 계속 남아있어서 안되고 pageContext는 자기페이지밖에안되니까 request에 담아야함
			request.setAttribute("url", request.getContextPath()+"/guList.gu"); //location.href로 갈주소를 여기다 적음
			//res가 1이되면 정상적으로 처리되어있다는거니까 guInputOk를 msg에 담고 guLisit.gu로감 -> guList.jsp로감 -> 글쓰기 누르면 guInput.gu로 감 -> guInput.jsp로감 -> guInputOk.gu로감 -> messgae.jsp로감 -> guInputOk msg인 방명록에 글이 등록되었습니다를 띄우고 url이 guList.gu로감
		}
		else { //res가 1로오지않으면 비정상적으로 처리됐다는 뜻
			request.setAttribute("msg", "guInputNo"); //application,session은 계속 남아있어서 안되고 pageContext는 자기페이지밖에안되니까 request에 담아야함
			request.setAttribute("url", request.getContextPath()+"/guInput.gu"); //다시 입력해야되니까 guInput 컨트롤러로감
		}
		
	}

}
