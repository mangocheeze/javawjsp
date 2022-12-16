package chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/chatList")
public class ChatListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//나중에 독립적으로 사용해야할일이 있을까봐 인코딩 처리함
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String listType = request.getParameter("listType");
		
		if(listType == null || listType.equals("")) {
			response.getWriter().write("");
		} 
		else if(listType.equals("ten")) {
			response.getWriter().write(getTen());
		}
		else { //ten이 아닐때 처리
			response.getWriter().write(getListIdx(listType));
		}
		
		
	}

	//2번째 이후부터 메세지 내용을 출력처리...
	private String getListIdx(String idx) {
		ChatDAO dao = new ChatDAO();
		ArrayList<ChatVO> vos = dao.getChatList(Integer.parseInt(idx)); //마지막 자료를 기준으로 처리(가져오기) , String인 idx를 int로 형변환
		ChatVO vo = new ChatVO();
		
		StringBuffer	res = new StringBuffer();
		res.append("{\"res\":[");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			res.append("[{\"value\":\""+vo.getIdx()+"\"},"); //안에는 큰따옴표를 줘야하는데 밖에 큰따옴표가있어서 문제가되니까 \를 하면됨
			res.append("{\"value\":\""+vo.getNickName()+"\"},");
			res.append("{\"value\":\""+vo.getContent()+"\"},");
			res.append("{\"value\":\""+vo.getcDate()+"\"},"); //여기쉼표는 하나의 레코드에서 각각의 데이터를 쉼표로 구분
			res.append("{\"value\":\""+vo.getAvatar()+"\"}]"); //원래 여기에 ],이렇게 넣어줘서 여기 쉼표는 다른레코드와 구분하기위한 쉼표로 넣어야하나 아래 if에서 처리함
			//여기까지가([ ]) 한건의 레코드가 되는거임
			if(i != (vos.size()-1)) res.append(",");  //vos.size()-1: 마지막찾음/마지막 데이터가 아닐경우에만 쉼표를 넣어라
		}
		res.append("],\"last\":\""+vos.get(vos.size()-1).getIdx()+"\"}"); //변수라서 또 역슬래시\붙여줘야함
		return res.toString(); //json형식이라 문자로 바꿔야됨
	
	}

	
	//최초 접속시 최근메세지 10개의 자료만 보여주기위한 처리
	private String getTen() {
		ChatDAO dao = new ChatDAO();
		ArrayList<ChatVO> vos = dao.getChatList(10); //최초 10개의 메세지 가져오기
		ChatVO vo = new ChatVO();
		
		StringBuffer	res = new StringBuffer();
		res.append("{\"res\":[");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			res.append("[{\"value\":\""+vo.getIdx()+"\"},"); //안에는 큰따옴표를 줘야하는데 밖에 큰따옴표가있어서 문제가되니까 \를 하면됨
			res.append("{\"value\":\""+vo.getNickName()+"\"},");
			res.append("{\"value\":\""+vo.getContent()+"\"},");
			res.append("{\"value\":\""+vo.getcDate()+"\"},"); //여기쉼표는 하나의 레코드에서 각각의 데이터를 쉼표로 구분
			res.append("{\"value\":\""+vo.getAvatar()+"\"}]"); //원래 여기에 ],이렇게 넣어줘서 여기 쉼표는 다른레코드와 구분하기위한 쉼표로 넣어야하나 아래 if에서 처리함
			//여기까지가([ ]) 한건의 레코드가 되는거임
			if(i != (vos.size()-1)) res.append(",");  //vos.size()-1: 마지막찾음/마지막 데이터가 아닐경우에만 쉼표를 넣어라
		}
		res.append("],\"last\":\""+vos.get(vos.size()-1).getIdx()+"\"}"); //변수라서 또 역슬래시\붙여줘야함
		return res.toString(); //json형식이라 문자로 바꿔야됨
	}
}
