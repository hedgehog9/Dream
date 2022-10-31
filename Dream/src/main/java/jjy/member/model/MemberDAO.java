package jjy.member.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO implements InterMemberDAO {
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;

	// 생성자
	public MemberDAO() {

		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("/jdbc/dream");

			aes = new AES256(SecretMyKey.KEY);// KEY는 스태틱 변수이기때문에 객체생성 필요 x
			// SecretMyKey.KEY 는 우리가 만든 비밀키이다.

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) { // key 가 16글자 미만인경우 발생하는 예외 처리
			e.printStackTrace();
		}
	}

	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of try ~ catch()---------------------------------

	}

	
	
	//  로그인된 사용자 아이디를 Map으로 전달받아 tbl_member 테이블에 멤버십 가입 여부, 멤버십 가입 날짜를 업데이트 하는 메소드
	@Override
	public int registMembership(Map<String, String> useridMap)throws SQLException {
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_member set membership = 1, membership_regist_date = sysdate "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridMap.get("loginuserid") );
			result = pstmt.executeUpdate();
			// System.out.println("sql 실행 후 result = "+result);
			
		} finally {
			close();
		}
		return result; // update 성공시 return 1
		
	}// end of public int registMembership(Map<String, String> useridMap) {}--------------------------
	
	
	
//  로그인된 사용자 아이디를 Map으로 전달받아 tbl_member 테이블에 멤버십 가입 여부, 멤버십 가입 날짜를 업데이트 하는 메소드
	@Override
	public int deleteMembership(String userid)throws SQLException {
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_member set membership = 0, membership_regist_date = null "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			result = pstmt.executeUpdate();
//			System.out.println("sql 실행 후 result = "+result);
			
		} finally {
			close();
		}
		return result; // update 성공시 return 1
		
	}// end of public int registMembership(Map<String, String> useridMap) {}--------------------------
	
	
	
	
	

	
	
	
	//(select)로그인된 String 타입 userid 를 전달받아 tbl_member 테이블에서 
	// 가입일자,멤버십 가입여부,포인트,핸드폰번호,멤버십 가입일자를 가져오는 메소드  
	@Override
	public MemberDTO selectOneUser(String userid) throws SQLException {
		MemberDTO user = new MemberDTO();
//		System.out.println("MemberDAO selectOneUser 호출됨");
		try {
			conn = ds.getConnection();
			
			String sql = " select userid, joindate, membership, username, "
					   + " membership_regist_date, mobile, "
					   + " trunc( months_between(sysdate, membership_regist_date),3 ) AS membershipregistgap "
					   + " from tbl_member "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid); 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				user.setUserid(rs.getString("userid"));
				user.setJoindate(rs.getString("joindate"));
				user.setUsername(rs.getString("username"));
				user.setMembership(rs.getInt("membership")); // 멤버십 가입여부 0: 가입중 1: 미가입
				user.setMembershipregistgap(rs.getFloat("membershipregistgap"));
				
//				System.out.println("DAO에서 확인 "+ rs.getInt("membershipregistgap"));
				}
			
		} finally {
			close();
		}
		
		return user;
	}
	
}
