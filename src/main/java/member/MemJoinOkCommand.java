package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import conn.SecurityUtil;

public class MemJoinOkCommand implements MemberInterface {
 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/data/member");
		int maxSize = 1024 * 1024 * 10;	// 서버에 저장할 최대용량을 10MByte로 한다.(1회 저장)
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
	  // 회원 사진이 업로드 되었는지의 여부 처리? 업로드된 파일이 있으면 서버에 저장된 이름을, 없으면 'noimage.jpg'파일명을 filesystemName변수에 저장시켜준다.
		// String originalFileName = multipartRequest.getOriginalFileName("fName");  // 업로드 시킬때의 업로드 파일명이다.
		String filesystemName = multipartRequest.getFilesystemName("fName");			// 실제 서버에 저장된 파일명.
		if(filesystemName == null) filesystemName = "noimage.jpg";
		
		String mid = multipartRequest.getParameter("mid")==null ? "" : multipartRequest.getParameter("mid");
		String pwd = multipartRequest.getParameter("pwd")==null ? "" : multipartRequest.getParameter("pwd");
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
		
		//취미 전송에 대한처리 (배열)

		String[] hobbys = multipartRequest.getParameterValues("hobby");
		String hobby = "";
		if(hobbys.length != 0) { //0이아닐때 = 하나라도 선택했을때
			for(String strHobby : hobbys) {
				hobby += strHobby + "/"; //hobby를 누적 /로 구분
			}
		}
		hobby = hobby.substring(0,hobby.lastIndexOf("/"));
		
		//DB에 저장시, 테이블설계에서 지정한 각 필드의 길이체크..(숙제)
		
		//아이디와 닉네임을 다시한번 중복체크 해준다. (vo에 담기전에 한번더 잠깐 체크해주는거임 -여긴 찐백에서 해주는 유효성검사 백에서도 한번더 해줘야함)
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getLoginCheck(mid); //자료가있으면 담기고 없으면안담김 자료가있다는건 중복됐다는거
		if(vo != null) { //중복이 됐으면 
			request.setAttribute("msg", "idCheckNo");
			request.setAttribute("url", request.getContextPath()+"/memJoin.mem"); //다시 회원가입으로가게함
			return; //return타입이없음. 리턴만하면 끝남
		}
		//닉네임중복체크
		String tempNickName = dao.memNickCheck(nickName); //temp는 임시로 준이름
		if(!tempNickName.equals("")) { //가져온 닉네임이 공백이 아니면
			request.setAttribute("msg", "nickCheckNo");
			request.setAttribute("url", request.getContextPath()+"/memJoin.mem");
			return; //return타입이없음. 리턴만하면 끝남
		}
		
		//비밀번호 암호화처리 (SHA256)
		SecurityUtil security = new SecurityUtil(); //SHA256 올려줌(생성)
		pwd = security.encryptSHA256(pwd); //암호화해서 변수pwd에 담음
		
		//모든체크가 완료되었다면 회원정보를 vo에 담아서 DB(DAO)로 넘겨준다
		vo = new MemberVO(); //생성
		
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setNickName(nickName); //tempNickName은 체크하기위한거라 그냥 nickName
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setJob(job);
		vo.setHobby(hobby);
		vo.setPhoto(filesystemName); //photo에 안담고 fileSystemName에 담음
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		//System.out.println("vo :" +vo);
		
		int res = dao.setMemberJoinOk(vo); //제대로 값넘어왔는지 확인하기위해 res변수에담음
		
		if(res ==1) {
			request.setAttribute("msg", "memJoinOk");
			request.setAttribute("url", request.getContextPath()+"/memLogin.mem"); //로그인하러감
		}
		else {
			request.setAttribute("msg", "memJoinNo");
			request.setAttribute("url", request.getContextPath()+"/memJoin.mem"); //다시 회원가입하러감
		}
	}

}
