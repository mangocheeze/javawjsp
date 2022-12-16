package study2.pdstest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class UpLoad1OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String fName = request.getParameter("fName")==null? "":
		 * request.getParameter("fName"); System.out.println("pdstest에서 넘어온 파일명 ? " +
		 * fName);
		 */
		
		// COS라이브러리에서 제공해주는 객체... 1.MultipartRequest() / 2.DefaultFileRenamePolicy()  =>중복방지를 위한클래스, 중복인 파일이생기면 알아서 숫자를 넣어줌(1,2,3..)
		
		//MultipartRequest(저장소명(request),"서버에 저장될 파일의 경로","서버에 저장될 파일의 최대용량","코드변환방식" ,기타옵션(중복방지를위한 클래스))  -가장만만한 저장소 request사용할거임 , 중복방지를위한클래스:DefaultFileRenamePolicy() 
		
		/*정보(파일) 올리기*/
		
		//서버에 저장될 파일경로 (이게 정석)
		//ServletContext application = request.getServletContext();
		//String realPath = application.getRealPath("/");
		
		//-위에를 한줄로쓰면 
		//String realPath = request.getRealPath("/"); 이렇게써도되는데 이상한메세지가뜸
		String realPath = request.getServletContext().getRealPath("/data/pdstest"); //내가 사용할 절대경로의 위치서부터 절대경로 (이렇게쓰기)
		
		//서버에 저장될 파일의 최대용량
		int maxSize = 1024 * 1024 * 10; //서버에 저장할 최대용량 10MByte로 한다(1회저장)
		
		//코드변환방식(인코딩방식)
		String encoding = "UTF-8";
		//System.out.println("realPath : " +realPath); 찍어보기
		
		/*파일 업로드 처리 ..끝*/
		//MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding );//지금은 같은이름의 파일을 올리면 덮어씌움
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy() );//똑같은파일올리면 이름이 자동으로바뀌어서 올라감
		
		/*업로드 된 파일의 정보를 추출해보자*/
		//String fName = request.getParameter("fName"); //(x)reqeust로 가져오면 못받음	
		//System.out.println("원본 파일명 : " + fName); //(x)업로드 시킬때의 업로드 파일명이다

		String OriginalFileName = multipartRequest.getOriginalFileName("fName"); //multipartRequest로해야 값을 받음 , 업로드 시킬때의 업로드 파일명이다
		String filesystemName = multipartRequest.getFilesystemName("fName"); //서버에 저장되는 실제파일명 
		
		System.out.println("원본 파일명 : " + OriginalFileName); //업로드 시킬때의 파일명
		System.out.println("파일이 저장될 서버의 실제 경로명: " +realPath);
		System.out.println("서버에 저장되는 실제파일명 : " + filesystemName );
		
		if(filesystemName == null) {
			request.setAttribute("msg", "upLoad1No");
		}
		else {
			request.setAttribute("msg", "upLoad1Ok");
		}
		request.setAttribute("url", request.getContextPath()+"/pdstest/upLoad1.st");
	}

}
