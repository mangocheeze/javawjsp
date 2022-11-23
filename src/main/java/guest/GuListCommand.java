package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//서비스객체(command객체) 자료를 전체 가져와야하는데 자기가 못가져오니까 DB한테 가져오라고하는거임
public class GuListCommand implements GuestInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO();  //생성을해야지만 쓸수있음
		
		ArrayList<GuestVO> vos = dao.getGuestList(); //여러개가져올거라 어레이리스트
		
		request.setAttribute("vos", vos); //담은내용은 컨트롤러로 보냄
	}

}
