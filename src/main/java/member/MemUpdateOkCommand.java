package member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemUpdateOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/data/member");
		int maxSize = 1024 * 1024 * 10;	// 서버에 저장할 최대용량을 10MByte로 한다.(1회 저장)
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
	  // 회원 사진이 업로드 되었는지의 여부 처리? 업로드된 파일이 있으면 서버에 저장된 이름을, 없으면 기존파일명을 filesystemName변수에 저장시켜준다.
		String filesystemName = multipartRequest.getFilesystemName("fName");			// 실제 서버에 저장된 파일명.
		if(filesystemName == null) filesystemName = multipartRequest.getParameter("photo");
		else {
			if(!multipartRequest.getParameter("photo").equals("noimage.jpg")) {
				File file = new File(realPath + "/" + multipartRequest.getParameter("photo"));
				if(file.exists()) file.delete();
			}
		}
		
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		String sNickName = (String) session.getAttribute("sNickName");
		
		String nickName = multipartRequest.getParameter("nickName")==null ? "" : multipartRequest.getParameter("nickName");
		String name = multipartRequest.getParameter("name")==null ? "" : multipartRequest.getParameter("name");
		String gender = multipartRequest.getParameter("gender")==null ? "" : multipartRequest.getParameter("gender");
		String birthday = multipartRequest.getParameter("birthday")==null ? "" : multipartRequest.getParameter("birthday");
		String tel = multipartRequest.getParameter("tel")==null ? "" : multipartRequest.getParameter("tel");
		String address = multipartRequest.getParameter("address")==null ? "" : multipartRequest.getParameter("address");
		String email = multipartRequest.getParameter("email")==null ? "" : multipartRequest.getParameter("email");
		String homePage = multipartRequest.getParameter("homePage")==null ? "" : multipartRequest.getParameter("homePage");
		String job = multipartRequest.getParameter("job")==null ? "" : multipartRequest.getParameter("job");
		String content = multipartRequest.getParameter("content")==null ? "" : multipartRequest.getParameter("content");
		String userInfor = multipartRequest.getParameter("userInfor")==null ? "" : multipartRequest.getParameter("userInfor");
		
		// 취미 전송에 대한 처리
		String[] hobbys = multipartRequest.getParameterValues("hobby");
		String hobby = "";
		if(hobbys.length != 0) { //0이아닐때 = 하나라도 선택했을때
			for(String strHobby : hobbys) {
				hobby += strHobby + "/"; //hobby를 누적 /로 구분
			}
		}
		hobby = hobby.substring(0,hobby.lastIndexOf("/"));
		
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
		vo.setPhoto(filesystemName); 
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
