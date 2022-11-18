package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JusorokDAO { //HttpServlet을 상속받아야 request랑 response를 사용할수있음- 상속안받으면 request사용못하니까 여기선 세션 생성못함
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	JusorokVO vo = null; //vo를 선언만해놓음
	
	public JusorokDAO() {
		String url = "jdbc:mysql://localhost:3306/javaworks"; //바깥에 선언하면 private 안에서 사용해서 default로줌
		String user = "root";
		String password ="1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn =DriverManager.getConnection(url, user, password); //connection연결한걸 conn변수에 담음
		} catch (ClassNotFoundException e) {//클래스가없을경우에 에러메세지
			System.out.println("드라이버 검색 실패~~~");
			
		} catch (SQLException e) {
			System.out.println("Database 연동 실패~~~");
		}
	}
	
	//사용한객체(데이터베이스사용)반납
	public void pstmtClose() {
		if(pstmt != null) { //null이 아니면 사용했다는거
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	public void rsClose() {
		if(rs!= null) {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {}
		}
	}

	//로그인 체크처리 ( LoginOk.java에서 호출)
	public JusorokVO loginCheck(String mid, String pwd) {
		vo = new JusorokVO(); //vo생성
		String name="";
		//데이터 베이스 검색할땐 무조건 예외처리해주기
		try {
			sql = "select * from jusorok where mid =? and pwd =?"; //뭐가올지 모르니까 ?로 써주기 /* 은 전체 name은 이름만찾기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid); //첫번째 물음표
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery(); //실행함과 동시에 결과를 rs에담아옴
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx")); //여기선 쓸필요없지만 나중에 vo건들일있으면 다른곳에서 사용할수있어서 넣음
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name")); //vo에담음 /자료를 찾았으면 name에 값이 들어가서 넘어감 , 못찾으면 초기값 "" 공백이들어감
				vo.setPoint(rs.getInt("point")); 
				vo.setLastDate(rs.getString("lastDate"));
			}
		} catch (SQLException e) { //여긴 무조건 sql오류
			System.out.println("SQL 오류 : " + e.getMessage()); //e쓴이유 :에러를 간단하게만보려고
		} finally {
			rsClose();
		}
		
		return vo;
	}

	//로그아웃처리(LogOut.java에서 호출)
	public void logout() {
		// 종료전에 DB에 처리해야 할 내용들을 기록...
		//현재는 없음 
		
	}

	//회원 정보 조회 (MemberMain.java에서 호출)
	public JusorokVO getMemberSearch(String mid) {
		vo = new JusorokVO(); //vo생성
		String name="";
		
		try {
			sql = "select * from jusorok where mid =?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid); //첫번째 물음표
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx")); 
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point")); 
				vo.setLastDate(rs.getString("lastDate"));
			}
		} catch (SQLException e) { 
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		
		return vo;
	}
	//방문포인트 증가와 최종방문일자 업데이트(LoginOk.java에서 호출)
	public void setVisitUpdate(int visitPoint, String mid) {
		try {
			sql = "update jusorok set point=?, lastDate=now() where mid=?"; //방문일자는 받는게아니니까 오늘날짜로 now()씀
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visitPoint); //point는 정수
			pstmt.setString(2, mid);
			pstmt.executeUpdate(); //실행하면서 끝나면됨 
		} catch (SQLException e) { //에러가 뜨면 사용자 콘솔창에만 아래가 뜨게됨
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
	}

	public int setJoinOk(JusorokVO vo) {
		int res = 0; //1일때가 정상 1이아닐때가 비정상처리
		try { //정상처리면 1
			sql = "insert into jusorok (mid,pwd, name) values (?,?,?)"; //insert into: 예약어
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid()); //pstmt에 vo에있는 mid를 저장함
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.executeUpdate();
			res =1; 
		} catch (SQLException e) {  //비정상처리면(sql 오류가 뜨면)res=0 이되면서 회원가입이 안됨
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	//전체회원조회하기(멤버리스트
	public ArrayList<JusorokVO> getMemberList() {
		ArrayList<JusorokVO> vos = new ArrayList<>();
		try {
			sql = "select * from jusorok order by idx desc"; //나중에 온사람순 (내림차순)
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //'나'라도 무조건 들어오는사람이있지만 반복문에선 해줘야함
				vo = new JusorokVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				
				vos.add(vo);
			}
		} catch (SQLException e) { 
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
}
