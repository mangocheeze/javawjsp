package study2.ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;
import member.MemberVO;

public class UserDAO {
	
	//싱글톤을 이용한 DB연결 객체 연결하기...
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	UserVO vo = null;

	//user 테이블의 정보를 모두 가져오기
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from user order by idx desc"; //내림차순으로 모든 자료가져옴
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new UserVO();
				
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo);
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	//유저 개별조회 검색
	public UserVO getUserSearch(String mid) {
		//UserVO vo = new UserVO();
		try {
			sql = "select * from user where mid = ?"; //아이디가 같으면 보여줘라
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	//유저 삭제
	public String setUserDel(String mid) {
		String res = "0";
		try {
			sql = "delete from user where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate(); //삭제한걸 업데이트
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			getConn.pstmtClose(); //지우기만해서
		}
		return res;
	}
}
