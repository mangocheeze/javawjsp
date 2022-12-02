package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class MemberDAO {
	
	//싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	MemberVO vo = null;

	//아이디체크(조건을 만족하면(아이디가같을때) 모든자료를 vo에 담아서 넘겨준다 =>다른곳에서도 많이쓰일예정)
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //rs생성안했으니까
		}
	}

	// 오늘 재방문이라면 '총방문수','오늘방문수','포인트' 누적처리
	public void setMemTotalUpdate(String mid, int nowTodayPoint) {
		try {
			sql = "update member set visitCnt=visitCnt+1, todayCnt=todayCnt+1, point=?, lastDate=now() where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nowTodayPoint); //MemLoginOkCommand에서 오늘방문횟수 5보다작으면 +10, 5랑같거나크면 포인트원래그대로로 줌
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //rs생성안했으니까
		}
	}

	//닉네임 중복처리( 숙제로한거)
	/*
	 * public int getNickNameCheck(String nickName) { int res =1; try { sql
	 * ="select * from member where nickName = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, nickName); rs =
	 * pstmt.executeQuery();
	 * 
	 * if(rs.next()) { res = 0; //닉네임이 같으면 res=0을보냄 } } catch (SQLException e) {
	 * System.out.println("SQL 오류 : " + e.getMessage()); } finally {
	 * getConn.rsClose(); } return res; }
	 */

	// 닉네임 중복체크
	public String memNickCheck(String nickName) {
		String name = "";
		try {
			sql = "select name from member where nickName = ?"; //닉네임이 같으면 이름을 읽어옴
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if(rs.next()) name = rs.getString("name");
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return name;
	}

	//신규회원 가입처리
	public int setMemberJoinOk(MemberVO vo) {
		int res = 0;
		try {
			sql = "insert into member values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default,default,default,default,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getBirthday());
			pstmt.setString(7, vo.getTel());
			pstmt.setString(8, vo.getAddress());
			pstmt.setString(9, vo.getEmail());
			pstmt.setString(10, vo.getHomePage());
			pstmt.setString(11, vo.getJob());
			pstmt.setString(12, vo.getHobby());
			pstmt.setString(13, vo.getPhoto());
			pstmt.setString(14, vo.getContent());
			pstmt.setString(15, vo.getUserInfor());
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //sql문이 insert라 pstmt 닫음
		}
		return res;
	}

	//전체회원리스트처리(회원자료 전체검색)
	public ArrayList<MemberVO> getMemList(int startIndexNo, int pageSize, String mid, int level) {
		ArrayList<MemberVO> vos = new ArrayList<>();
		try {
			if(level != 0) {
				sql = "select * from member where userInfor = '공개' order by idx desc limit ?,?"; //관리자 뿐만아니라 모두가 볼수있음(공개처리한 멤버의 리스트에 한해서) /나중에 등록한 사람순으로
			}
			else { //관리자일경우
				sql = "select * from member order by idx desc limit ?,?";  //모두볼수있음
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery(); //실행한뒤 rs변수에 담음
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd")); //일단 넣어놓기만하고 출력은 안할거임
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
				
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose(); 
		}
		return vos;
	}

	//비밀번호 변경처리
	public int setMemUpdatePwdOk(String mid, String newPwd) {
		int res = 0;
		try {
			sql = "update member set pwd = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();  //sql문이 update니까 pstmt닫음
		}
		return res;
	}

	//회원정보수정하기
	public int setMemberUpdateOk(MemberVO vo) {
		int res = 0;
		try {
			sql = "Update member set nickName=?, name=?, gender=?, birthday=?,"
					+ "tel=?, address=?, email=?, homePage=?, job=?, hobby=?, "
					+ "photo=?, content=?, userInfor=? where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNickName());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getBirthday());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getHomePage());
			pstmt.setString(9, vo.getJob());
			pstmt.setString(10, vo.getHobby());
			pstmt.setString(11, vo.getPhoto());
			pstmt.setString(12, vo.getContent());
			pstmt.setString(13, vo.getUserInfor());
			pstmt.setString(14, vo.getMid());
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //sql문이 insert라 pstmt 닫음
		}
		return res;
	}


	// 총 레코드 건수 구하기
	public int totRecCnt(String mid, int level) {
		int totRecCnt = 0;
		try {
			if(mid.equals("")) {	// 전체리스트
				if(level != 0) {		// 일반사용자
					sql = "select count(*) as cnt from member where userInfor = '공개'";
				}
				else {		// 관리자
					sql = "select count(*) as cnt from member";
				}
				pstmt = conn.prepareStatement(sql);
			}
			else {		// 조건 리스트
				if(level != 0) {
					sql = "select count(*) as cnt from member where userInfor = '공개' and mid like ?";
				}
				else {
					sql = "select count(*) as cnt from member where mid like ?";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+mid+"%");
			}
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	//회원 자료 검색 (전체회원리스트에서 아이디개별검색 누를때)
	public ArrayList<MemberVO> getMemberSearch(String mid) {
		ArrayList<MemberVO> vos = new ArrayList<>();
		try {
			sql = "select * from member where mid like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+mid+"%"); //like연산자라서 
			rs = pstmt.executeQuery();
			
			//매개변수가 0이면 전체리스트 0이아니면 ?????
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
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
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	//회원탈퇴처리(userDel필드의 값을 'OK'로 변경처리한다- 탈퇴신청) 
	public void setMemberDel(String mid) {
		try {
			sql = "update member set userDel = 'OK' where mid = ?"; //이렇게만하면 로그인은됨(탈퇴신청만받은거라 -> loginOkCommand로감)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}
	


	
}
