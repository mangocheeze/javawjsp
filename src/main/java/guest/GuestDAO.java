package guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {
	//import 올릴때 우린 무조건 sql로 올림(mysql로 안함)
	private Connection conn = null; //자바와 DB를 연결하는객체
	private PreparedStatement pstmt = null; //SQL문을 실행하고 결과를 반환
	private ResultSet rs = null;  //결과가있는 SQL문 실행후 결과를 받을때만 사용
	
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

	//방명록 전체 리스트 읽어오기 (GuListCommand객체에서 호출 -dao에 있는걸 읽어서 vo에 담아오려는거임)
	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize) {
		ArrayList<GuestVO> vos = new ArrayList<>(); //vos생성
		try {
			sql = "select * from guest order by idx desc limit ?,?"; //guest테이블의 idx를 내림차순으로 보여달라(나중에쓴게 위에올라옴) / 한계
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo); //첫번째물음표 시작인덱스번호
			pstmt.setInt(2, pageSize); //두번째물음표 한페이지의 건수
			
			rs = pstmt.executeQuery(); //수행결과로 ResultSet 객체의값을 반환? select 구문수행할때사용 (select구문의 sql문 결과를 rs에담음)
			
			while(rs.next()) {
					//여기선 idx만 넘기지만 vo에 다 저장해놓음
					vo = new GuestVO(); //vo생성
					vo.setIdx(rs.getInt("idx")); //rs에있는 idx를 읽어와서 vo에 저장
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setHomePage(rs.getString("homePage"));
					vo.setVisitDate(rs.getString("visitDate"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setContent(rs.getString("content"));
					
					vos.add(vo); //vos에 vo를저장
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

	//방명록의 총 레코드 건수 구하기
	public int totRecCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as cnt from guest"; //별명을 cnt로 하겠다
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			rs.next(); //무조건 나옴 ,자료가 없어도 0으로 나옴,if로 물어볼필요 없음
			
//			totRecCnt = rs.getInt(1); //데이터베이스에선 0번째가 아니라 1번째임 그래서 (1)넣기 ????, 가능하나 이렇게쓰지말고 변수에 담아서 넣기
			totRecCnt = rs.getInt("cnt"); //변수에담아서 넣음
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return totRecCnt;
	}

	//사용자가 방명록에 글쓴횟수 가져오기
	public int getNumberGuest(String name) {
		int num = 0;
		try {
			sql = "select count(*) as cnt from guest where name = ?"; //guest테이블에서 name이 ?과 일치하는(이름이일치하는) 데이터 갯수 출력, 별명을 cnt로줌
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				num = rs.getInt("cnt");
			}
    } catch (Exception e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      rsClose();
    }
		return num;
	}
	
	
}
