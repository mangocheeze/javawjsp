package study2.pdstest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class UpLoad2OkCommand implements StudyInterface {
	//반복문버전
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String realPath = request.getServletContext().getRealPath("/data/pdstest"); // 내가 사용할 절대경로의 위치서부터 절대경로 (이렇게쓰기)

		//서버에 저장될 파일의 최대용량
		int maxSize = 1024 * 1024 * 10; //서버에 저장할 최대용량 10MByte로한다(1회저장)
		  
		//코드변환방식(인코딩방식) 
		String encoding = "UTF-8";
		  
		 
	  //파일 업로드 처리 ..끝 
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy() );//똑같은파일올리면 이름이 자동으로바뀌어서 올라감
		
		/* 연습
		//Enumeration e = multipartRequest.getParameterNames(); //(사용x) name이 출력이안됨
		Enumeration e = multipartRequest.getFileNames(); //(사용o)
		while(e.hasMoreElements()) {
			String name= (String) e.nextElement();
			System.out.println("name : " +name);
		}*/
		@SuppressWarnings("rawtypes")
		Enumeration fileNames = multipartRequest.getFileNames(); // Enumeration: 열거형
		String file ="";
		String originalFileName = "";
		String filesystemName = "";
		
		while(fileNames.hasMoreElements()) { //hasMoreElements : 읽어올요소가 남아있는지확인. 있으면 true,없으면 false
			file = (String) fileNames.nextElement();   //nextElement : 다음요소를 읽어옴 /업로드시 폼태그안의 필드네임(name)
			originalFileName = multipartRequest.getOriginalFileName(file); //업로드시 원본파일명
			filesystemName = multipartRequest.getFilesystemName(file); //서버에 저장된 실제 파일명
			System.out.println("업로드시의 원본 파일명 : " + originalFileName);
			System.out.println("서버에 저장된 실제  파일명 : " + filesystemName);
		}
		
		
		if(filesystemName.equals("")) {
			request.setAttribute("msg", "upLoad1No"); 
		} 
		else {
			request.setAttribute("msg", "upLoad1Ok");
		}			 
		
		int upLoadFlag = multipartRequest.getParameter("upLoadFlag")==null ? 2 : Integer.parseInt(multipartRequest.getParameter("upLoadFlag"));
		if (upLoadFlag == 2) {
			request.setAttribute("url", request.getContextPath() + "/pdstest/upLoad2.st");
		}
		else if(upLoadFlag == 3) {
			request.setAttribute("url", request.getContextPath() + "/pdstest/upLoad3.st");
		}
		else if(upLoadFlag == 4) {
			request.setAttribute("url", request.getContextPath() + "/pdstest/upLoad4.st");
		}
	}

}
