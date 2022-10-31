package choi.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.security.Sha256;

public class MemberDAO implements InterMemberDAO{
	// DBCP
			private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
			private Connection conn;
			private PreparedStatement pstmt;
			private ResultSet rs;
			
			
			// 생성자 
			public MemberDAO() {
				
				try {
					// connection pool 을 위한 작업
					Context initContext = new InitialContext();
					Context envContext  = (Context)initContext.lookup("java:/comp/env");
					ds = (DataSource)envContext.lookup("/jdbc/dream");
					
					
				} catch(NamingException e) {
					e.printStackTrace();
				}
			}
			
			// 사용한 자원을 반납하는 close() 메소드 생성하기
			private void close() {
				
				try {
					if(rs != null)    { rs.close();    rs = null; }
					if(pstmt != null) { pstmt.close(); pstmt = null; }
					if(conn != null)  { conn.close();  conn = null; }
				}catch(SQLException e){
					e.printStackTrace();
				}// end of try ~ catch()---------------------------------
				
			}

			@Override
			// ID 중복검사 (tbl_member 테이블에서 userid 가 존재하면 true를 리턴해주고, userid 가 존재하지 않으면 false를 리턴한다)
			public boolean idDuplicateCheck(String userid) throws SQLException{
				boolean isExists = false;
				
				try {
					
					conn = ds.getConnection();
					
					String sql = " select userid "
							   + " from tbl_member_login "
							   + " where userid = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					
					rs = pstmt.executeQuery();
					
					isExists = rs.next(); // 행이 있으면 (중복된 userid) true, 
										  // 행이 없으면 (사용가능한 userid) false  
					
				} finally {
					close();
				}
				
				return isExists;
			}//end of public boolean idDuplicateCheck(String userid) throws SQLException--

			
			
			
			// 맵에 회원 아이디와 비밀번호를 받아와서, 회원테이블과 회원로그인테이블에 insert를 해주는 메서드
			@Override
			public int memberJoin(Map<String, String> paraMap) throws SQLException {
				
				int n = 0;
				
				try {
					conn = ds.getConnection();
					conn.setAutoCommit(false);	//트랜잭션 처리를 위해서 autocommit
					
					
					String sql = " insert into tbl_member(userid,username,mobile) "
							   + " values(?, ?, ?) ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("userid"));
					pstmt.setString(2, paraMap.get("username"));
					pstmt.setString(3, paraMap.get("mobile"));
					
					n = pstmt.executeUpdate();
					
					if(n != 1) {	//tbl_member 테이블에 insert가 실패시
						conn.rollback();
						conn.setAutoCommit(true);
						return n;
					}
					else {	//tbl_member 테이블에 insert가 성공시 tbl_member 테이블에도 insert하기
						
						sql = " insert into tbl_member_login(userid,passwd) "
							 +" values(? , ?) ";
					
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, paraMap.get("userid"));
						pstmt.setString(2, Sha256.encrypt(paraMap.get("passwd")));	//비밀번호 단방향 암호화
						
						n += pstmt.executeUpdate();
						
						if(n!=2) {	//tbl_member_login insert 실패시
							conn.rollback();
							conn.setAutoCommit(true);
							return n;
						}
						else {	//tbl_member_login 테이블에 insert하는것까지 성공시
							sql = "insert into tbl_point(point_num,userid,point_amount,status,event_type) "
								 +" values(seq_point_num.nextval , ?, 2000, '적립', '신규가입') ";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, paraMap.get("userid"));
							n += pstmt.executeUpdate();
							
							if(n != 3) {
								conn.rollback();
								conn.setAutoCommit(true);
								return n;
							}
							else {
								conn.commit();
								conn.setAutoCommit(true);
								return n;
							}
						}
					}//end of else---
				} finally {
					close();
				}
			}//end of public int memberJoin(Map<String, String> paraMap) throws SQLException

			
			
			
			// 회원 아이피를 파라미터로 받아서, 로그인기록테이블에서 유저아이디를 검색해오는 메소드
			@Override
			public String select_ip(String ipAddress) throws SQLException{
				String userid = null; 
				
				try {
					
					conn = ds.getConnection();
					
					String sql = " select userid from tbl_login_record "
							   + " where client_ip = ? "
							   + " order by login_date desc ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ipAddress);
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						userid = rs.getString("userid");
					}
					
				} finally {
					close();
				}
				
				return userid;
			}
			
			
			// 회원 아이피를 파라미터로 받아서, 회원테이블에서 유저이름를 검색해오는 메소드
			@Override
			public String select_username(String userid) throws SQLException {
				String username = ""; 
				
				try {
					
					conn = ds.getConnection();
					
					String sql = " select username "
							   + " from tbl_member"
							   + " where userid = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						username = rs.getString("username");
					}
					
				} finally {
					close();
				}
				
				return username;
			}
			
			// mobile 중복검사 (tbl_member 테이블에서 mobile이 존재하면 true를 리턴해주고, mobile 가 존재하지 않으면 false를 리턴한다)
			@Override
			public boolean mobileDuplicateCheck(String mobile) throws SQLException {
				boolean isExists = false;
				
				try {
					
					conn = ds.getConnection();
					
					String sql = " select mobile "
							   + " from tbl_member "
							   + " where mobile = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mobile);
					
					rs = pstmt.executeQuery();
					
					isExists = rs.next(); // 행이 있으면 (중복된 userid) true, 
										  // 행이 없으면 (사용가능한 userid) false  
					
				} finally {
					close();
				}
				
				return isExists;
			}
}
