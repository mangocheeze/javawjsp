package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/chatInput")
public class ChatInputController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = URLDecoder.decode(request.getParameter("nickName"), "UTF-8");
		String content = URLDecoder.decode(request.getParameter("content"), "UTF-8");
		String avatar = request.getParameter("avatar");
		
		if(nickName == null || nickName.equals("") || content == null || content.equals("")) { //null값 처리
			response.getWriter().write("");
		}
		else {
			response.getWriter().write(new ChatDAO().setChatInput(nickName, content , avatar)); //처리할게 없으면 이렇게 한줄로 써도됨
			
		}
	}
}
