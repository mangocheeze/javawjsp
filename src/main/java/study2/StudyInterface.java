package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StudyInterface {
	//선언만해놓으면 얘는 추상메소드가됨
	//StudyInterface를 implements 걸면 사용가능
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
