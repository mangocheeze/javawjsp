package study2.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class CrimeDAO {
	// 싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	CrimeVO vo = null;

	//범죄 자료 통계 등록
	public void setCrimeInputOk(CrimeVO vo) {
		try {
			sql = "insert into crime values (default,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getYear());
			pstmt.setString(2, vo.getPolice());
			pstmt.setInt(3, vo.getBurglar());
			pstmt.setInt(4, vo.getMurder());
			pstmt.setInt(5, vo.getTheft());
			pstmt.setInt(6, vo.getViolence());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		
		
	}

	//범죄 내역 삭제하기
	public String setCrimeDeleteOk() {
		String res = "0";
		try {
			sql ="delete from crime";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	//범죄 내역 가져오기
	public ArrayList<CrimeVO> getCrimeList() {
		ArrayList<CrimeVO> vos =  new ArrayList<>();
		try {
			sql = "select * from crime order by year desc,police ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setBurglar(rs.getInt("burglar"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("violence"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	// 총계와 평균 분석자료 가져오기
	public ArrayList<CrimeTotalVO> getCrimeTotalList(String str) {
		ArrayList<CrimeTotalVO> vos = new ArrayList<>();
		try {
			if(str.equals("T")) {
				sql = "select year,sum(burglar) as totBurglar,sum(murder) as totMurder,sum(theft) as totTheft,sum(violence) as totViolence from crime group by year order by year desc";
			}
			else {
				sql = "select year,avg(burglar) as totBurglar,avg(murder) as totMurder,avg(theft) as totTheft,avg(violence) as totViolence   from crime group by year order by year desc";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CrimeTotalVO vo = new CrimeTotalVO();
				vo.setYear(rs.getInt("year"));
				vo.setTotBurglar(rs.getInt("totBurglar"));
				vo.setTotMurder(rs.getInt("totMurder"));
				vo.setTotTheft(rs.getInt("totTheft"));
				vo.setTotViolence(rs.getInt("totViolence"));
				
				if(str.equals("T")) vo.setPart("합계");
				else vo.setPart("평균");
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

}
