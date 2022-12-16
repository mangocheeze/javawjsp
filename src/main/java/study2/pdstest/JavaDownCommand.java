package study2.pdstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class JavaDownCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//input으로 가져와서 output으로 나감,  서블릿을 통해서 클라이언트로 감 (서블릿통해서 가는 경로 =>realPath (ServletContext를통해서 꺼내옴))
		String realPath = request.getServletContext().getRealPath("/data/pdstest/"); //처음슬래시는 웹앱(루트)를 말함 마지막슬래시는 파일이름이 바로들어오니까 써줌
		
		String fName = request.getParameter("file");
		
		//파일객체만듦
		File file = new File(realPath + fName);
		
		/*프로토콜형식에 맞도록 헤더에 정보를 제공해 준다.*/
		// mimeType : 파일형식(예 : 텍스트파일? 바이너리화일? ..등을 맞춰줌) --> 2진 바이너리 형식으로 전송해야한다
		String mimeType = request.getServletContext().getMimeType(file.toString());
		if(mimeType == null) { //아무형식이 없다
			response.setContentType("appliction/octet-stream"); //보낼땐 response /2진 바이너리형식
			
		}
		//사용하는브라우저에 대한 정보 : 만약에 인터넷익스플로러(IE)인 경우는 'euc-Kr', 나머지는 'utf-8'로 전송한다 / 생략가능
		String downLoadName = "";
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {//마이크로소프트가 아님(-1:아니라는뜻)
			downLoadName = new String(fName.getBytes("UTF-8"), "8859_1");
		}
		else {
			downLoadName = new String(fName.getBytes("EUC-KR"), "8859_1");
		}
		
		//헤더 정보를 첨부하여 클라이언트에 전송할준비를 마친다
		response.setHeader("Content-Disposition", "attachment;filename=0_"+downLoadName ); //예약어 Content-Disposition,attachment  /filename:변수명
		
		//---위까진 준비 아래부턴 복사해줌

		//java에 의해서 실제로 파일을 다운(업)로드 처리시켜준다-껍데기만듦 (FileInputStream /FileOutputStream /ServletOutputStream)
		FileInputStream fis = new FileInputStream(file); //인풋스트림 , 서버에 있는파일
		//FileOutputStream fos = new FileOutputStream(nFile);  아웃풋스트림, 서버로 보냄
		ServletOutputStream sos = response.getOutputStream(); //서블릿스트림 ,서블릿을통해서 클라이언트로감
		
		//전송할 객체를 생성후 실제로 파일을 객체에담아서 처리시켜준다.
		byte[] b = new byte[2048]; //2k씩 담겠다
		int data = 0;
		
		while((data = fis.read(b, 0, b.length)) != -1)	{ //-1  : 내용이 없다
			sos.write(b, 0, data);
		}
		sos.flush();
		//여기까지가 파일 다운로드 처리가 완료된다.
		
		sos.close();
		fis.close();
		
		
		
	}

}
