package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;


public class DownLoadCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/data/pdstest"); //realPath 변수를 통해 pdstest파일까지 경로온거임 (절대경로를 가져옴)
		
		//File file = new File("atom.txt"); //한개화일만 생성(자바에서 화일객체생성) 한개있는 화일은(껍데기만) 이렇게처리함
		String[] files = new File(realPath).list();// 화일의 정보를가져옴. 절대경로에 들어있는 파일의 목록을 가져와서 문자배열로 담아라 ///화일이름을 문자로가져올건데 배열로 가져올거임. 여러개파일 생성할때사용, 경로를 리스트로 만들어서 반환, 화일의 목록만 만들거야
		
		for(String file : files) {
			System.out.println("file : " + file);
		}
		
		request.setAttribute("files", files); //정보를 저장, file리스트를 가져와서 변수에 담아서 가져감
		
	}

}
