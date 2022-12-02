package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class BoardDAO {
	
	//싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance(); //GetConn에 있는 객체를 읽어와 getConn에 담음(사용하려고 생성)
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	BoardVO vo = null;

	//전체(*) 레코드 건수 구하기 - 페이징처리하기위해서 구함
	public int totRecCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as cnt from board"; // count(*) => 전체의 갯수를 보여달라 (여기선 게시판글의갯수)
			pstmt = conn.prepareStatement(sql); //pstmt 생성
			rs = pstmt.executeQuery();
			
			rs.next();  //무조건 나오면 이렇게 써주면됨 나올지안나올지모르면(얼마나나올지모르면) if,while문사용
			totRecCnt = rs.getInt("cnt"); //필드명이라 ""씀 (변수면 안씀)
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	//전체 게시글 가져오기
	public ArrayList<BoardVO> getBoList(int startIndexNo, int pageSize) {
		ArrayList<BoardVO> vos = new ArrayList<>();  //실무에서 List<BoardVO> 라고쓰는경우도있음
		try {
			//sql = "select *, datediff(now() ,wDate) as day_diff from board order by idx desc limit ?,?"; //마지막에쓴 순서대로,
			sql = "select *,datediff(now(), wDate) as day_diff,"
					+ " timestampdiff(hour, wDate, now()) as hour_diff"
					+ " from board order by idx desc limit ?,?"; 
			// 전체를 보여주고 날짜차이계산과 시간차이계산을 각각 별명으로 필드를추가해서 보여줌 결과값을 startIndexNo 부터 페이지사이즈만큼 보여줌
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery(); //읽어내서 rs변수에담음
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setwDate(rs.getString("wDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setGood(rs.getInt("good"));
				vo.setMid(rs.getString("mid"));
				
				vo.setDay_diff(rs.getInt("day_diff"));
				vo.setHour_diff(rs.getInt("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		
		return vos;
	}

	//게시글 내용 DB에 저장하기
	public int setBoinputOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "insert into board values (default,?,?,?,?,?,default,?,default,default,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNickName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getHomePage());
			pstmt.setString(5, vo.getContent());
			pstmt.setString(6, vo.getHostIp());
			pstmt.setString(7, vo.getMid());
			pstmt.executeUpdate(); //실행 이걸써야 DB에저장
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//개별자료 검색 (게시판 글제목 누르면)
	public BoardVO getBoContentSearch(int idx) {
		try {
			sql="select * from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			rs.next(); //자료가 분명히 있으니까
			
			vo = new BoardVO();
			vo = new BoardVO();
			vo.setIdx(rs.getInt("idx"));
			vo.setNickName(rs.getString("nickName"));
			vo.setTitle(rs.getString("title"));
			vo.setEmail(rs.getString("email"));
			vo.setHomePage(rs.getString("homePage"));
			vo.setContent(rs.getString("content"));
			vo.setwDate(rs.getString("wDate"));
			vo.setHostIp(rs.getString("hostIp"));
			vo.setReadNum(rs.getInt("readNum"));
			vo.setGood(rs.getInt("good"));
			vo.setMid(rs.getString("mid"));
			
		
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	//조회수 증가처리
	public void setReadNumPlus(int idx) {
		try {
			sql="update board set readNum = readNum + 1 where idx =?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}

	//좋아요 횟수 증가 처리
	public void setBoGood(int idx) {
		try {
			sql = "update board set good = good + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		
	}
	// 따봉을 이용한 좋아요 횟수 증가, 감소
	public void setGoodPlusMinus(int idx, int goodCnt) {
		try {
			sql = "update board set good = (good + ?) where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodCnt); // +1이나 -1이 들어옴
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}

	//게시글 삭제처리
	public int setBoDeleteOk(int idx) {
		int res = 0;
		try {
			sql = "delete from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//게시글 수정처리
	public int setBoUpdateOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "update board set title=? ,email=? ,homePage=? ,content=? ,hostIp=? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getHostIp());
			pstmt.setInt(6, vo.getIdx());
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//이전글/다음글에 필요한 내용 검색처리
	public BoardVO getPreNextSearch(String str, int idx) {
		vo = new BoardVO();
		try {
			if(str.equals("pre")) { //이전글 : str에 넘어온게 pre라면 
				sql="select * from board where idx < ? order by idx desc limit 1"; //idx가 넘어오는 idx보다 작을때 
			}
			else { //다음글
				sql="select * from board where idx > ? limit 1"; 
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs= pstmt.executeQuery();
			
			if(str.equals("pre") && rs.next()) { //pre 이고 rs.next 가 참이면 이전데이터가있다
				vo.setPreIdx(rs.getInt("idx"));
				vo.setPreTitle(rs.getString("title"));
			}
			else if(str.equals("next") && rs.next()) { //next이고 rs.next가 참이면
				vo.setNextIdx(rs.getInt("idx"));
				vo.setNextTitle(rs.getString("title"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	//게시판 검색처리
	public ArrayList<BoardVO> getBoContentSearch(String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<>();
		try {
			sql = "select * from board where "+search+" like ? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchString+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setwDate(rs.getString("wDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setGood(rs.getInt("good"));
				vo.setMid(rs.getString("mid"));
				
//				vo.setDay_diff(rs.getInt("day_diff"));
//				vo.setHour_diff(rs.getInt("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	//댓글입력처리
	public String setReplyInputOk(BoardReplyVO replyVo) {
		String res = "0";
		try {
			sql = "insert into boardReply values (default,?,?,?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyVo.getBoardIdx());
			pstmt.setString(2, replyVo.getMid());
			pstmt.setString(3, replyVo.getNickName());
			pstmt.setString(4, replyVo.getHostIp());
			pstmt.setString(5, replyVo.getContent());
			pstmt.executeUpdate();
			res = "1";
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//댓글 가져오기
	public ArrayList<BoardReplyVO> getBoReply(int idx) {
		 ArrayList<BoardReplyVO> replyVos = new ArrayList<>();
		 try {
			 sql = "select * from boardReply where boardIdx = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, idx);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) { //댓글이 없을수도있음
				 BoardReplyVO replyVo = new BoardReplyVO();
				 replyVo.setIdx(rs.getInt("idx")); //댓글의 idx
				 replyVo.setBoardIdx(idx); //idx 넘어온 부모글
				 replyVo.setMid(rs.getString("mid"));
				 replyVo.setNickName(rs.getString("nickName"));
				 replyVo.setwDate(rs.getString("wDate"));
				 replyVo.setHostIp(rs.getString("hostIp"));
				 replyVo.setContent(rs.getString("content"));
				 
				 replyVos.add(replyVo);
			 }
			} catch (SQLException e) {
				System.out.println("SQL 에러 : " + e.getMessage());
			} finally {
				getConn.rsClose();
			}
		return replyVos;
	}

	//댓글삭제하기
	public String setBoReplyDeleteOk(int idx) {
		String res ="0";
		try {
			sql = "delete from boardReply where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}


}
