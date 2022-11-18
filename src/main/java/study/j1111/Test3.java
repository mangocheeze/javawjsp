package study.j1111;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atom") //이름 아무거나 해줘도됨
//@WebServlet("/T2")
public class Test3 extends HttpServlet{ //?을해서 무조건 HttpServlet을 상속받아야함
	//doget하고 스페이스바 엔터치면 나옴
	//폼에서 post로 넘기면 얘가 필요없음
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 Get입니다");
	}
	
	//dopost하고 스페이스바 엔터치면 나옴
	@Override //폼에서 get으로 넘기면 이게 필요없음 //폼에서 메소드가 get이면 get,post면 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 Post입니다");
	}
}
