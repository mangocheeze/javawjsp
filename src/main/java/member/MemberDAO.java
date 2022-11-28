package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.GetConn;

public class MemberDAO {
	
	//싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	MemberVO vo = null;

	//아이디체크(조건을 만족하면 모든자료를 vo에 담아서 넘겨준다 =>다른곳에서도 많이쓰일예정)
	public MemberVO getLoginCheck(String mid) {
		vo = new MemberVO(); //생성
		try {
			sql="select * from member where mid = ?";  //mid가 ?일때 member테이블의 내용을 읽어옴
			pstmt = conn.prepareStatement(sql); //DB연결객체인 conn객체에 sql문을캡슐화한후 변수 pstmt에 담음?
			pstmt.setString(1, mid); //pstmt에 물음표에 가져온mid(사용자가 입력한 아이디)를 저장함
			rs = pstmt.executeQuery(); //실행함과 동시에 변수 rs에담음
			
			//sql문을 실행해서 나온 결과인 rs를 반복해서 rs에있는 정보를 읽어와서 vo에담음
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(mid);
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
			}
			else {
				vo = null; 
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.rsClose(); //싱글톤에 있는 객체를 통해서 rs를 닫음
		}
		return vo;
	}

	//오늘 처음방문시 방문카운트 0로 초기화
	public void setTodayCntUpdate(String mid) {
		try {
			sql = "update member set todayCnt = 0 where mid = ?"; //mid가 ?일때 오늘 방문횟수를 0으로 업데이트함
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //rs생성안했으니까
		}
	}

	// 오늘 재방문이라면 '총방문수','오늘방문수','포인트' 누적처리
	public void setMemTotalUpdate(String mid, int nowTodayPoint) {
		try {
			sql = "update member set visitCnt=visitCnt+1, todayCnt=todayCnt+1, point=?, lastDate=now() where mid = ?"; //mid가 ?일경우(같을경우) 방문횟수 +1, 오늘방문한횟수+1, 포인트 MemLoginOkCommand.java에서 받은포인트 , 마지막접속일을 오늘로 업데이트
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nowTodayPoint); //MemLoginOkCommand에서 오늘방문횟수 5보다작으면 +10, 5랑같거나크면 포인트원래그대로로 줌
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //rs생성안했으니까
		}
		
	}
	
}
