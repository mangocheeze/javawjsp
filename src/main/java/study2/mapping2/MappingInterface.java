package study2.mapping2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스 만들어짐 커멘드객체의 메소드명을 excute로 정의해줌
public interface MappingInterface {
	public void excute(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException; //ServletException, IOException 이 두객체를 넘김 , 컨트롤러의 기본틀? 과 똑같음 :컨트롤러가 받은거를 그대로 주겠다
	//리턴타입은 넘겨주는거 없어야되니까 void로 줘야함 /메소드 이름은 우리끼리 excute로정함(마음대로)
	//예외처리를 무조건해줘야함
	
}
