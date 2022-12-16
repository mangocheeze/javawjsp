package pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;

public class PdsDeleteCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String fSName_ = request.getParameter("fSName")==null ? "" : request.getParameter("fSName");  //fSName_ : 임시화일개념(이름 겹쳐서 이렇게씀)
		String[] fSNames = fSName_.split("/");
		
		// 비밀번호를 암호화 한후 DB의 비밀번호와 같으면 작업을 계속 수행처리한다.
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd); //비밀번호를 암호화한후 pwd변수에담음
		
		PdsDAO dao = new PdsDAO();
		
		PdsVO vo = dao.getIdxSearch(idx);
		
		if(vo.getPwd().equals(pwd)) {
			
			/*파일이 있는지 먼저보고 지워야함*/
			//파일이 저장되어있는 서버의 실제 경로를 찾아온다
			String realPath = request.getServletContext().getRealPath("/data/pds/");
			
			//서버 파일 시스템에 실제로 존재하는 파일들을 찾아서 삭제처리한다.
			//향상된for문사용함
			for(String fSName : fSNames) {
				//File file = new File(realPath + fSName);
				//file.delete();
				
				new File (realPath + fSName).delete();//위에걸 이렇게 한줄로 생성하면서 바로 쓸수있음
			}
			
			//파일 삭제 완료후 DB에 저장된 자료파일을 삭제한다.
			String res = dao.setPdsDelete(idx);
			
			response.getWriter().write(res);
		}
	}

}
