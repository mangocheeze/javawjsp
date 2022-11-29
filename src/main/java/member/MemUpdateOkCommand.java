package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemUpdateOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		String sNickName = (String) session.getAttribute("sNickName");
		
		
		String nickName = request.getParameter("nickName")==null ? "" : request.getParameter("nickName");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String birthday = request.getParameter("birthday")==null ? "" : request.getParameter("birthday");
		String tel = request.getParameter("tel")==null ? "" : request.getParameter("tel");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null ? "" : request.getParameter("homePage");
		String job = request.getParameter("job")==null ? "" : request.getParameter("job");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String userInfor = request.getParameter("userInfor")==null ? "" : request.getParameter("userInfor");
		
		//취미 전송에 대한처리 (배열)
		String[] hobbys = request.getParameterValues("hobby");
		String hobby = "";
		if(hobbys.length != 0) { //0이아닐때 = 하나라도 선택했을때
			for(String strHobby : hobbys) {
				hobby += strHobby + "/"; //hobby를 누적 /로 구분
			}
		}
		hobby = hobby.substring(0,hobby.lastIndexOf("/"));
		
		//회원 사진이 업로드 되었는지의 여부 처리
		String fileSystemName = "";
		String photo = request.getParameter("photo");
		if(photo.equals("noimage")) {
			fileSystemName = "noimage.jpg";
		}
		else {
			fileSystemName = photo; //사용자가 사진을 넘겼으면 넘긴화일명을 줘야함
		}
		
		//DB에 저장시, 테이블설계에서 지정한 각 필드의 길이체크..(숙제)
		
		//아이디와 닉네임을 다시한번 중복체크 해준다. (DB에서해야함)
		MemberDAO dao = new MemberDAO();
		
		if(!nickName.equals(sNickName)) { //닉네임이 같지 않을때 중복체크처리
			String tempNickName = dao.memNickCheck(nickName);
			if(!tempNickName.equals("")) { //공백이 아니면
				request.setAttribute("msg", "nickCheckNo");
				request.setAttribute("url", request.getContextPath()+"/memJoin.mem");
				return; //return타입이없음. 리턴만하면 끝남
			}
		}
		
	//모든체크가 완료되었다면 회원정보를 vo에 담아서 DB(DAO)로 넘겨준다
		MemberVO vo = new MemberVO(); //생성

		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setJob(job);
		vo.setHobby(hobby);
		vo.setPhoto(fileSystemName); 
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		
		int res = dao.setMemberUpdateOk(vo); //제대로 값넘어왔는지 확인하기위해 res변수에담음
		
		//정보가 변경되었으면 새로운 닉네임 세션에 저장처리한다
		
		if(res ==1) { //정상으로 돌아오면 1
			session.setAttribute("sNickName", nickName);
			request.setAttribute("msg", "memUpdateOk");
			request.setAttribute("url", request.getContextPath()+"/memMain.mem");
		}
		else {
			request.setAttribute("msg", "memUpdateNo");
			request.setAttribute("url", request.getContextPath()+"/memUpdate.mem");
		}
		
	}

}
