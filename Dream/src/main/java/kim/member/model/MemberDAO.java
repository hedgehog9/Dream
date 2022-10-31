package kim.member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jjy.member.model.MemberDTO;
import kim.member.model.InterMemberDAO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO implements InterMemberDAO {
	
	
	
	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.  
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	
	// 기본생성자
	public MemberDAO() {
		
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/dream");
		    
		    aes = new AES256(SecretMyKey.KEY);
		    // SecretMyKey.KEY 은 우리가 만든 비밀키이다.
		    
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기 
	private void close() {
		try {
			if(rs != null)    {rs.close();    rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn != null)  {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	
	

	@Override
	public int updateCart(Map<String, String> paraMap) throws SQLException{
		
		int result = 0;
		
		try {
			
			int length = Integer.parseInt(paraMap.get("length"));
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			
			int count = 0; // 체크용
			int n =0;
			String sql="";
			
			
			for(int i=0; i<length;i++) {
				sql = " MERGE INTO tbl_cart "+
						" USING dual "+
						" ON (userid= ? and PRODUCT_NUM = ? and product_size=?) "+
						" WHEN MATCHED THEN "+
						" UPDATE SET cart_cnt = cart_cnt+ ? "+
						" WHEN NOT MATCHED THEN " +
						" insert (CART_NUM, USERID, PRODUCT_NUM, CART_CNT , PRODUCT_SIZE) "+
						" values(seq_cart_num.nextval , ? ,?,?, ?) ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, paraMap.get("userid"));
				pstmt.setString(2, paraMap.get("productNum"));
				pstmt.setString(3, paraMap.get("size"+i));
				pstmt.setString(4, paraMap.get("cnt"+i));
				pstmt.setString(5, paraMap.get("userid"));
				pstmt.setString(6, paraMap.get("productNum"));
				pstmt.setString(7, paraMap.get("cnt"+i));
				pstmt.setString(8, paraMap.get("size"+i));
				
				n = pstmt.executeUpdate();
			
				count += n;
			}
			
			if(count == length) {
				// 추가하려는 만큼 다 insert 성공했다면
				conn.commit(); 
				result = count;
				// system.out.println(result+"개 행 insert 성공!");
				
			}
			else {
				// 추가하려값만큼 insert 되지 않았다면
				conn.rollback();
			
				result = -1;
				
			}
			
			
			
		
	
			
			
			
		}finally {
			conn.setAutoCommit(true); 
			close();
		}
		
		return result;
		
	}

	@Override
	public Map<String, String> likeCheck(Map<String, String> paraMap) throws SQLException {
		
		Map<String, String> resultMap = new HashMap<>();
		String resultType = "";
		String resultSuccess = "false";
		int n = 0;
		
		String userid = paraMap.get("userid");
		
		String productNum = paraMap.get("productNum");
		
		try {
			conn = ds.getConnection();
			
			String sql = " select userid, product_num "+
					" from tbl_like "+
					" where userid= ? and product_num = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			
			
			pstmt.setString(1, userid); 
			pstmt.setString(2,productNum );
			
			rs = pstmt.executeQuery();
			
			
	
			

			if(rs.next()) {
				
				sql = " delete from tbl_like "+
						" where userid = ?  and product_num = ? ";
//				// system.out.println("좋아요있음");
				
				pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, userid); 
				pstmt.setString(2,productNum );

				n = pstmt.executeUpdate();
				if(n==1) {
//					// system.out.println("좋아요삭제성공");
					resultType = "remove";
					resultSuccess = "true";
					
				}
				else {
//					// system.out.println("좋아요삭제실패");
					resultType = "remove";
					resultSuccess = "false";
				}
				
		
			}
			 else {
//				 // system.out.println("좋아요 없음");
				 sql = " insert into tbl_like(userid, product_num) "+
					   " values( ? , ?) ";
 
				    pstmt = conn.prepareStatement(sql); 

				    pstmt.setString(1, userid);
					pstmt.setString(2, productNum );
					 
					n = pstmt.executeUpdate();
					if(n==1) {
//						// system.out.println("좋아요등록성공");
						resultType = "add";
						resultSuccess = "true";
					
					}
					else {
//						// system.out.println("좋아요등록실패");
						resultType = "add";
						resultSuccess = "false";
					}

			
			 }
			
			
			resultMap.put("resultType", resultType);
			resultMap.put("resultSuccess", resultSuccess);
			
			// 좋아요 수 가져오기
			String sql2 = "  select count(*) "+
					" from tbl_like "+
					" where product_num =? ";

		    pstmt = conn.prepareStatement(sql2); 

			pstmt.setString(1, productNum );
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				// system.out.println("count" + rs.getString(1));
				resultMap.put("count", rs.getString(1));
			}
			else { //없는경우
				resultMap.put("count", "0");
			}
			return resultMap;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			close();
			return resultMap;
		}
		

	}

	@Override
	public MemberVO pointCheck(String sessionUserid) throws SQLException {


		MemberVO mvo = new MemberVO();

		try {
		
			
	
				// 여기서부터 포인트전까지 조쉬거 훔쳐옴
				conn = ds.getConnection();
				
				String sql = " select A.userid, mobile, username, membership "
						    + " from\n"
						    + " (\n"
							+ " select userid, mobile, username, membership "
							+ " from tbl_member "
							+ " )A "
							+ " join "
							+ " ( "
							+ " select userid, passwd "
							+ " from tbl_member_login "
							+ " )B "
							+ " on A.userid = B.userid "
							+ " where A.userid = ? ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, sessionUserid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					mvo.setUserid(rs.getString(1));
					mvo.setMobile(rs.getString(2));
					mvo.setUsername(rs.getString(3));
					mvo.setMembership(rs.getInt(4));
				}
				
				// 포인트
				
				conn = ds.getConnection();
				
				String sql2 = " SELECT SUM(CASE WHEN  status = '적립' THEN point_amount "+
						"        WHEN  status = '차감' THEN -point_amount "+
						"        else 0 END) "+
						" FROM tbl_point "+
						" where userid = ? ";
				
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setString(1, sessionUserid); // 세션 아이디값

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					// 내역 없어도 sum 이라 한행이 나옴
					// 근데 내역이 하나도 없으면 null 나옴
					
					if( rs.getString(1) != null) {
						// 내역이 존재한 경우에는 총합값을 저장
						mvo.setPoint(rs.getInt(1));
					}
					else {
						// 내역이 없는 경우에는 0 을 저장
						mvo.setPoint(0);
					}
					
				}
			

			
			
		} finally {
			close();
		}
		
		return mvo;
		
		
		
	}

	@Override
	public List<PointVO> allPointList(String userid) throws SQLException{

		List<PointVO> pointList= new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql = " select point_num, userid,point_amount,event_date, status, event_type "+
					" from tbl_point "+
					" where userid = ? "
					+ " order by 1 desc";

		
				pstmt = conn.prepareStatement(sql);
	
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					PointVO pvo = new PointVO();
					
					pvo.setPoint_num(rs.getInt(1));
					pvo.setUserid(rs.getString(2));
					pvo.setPoint_amount(rs.getInt(3));
					pvo.setEvent_date(rs.getString(4));
					pvo.setStatus(rs.getString(5));
					pvo.setEvent_type(rs.getString(6));
					
					pointList.add(pvo);

				}
				
		}finally {
		
			close();
		}
		
		return pointList;
	}

	@Override
	public int getTotalPoint(String userid) throws SQLException {
		
		conn = ds.getConnection();
		int n = -1;
		try {
			String sql2 = " SELECT SUM(CASE WHEN  status = '적립' THEN point_amount "+
					"        WHEN  status = '차감' THEN -point_amount "+
					"        else 0 END) "+
					" FROM tbl_point "+
					" where userid = ? ";
			
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setString(1, userid); // 세션 아이디값
	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 내역 없어도 sum 이라 한행이 나옴
				// 근데 내역이 하나도 없으면 null 나옴
				
				if( rs.getString(1) != null) {
					// 내역이 존재한 경우에는 총합값을 저장
					n = rs.getInt(1);
				}
				else {
					// 내역이 없는 경우에는 0 을 저장
					n = 0;
				}
				
			}
			return n;
		}finally{
			close();
			
		}
	}

	@Override
	public int reply(Map<String, String> paraMap) throws SQLException {
		int result = 0;
		conn = ds.getConnection();
		int n = -1;
		try {
		
			String sql = " insert into tbl_message (messageno, fk_sender_userid, fk_Recipient_userid, title, Contents) "+
					" values(seq_message_num.nextval , ? ,?,?, ?) ";
				
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("fk_sender_userid")); 
			pstmt.setString(2, paraMap.get("fk_Recipient_userid")); 
			pstmt.setString(3, paraMap.get("title")); 
			pstmt.setString(4, paraMap.get("contents")); 
			
			
			result = pstmt.executeUpdate();
				
			
			return result;
		}finally{
			close();
			
		}
	}

	@Override
	public int deletemessage(String messageno, String type) throws SQLException {
		
		int result = 0;
		conn = ds.getConnection();
		
		String set = "RECIPIENT_DELETE";
		if( !type.equalsIgnoreCase("receieve")) {
			set = "SENDER_DELETE";
		}
		
		try {
		
			String sql = " update tbl_message set  "+ set +" = 1 "+
					" where MESSAGENO in ("+ messageno+ ") ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			 
			
			
			result = pstmt.executeUpdate();
				
			
			return result;
			// 여기선 처리된 행 개수 반환만 해준다
		}finally{
			close();
			
		}
		
		
	}
	
	
	
	@Override
	public int readmessage(String messageno) throws SQLException {
		
		int result = 0;
		conn = ds.getConnection();

		try {
		
			String sql = " update tbl_message set  read_check = 1 "+
					" where MESSAGENO = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, messageno); 
			 
			
			
			result = pstmt.executeUpdate();
			
			if(result != 1) {
				// system.out.println("메시지가 읽음처리 되지않았습니다.");
				result = 0;
			}
				
			
			return result;
			
		}finally{
			close();
			
		}
		
		
	}
	

}
