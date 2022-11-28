package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import guest.GuestVO;

public class GetConn {
	private Connection conn = null; //자바와 DB를 연결하는객체
	private PreparedStatement pstmt = null; //SQL문을 실행하고 결과를 반환
	private ResultSet rs = null;  //결과가있는 SQL문 실행후 결과를 받을때만 사용
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url ="jdbc:mysql://localhost:3306/javaworks";
	private String user = "root";
	private String password = "1234";
	
	//싱글톤객체 최초1번 생성
	private static GetConn instance = new GetConn();//필드메모리에 올리면서 생성함
	
	//객체 생성시에 DB연결
	private GetConn() {  //생성자생성할때 DB연결 생성자를 외부에서 함부로생성하면안되니까 private선언
		try {
			Class.forName(driver); //제작사에서 나오는 주소
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~"); // 이 에러가뜨면 lib 폴더에 있는 mysql 커넥터 들어있는지확인
		} catch (SQLException e) {
			System.out.println("Database 연동 실패~~"); //이 에러 뜨면 위에 url , user, password 확인해야함
		}
	}
	
	//외부에서 생성자 연결
	public  Connection getConn() { //연결목적으로 쓰는거니까 타입은 Connection타입
		return conn;//객체를 넘김 => conn = DriverManager.getConnection(url, user, password); 를 넘김
	}
	
	//객체 소멸처리
	public void pstmtClose() {
		if(pstmt != null) { //null이 아니며
			try {
				pstmt.close();
			} catch (Exception e) {} //에러가 날일이 없어서 닫음
		}
	}
	

	public void rsClose() {
		if(rs != null) { //null이 아니면 사용을 해서 뭔가 들어와있음-> 닫아주기
			try {
				rs.close();
				pstmtClose();
			} catch (Exception e) {}
		}
	}
	
	//싱글톤 getter 만들어줌
	public static GetConn getInstance() {
		return instance;
	}
}

