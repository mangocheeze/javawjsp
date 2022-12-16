package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDeleteCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("file"); //enctype을 안써서 그냥 get방식

		String realPath = request.getServletContext().getRealPath("/data/pdstest/"); //서버에 저장된 폴더 들어가는 절대경로
		
		File file = new File(realPath + fName);  // file io 꺼로 올려야함
		
		String res = "0";
		if(file.exists()) { //.exists() :존재하니? /파일이 있을때 지워야지 없을때 바로지우면 에러남
			file.delete();
			res = "1";
		}
		
		response.getWriter().write(res); 
	}

}
