package study2.mapping2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.JusorokDAO;
import study.database.JusorokVO;

public class JuListCommand implements MappingInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JusorokDAO dao = new JusorokDAO(); //DAO생성. 데이터베이스의 JusoRokDAO 이미만들어놓음
		
		ArrayList<JusorokVO> vos = dao.getMemberList(); //DAO한테 의뢰만한거임 , dao에서 return vos로 넘겨줌
		
		request.setAttribute("vos", vos);//DAO에서 보내줘서 여기선 저장만하면되는거임
	}

}
