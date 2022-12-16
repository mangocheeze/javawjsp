package pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsTotalDownCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 :Integer.parseInt(request.getParameter("idx"));
		
		PdsDAO dao = new PdsDAO();
		
		PdsVO vo = dao.getIdxSearch(idx);
		
		String[] fNames = vo.getfName().split("/"); // 업로드시 파일명 슬래시로 분리
		String[] fSNames = vo.getfSName().split("/"); // 서버에저장되는 파일명 슬래시로 분리
		
		//파일 압축에 필요한 객체들을 선언한다.
		FileInputStream fis = null;  //파일 가져와야하니까 인풋 선언
		FileOutputStream fos = null; //아웃풋
		ZipOutputStream zos = null;  //zip으로 묶어야함 - zip아웃풋 
		ServletOutputStream sos = null; //서블릿아웃풋 - 클라이언트에 전송하기 위해 필요함
		
		//서버에서 가져온 a1.jpg를 a.jpg로 가져와야하는데 그럼 덮어써버리니까 pds폴더 아래 temp폴더를 만들었음
		String realPath = request.getServletContext().getRealPath("/data/pds/");
		String zipPath = request.getServletContext().getRealPath("/data/pds/temp/"); //zip파일로 묶음. 덮어씌울까봐 temp폴더에 복사해놓음
		String zipName = vo.getTitle() + ".zip"; //zip파일로 만든다는거임
		
		fos = new FileOutputStream(zipPath + zipName);
		zos = new ZipOutputStream(fos); //(한번만 생성할거라 여기서 생성함-서버 fSName을 잠깐 복사해놓을 껍데기를 만드는거임)
		
		byte[] b = new byte[2048]; //2048바이트는 내맘대로줘도됨
		int data = 0;
		
		for(int i=0; i<fNames.length; i++) {
			File file = new File(realPath	+ fSNames[i]); //()안 => 읽어올화일이니까 realPath /원본파일껍데기
//			File copyAndRename = new File(realPath + fSNames[i]); //() dks=> 올릴때파일명 fSNames의 i번째 /copyAndRename :변수명 /복사하기 위한 껍데기 객체가 필요 ( 서버에 fSName을 껍데기에 복사후 그린.zip으로 이름을바꿔서 가져가야함)
			
//			file.renameTo(copyAndRename); //copyAndRebane 을 file로 이름을 바꾸는거임 => fSName을 fName으로 바꾸는거임(뒤에있는거를 앞에있는걸로바꿈)
			
//			fis = new FileInputStream(copyAndRename); //아웃풋이던 그린.zip이 다시 인풋으로됨
			fis = new FileInputStream(file); //아웃풋이던 그린.zip이 다시 인풋으로됨
			zos.putNextEntry(new ZipEntry(fNames[i])); //받을화일/zos에다가 객체로 넣어라 (그래서 ZipEntry 객체생성함)
			//---여기위까지 그린.zip이라는 압축파일에 들어가기위해 준비를 한과정임(껍데기만만듦) , 이제 fSName의 파일을 이 껍데기에 채워넣어야함
			
			while((data = fis.read(b,0,b.length)) != -1) { //2048을 0에서부터 그 길이까지 읽어옴 -1이 아닐때까지 계속반복(-1이되면 없는거니까)
				zos.write(b, 0 , data);
			}
			zos.flush(); //찌꺼기를 다 담음
			zos.closeEntry(); //zos 반납 (close로 닫으면 껍데기뚜껑을 닫는거라 closeEntry로 닫기)
			fis.close();
		}
		zos.close();
		//--여기까지 서버에서 압축작업까지가 끝난거임
		
		/*서버에서 압축작업이 완료되면, 압축파일을 클라이언트에 전송하고, 서버에 압축되어있는 파일을 삭제한다*/
		
		//전송프로토콜인 http헤더에 필요한 정보를 알려준다.
		String mimeType = request.getServletContext().getMimeType(zipName.toString());
		if(mimeType == null) { //아무형식이 없다
			response.setContentType("appliction/octet-stream"); //보낼땐 response /2진 바이너리형식
		}
		String downLoadName = "";
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {//마이크로소프트가인터넷익스플로러 아님(-1:아니라는뜻)
			downLoadName = new String(zipName.getBytes("UTF-8"), "8859_1");
		}
		else {
			downLoadName = new String(zipName.getBytes("EUC-KR"), "8859_1");
		}
		//헤더 정보를 가지고 서버의 zip파일을 클라이언트에 전송처리한다
		response.setHeader("Content-Disposition", "attachment;filename=0_"+downLoadName );
		
		//java로 실제파일을 클라이언트로 다운처리 시켜준다.
		fis = new FileInputStream(zipPath + zipName); //인풋스트림 생성
		sos = response.getOutputStream(); //서블릿아웃풋스트림 생성
		
		while((data = fis.read(b, 0, b.length)) != -1) { //열심히 뱅글뱅글 돔
			sos.write(b, 0 , data);
		}
		sos.flush();
		
		sos.close();
		fis.close();
		// --여기까지가 클라이언트로 전송완료됨..
		
		//서버에 존재하는 zip파일을 삭제처리한다
		new File(zipPath + zipName).delete();
		
		//다운로드 횟수를 증가처리한다.
		dao.setPdsDelete(idx);
	}

}
