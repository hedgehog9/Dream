package hgb.member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import hgb.member.model.MemberDTO;
import jjy.purchase.model.PurchaseListDTO;
import kim.member.model.PointVO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO implements InterMemberDAO{

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
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("/jdbc/dream");
			
			aes = new AES256(SecretMyKey.KEY);// KEY는 스태틱 변수이기때문에 객체생성 필요 x
			// SecretMyKey.KEY 는 우리가 만든 비밀키이다.
			
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) { // key 가 16글자 미만인경우 발생하는 예외 처리
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
	
	
	
	
	// 회원한명의 상세정보를 보여주는 메소드
	@Override
	public MemberDTO memberOneDetail(String userid) throws SQLException {
		
		MemberDTO member = null;
		
		
		
		try {
			conn = ds.getConnection();
			
			String sql = " select userid, to_char(joindate,'yyyy-mm-dd') as joindate, membership, username, mobile, secession, rest_member "
					   + " from "
					   + " ( "
					   + " select rownum AS RNO, userid, joindate, membership, username, mobile, secession, rest_member "
					   + " from "
					   + " ( "
					   + " select M.userid, joindate, membership, username, mobile, secession, rest_member "
					   + " from tbl_member M join tbl_member_login L "
					   + " on M.userid = L.userid "
					   + " where M.userid = ? "
					   + " order by joindate desc "
					   + " ) A "
					   + " ) B  ";				   					  															
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDTO();
				
				
				member.setUserid(rs.getString(1));
				member.setJoindate(rs.getString(2));
				member.setMembership(rs.getInt(3)); 
				member.setUsername(rs.getString(4));
				member.setMobile(rs.getString(5));
				member.setSecession(rs.getInt(6));
				member.setRest_member(rs.getInt(7));
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		
		
		return member;
				
	}// end of public MemberDTO memberOneDetail(String userid) throws SQLException-------------------

  
  
   
   
	// 회원한명의 구매내역을 보여주는 메소드
	@Override
	public ArrayList<PurchaseListDTO> memberOneBuyDetail(String userid) throws SQLException {
		
		ArrayList<PurchaseListDTO> plist = new ArrayList<PurchaseListDTO>();
		
		
		
		try {
			conn = ds.getConnection();
			
			String sql = " select order_num , product_num, buy_cnt, buy_date, shipping "
					   + " from tbl_buylist "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				PurchaseListDTO pdto = new PurchaseListDTO();
				
				
				pdto.setOrder_num(Integer.parseInt(rs.getString(1)) );
				pdto.setProduct_num(Integer.parseInt(rs.getString(2)));
				pdto.setBuy_cnt(Integer.parseInt(rs.getString(3)));
				pdto.setBuy_date(rs.getString(4));
				pdto.setShipping(Integer.parseInt(rs.getString(5)));
				cnt++;
				
				plist.add(pdto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		
		
		return plist;
		
	}

  
	/** 회원한명의 포인트내역을 보여주는 메소드 */
	@Override
	public ArrayList<PointVO> memberOnePointDetail(String userid) throws SQLException {
		
		ArrayList<PointVO> pointlist = new ArrayList<PointVO>();				
		
		try {
			conn = ds.getConnection();
			
			String sql = "select  point_num, point_amount, event_date, status, event_type, "
					   + "  sum(CASE WHEN  status = '적립'  THEN point_amount "
					   + "        WHEN  status = '차감' THEN -point_amount "
					   + "        else 0 END)OVER(ORDER BY point_num) as sum, "
					   + "    sum(CASE WHEN  status in ('적립' , '차감')  THEN 1 end)OVER(ORDER BY point_num) as point_number_see "
					   + " from tbl_point "
					   + " where userid = ? "
					   + " order by point_num desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				PointVO pdto = new PointVO();
				
				
				pdto.setPoint_num(Integer.parseInt(rs.getString(1)));				
				pdto.setPoint_amount(Integer.parseInt(rs.getString(2)));
				pdto.setEvent_date(rs.getString(3));
				pdto.setStatus(rs.getString(4));
				pdto.setEvent_type(rs.getString(5));
				pdto.setSum(rs.getString(6));
				pdto.setPoint_number_see(rs.getString(7));
				
				
				pointlist.add(pdto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		
		
		return pointlist;
		
	}

	@Override
	public int editmember(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		
		String sql = "";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			
			if( paraMap.get("passwd") != "없음") {
				sql = " update tbl_member_login set  passwd = ? , secession = ? , rest_member = ? "
					   + " where userid = ? "; 
					   
			
				pstmt = conn.prepareStatement(sql);					

				pstmt.setString(1, Sha256.encrypt(paraMap.get("passwd")));	//비밀번호 단방향 암호화);
				pstmt.setString(2, paraMap.get("secession"));
				pstmt.setString(3, paraMap.get("rest_member"));
				pstmt.setString(4, paraMap.get("userid"));	
			}
			else{
				sql = " update tbl_member_login set   secession = ? , rest_member = ? "
						   + " where userid = ? "; 
						   
				
				pstmt = conn.prepareStatement(sql);					

				pstmt.setString(1, paraMap.get("secession"));
				pstmt.setString(2, paraMap.get("rest_member"));
				pstmt.setString(3, paraMap.get("userid"));	
			}
					
			
			int n = pstmt.executeUpdate();
			
			if (n == 1) {								
				
				sql =  " update tbl_member set  username = ? , MEMBERSHIP = ? , MEMBERSHIP_REGIST_DATE = sysdate "
						+ " where userid = ? ";
				
				pstmt = conn.prepareStatement(sql);					
				
				pstmt.setString(1, paraMap.get("username"));
				pstmt.setString(2, paraMap.get("membership"));
				pstmt.setString(3, paraMap.get("userid"));			
				
			    n = pstmt.executeUpdate();
			    
			    if (n == 1) {
			    	result = n; // result == 1
			    	conn.commit();
		    	
			    }
			    else {// 두번째 업데이트가 안된 경우 
			    	conn.rollback();
			    	
			    }
				
			}
			else { // 첫 업데이트가 안된 경우 
				
				
			}
			

			conn.rollback();
			conn.setAutoCommit(true); 

		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			
			conn.setAutoCommit(true); 
			close();
		}		
		
		
		return result;
		
	}
	
	
	
	/** 회원목록에서 회원을 삭제시키기 */
	@Override
	public int delMember(String userid) throws SQLException {
		
		int n = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " delete from tbl_member "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			n = pstmt.executeUpdate();					
			
		} finally {
			close();
		}
		
		
		return n;
	
	}// end of public int delMember(String userid) throws SQLException ----------------------
	
	






}













