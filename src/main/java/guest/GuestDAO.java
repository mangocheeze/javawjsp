package guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {
	private Connection conn = null; //import 올릴때 우린 무조건 sql로 올림(mysql로 안함)
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = ""; //null값 나오는게 지겨워서 처음부터 공백으로함
	
	GuestVO vo = null;  //vo써야하니까 껍데기 먼저만들어놈
	
	private String url ="jdbc:mysql://localhost:3306/javaworks";
	private String user = "root";
	private String password = "1234";
	//혹시 비밀번호 유출되는거아니냐 ? 이게 노출될정도면 이미 다 해킹당한거임
	
	//객체 생성시에 DB연결
	public GuestDAO() {  //기본생성자
		try {
			Class.forName("com.mysql.jdbc.Driver"); //제작사에서 나오는 주소
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~"); // 이 에러가뜨면 lib 폴더에 있는 mysql 커넥터 들어있는지확인
		} catch (SQLException e) {
			System.out.println("Database 연동 실패~~"); //이 에러 뜨면 위에 url , user, password 확인해야함
		}
	}
	
	//객체 소멸처리
	public void pstmtClose() {
		if(pstmt != null) {
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

	//게시글 전체 리스트 읽어오기 (command객체에서 호출)
	public ArrayList<GuestVO> getGuestList() {
		ArrayList<GuestVO> vos = new ArrayList<>();
		try {
			sql = "select * from guest order by idx desc"; //내림차순(나중에쓴게 위에올라옴)
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); //rs에 내용이 들어옴
			
			while(rs.next()) {
					vo = new GuestVO();
					vo.setIdx(rs.getInt("idx")); //rs에있는 idx를 읽어와서 vo에 저장
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setHomePage(rs.getString("homePage"));
					vo.setVisitDate(rs.getString("visitDate"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setContent(rs.getString("content"));
					
					vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	//방명록 글올리기처리 GuInputOkCommand.java 에서 호출
	public int setGuInput(GuestVO vo) {
		int res = 0;
		try {
			sql="insert into guest values (default,?,?,?,default,?,?)";  //default가 절반넘게있으면 필드를 적고 그값만 하는게나음, 여긴 두개밖에없어서 다적음
			pstmt = conn.prepareStatement(sql);  //conn객체를 통해서 sql을 연결?
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getHostIp());
			pstmt.setString(5, vo.getContent());
			pstmt.executeUpdate(); //실행
			res =1; //정상처리되면 res = 1을 보냄
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res; 
	}

	//방명록의 글 삭제처리하기
	public int setGuDelete(int idx) {
		int res = 0;
		try { //글을 보고 들어와서 지울거니까 검색처리까지는 안해도됨(rs객체에담아서 찾을필요가없음)
			sql ="delete from guest where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res =1; 
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	
}
