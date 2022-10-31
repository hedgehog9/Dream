package choi.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class LikeDAO implements InterLikeDAO{
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	// 생성자 
	public LikeDAO() {
		
		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/dream");
			
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
	
	
	//로그인중인 회원의 아이디와 제품번호를받아서 좋아요를 눌렀는지 체크하는 메소드
	@Override
	public boolean likeCheck(Map<String, String> paraMap) throws SQLException{
		boolean like_exist = false;
		try {
			
			conn = ds.getConnection();
			
			String sql = " select userid "
					   + " from tbl_like "
					   + " where userid = ? and product_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("productNum"));
			
			rs = pstmt.executeQuery();
			
			like_exist = rs.next(); // 행이 있으면 (중복된 userid) true, 
								  // 행이 없으면 (사용가능한 userid) false  
			
		} finally {
			close();
		}
		
		return like_exist;
	}//end of public boolean likeCheck(Map<String, String> paraMap)
	
	
	
	//로그인중인 회원의 아이디를 파라미터로 받아서, 좋아요 테이블에 insert 해주는 메소드
	@Override
	public int likeinsert(Map<String, String> paraMap) throws SQLException{
		int n = 0;
		try {
			
			conn = ds.getConnection();
			
			String sql = " insert into tbl_like(userid,product_num) "
					   + " values(?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("productNum"));
			
			n = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return n;
	}//end of public int likeinsert(Map<String, String> paraMap)

	
	
	
	//로그인중인 회원의 아이디를 파라미터로 받아서, 좋아요 테이블에 delete 해주는 메소드
	@Override
	public int likeDelete(Map<String, String> paraMap) throws SQLException {
		int n = 0;
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from tbl_like "
					   + " where userid = ? and product_num = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("productNum"));
			
			n = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return n;
	}//end of public int likeDelete(Map<String, String> paraMap)
	
	
	
	
}
