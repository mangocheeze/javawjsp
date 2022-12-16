package pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.BoardVO;
import conn.GetConn;

public class PdsDAO {
	
	//싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance(); //GetConn에 있는 객체를 읽어와 getConn에 담음(사용하려고 생성)
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	PdsVO vo = null;

	//파트별 전체(*) 레코드(자료) 건수 구하기 - 페이징처리하기위해서 구함
	public int totRecCnt(String part) {
		int totRecCnt = 0;
		try {
			if(part.equals("전체")) {
				sql = "select count(*) as cnt from pds "; // count(*) => 전체의 갯수를 보여달라 //파트에 전체라는건없음
				pstmt = conn.prepareStatement(sql); //pstmt 생성
			}
			else {
				sql = "select count(*) as cnt from pds where part = ?"; 
				pstmt = conn.prepareStatement(sql); //pstmt 생성
				pstmt.setString(1, part);
			}
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

	// 파트별 전체 자료 가져오기
	public ArrayList<PdsVO> getPdsList(int startIndexNo, int pageSize, String part) {
		ArrayList<PdsVO> vos = new ArrayList<>();  //실무에서 List<BoardVO> 라고쓰는경우도있음
		try {
			if(part.equals("전체")) {
				sql = "SELECT *,datediff(now(), fDate) as day_diff, "
						+ "timestampdiff(hour, fDate, now()) as hour_diff "
						+ "FROM pds order by idx desc "
						+ "limit ?,?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {//전체가 아닐경우
				sql = "SELECT *,datediff(now(), fDate) as day_diff, "
						+ "timestampdiff(hour, fDate, now()) as hour_diff "
						+ "FROM pds where part=? order by idx desc "
						+ "limit ?,?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery(); //읽어내서 rs변수에담음
			
			while(rs.next()) {
				vo = new PdsVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setfName(rs.getString("fName"));
				vo.setfSName(rs.getString("fSName"));
				vo.setfSize(rs.getInt("fSize"));
				vo.setTitle(rs.getString("title"));
				vo.setPart(rs.getString("part"));
				vo.setPwd(rs.getString("pwd"));
				vo.setfDate(rs.getString("fDate"));
				vo.setDownNum(rs.getInt("downNum"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setContent(rs.getString("content"));
				vo.setHostIp(rs.getString("hostIp"));
				
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

	//자료실에 신규 업로드시킨 파일의 정보를 DB에 저장한다 
	public int setPdsInputOk(PdsVO vo) {
		int res = 0;
		try {
			sql = "insert into pds values (default,?,?,?,?,?,?,?,?,default,default,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getfName());
			pstmt.setString(4, vo.getfSName());
			pstmt.setInt(5, vo.getfSize());
			pstmt.setString(6, vo.getTitle());
			pstmt.setString(7, vo.getPart());
			pstmt.setString(8, vo.getPwd());
			pstmt.setString(9, vo.getOpenSw());
			pstmt.setString(10, vo.getContent());
			pstmt.setString(11, vo.getHostIp());
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//idx를 이용한 개별자료 검색 (자료 삭제처리시 비밀번호 맞는지 확인하기위해서)
	public PdsVO getIdxSearch(int idx) {
		vo = new PdsVO();
		try {
			sql = "select * from pds where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();  //자료가 무조건 있음
			
			vo.setIdx(idx);
			//vo.setIdx(rs.getInt("idx")); 똑같음
			vo.setMid(rs.getString("mid"));
			vo.setNickName(rs.getString("nickName"));
			vo.setfName(rs.getString("fName"));
			vo.setfSName(rs.getString("fSName"));
			vo.setfSize(rs.getInt("fSize"));
			vo.setTitle(rs.getString("title"));
			vo.setPart(rs.getString("part"));
			vo.setPwd(rs.getString("pwd"));
			vo.setfDate(rs.getString("fDate"));
			vo.setDownNum(rs.getInt("downNum"));
			vo.setOpenSw(rs.getString("openSw"));
			vo.setContent(rs.getString("content"));
			vo.setHostIp(rs.getString("hostIp"));
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	//자료화일 삭제하기
	public String setPdsDelete(int idx) {
		String res = "0";
		try {
			sql ="delete from pds where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res ="1";
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//다운횟수 증가
	public void setPdsDownNum(int idx) {
		try {
			sql = "update pds set downNum = downNum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		
	}





}
