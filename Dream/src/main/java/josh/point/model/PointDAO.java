package josh.point.model;

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

public class PointDAO implements InterPointDAO {

	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	
	// 생성자 
	public PointDAO() {
		
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
		
	}// end of private void close() {} --------------------------

	@Override
	public int insertPoint(Map<String, String> paraMap) throws SQLException {
		
		int n = 0;
		
		try {
			
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
			//System.out.println("포인트 dao에 진입함");
			//System.out.println(Integer.parseInt(paraMap.get("minusPoint")));
			//System.out.println(Integer.parseInt(paraMap.get("plusPoint")));
			
			if(Integer.parseInt(paraMap.get("minusPoint")) > 0) {
				
				// System.out.println("포인트 dao에 진입함");
				
				// 결제로 인한 포인트 차감
				String sql = " insert into tbl_point (point_num, userid, point_amount , status, event_type, event_date) "
			        + " values(seq_point_num.nextval , ? , ? , '차감',  '상품구매' ,sysdate) "; 
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, paraMap.get("userid"));
				pstmt.setString(2, paraMap.get("minusPoint"));
				
				n += pstmt.executeUpdate();
				
				conn.commit();
			}
			
			// 결제로 인한 포인트 추가
			String sql = " insert into tbl_point (point_num, userid, point_amount , status, event_type, event_date) "
		               + " values(seq_point_num.nextval , ? , ? , '적립',  '상품구매' ,sysdate) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("plusPoint"));
			
			n += pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.setAutoCommit(true);
			close();
		}
		
		return n;
	}
	
	

}













