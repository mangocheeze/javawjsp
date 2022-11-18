package filter;

import java.io.IOException;

import javax.servlet.Filter; //서블릿걸로 가져오기
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") //우리가 만든 모든파일이 이파일을 거치게함 .모든파일이 한글처리가되게함web.xml에서 filter매핑처리 주석해도 한글안깨지게함(들어오는 모든경로에 대해서 필터해달라)
public class EncodingFilter implements Filter{

	@Override  //EncodingFilter 에 빨간줄뜬거에서 첫번째꺼 누르면 자동으로 나옴
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //웹서비스하는애가아닌 필터처리하는애라 추상클래스가아닌 인터페이스를 연결해줘야함
			throws IOException, ServletException {
		
		//필터에서 한글처리(이걸쓰면 이제 서블릿에서 한글처리안해도 한글안깨짐)
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		// System.out.println("이곳은 Filter통과 전~~"); 
		
		chain.doFilter(request, response);
		
		//System.out.println("이곳은 Filter통과 후~~~");
		
	} 

}
