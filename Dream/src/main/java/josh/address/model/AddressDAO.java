package josh.address.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.security.AES256;
import util.security.SecretMyKey;

public class AddressDAO implements InterAdressDAO{

	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	// 생성자 
	public AddressDAO() {
		
		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/dream");
			
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

	@Override
	public List<AddressDTO> selectAddress(String userid) throws SQLException {
		List<AddressDTO> adList = new ArrayList<AddressDTO>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select address_num, userid, basic_address, address, detail_address, post_code, order_name, mobile  "
					   + " from tbl_address "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AddressDTO adto = new AddressDTO();
				
				adto.setAddress_num(rs.getInt(1));
				adto.setUserid(rs.getString(2));
				adto.setBasic_address(rs.getInt(3));
				adto.setAddress(rs.getString(4));
				adto.setDetail_address(rs.getString(5));
				adto.setPost_code(rs.getString(6));
				adto.setOrder_name(rs.getString(7));
				adto.setMobile(rs.getString(8));
				
				adList.add(adto);
				
			}// end of while
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return adList;
	}
	
	
	
	
	
	
	
	
}










