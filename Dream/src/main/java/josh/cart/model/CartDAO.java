package josh.cart.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class CartDAO implements InterCartDAO{
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	// 생성자 
	public CartDAO() {
		
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
	public List<CartDTO> selectCart(String userid) throws SQLException{
		
		List<CartDTO> cartList = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select A.product_num, product_name, product_content, product_image, price, discount_rate, A.product_size, nvl(size_cnt,0) as size_cnt, cart_cnt, cart_num "
					+ " from "
					+ " ( "
					+ " select c.product_num as product_num, product_name, product_content, product_image, price, discount_rate , product_size, cart_cnt, cart_num "
					+ "      , c.product_num || product_size AS product_stock "
					+ " from tbl_product p join tbl_cart c "
					+ " on p.product_num = c.product_num "
					+ " where userid = ? "
					+ " )A "
					+ " left join "
					+ " ( "
					+ " select product_num, product_size, size_cnt, product_num || product_size AS product_stock "
					+ " from tbl_product_stock  "
					+ " )B "
					+ " on A.product_stock = B.product_stock "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			cartList = new ArrayList<>();
			while(rs.next()) {
				
				CartDTO cdto = new CartDTO();
				
				cdto.setProduct_num(rs.getInt(1));
				cdto.setProduct_name(rs.getString(2));
				cdto.setProduct_content(rs.getString(3));
				cdto.setProduct_image(rs.getString(4));
				cdto.setPrice(rs.getInt(5));
				cdto.setDiscount_rate(rs.getFloat(6));
				cdto.setProduct_size(rs.getString(7));
				cdto.setSize_cnt(rs.getInt(8));
				cdto.setCart_cnt(rs.getInt(9));
				cdto.setCart_num(rs.getInt(10));
				
				cartList.add(cdto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cartList;
	}

    // 체크 선택한 상품들만 장바구니에서 삭제하는 메소드
	@Override
	public int cartSelectDelete(String cart_num) throws SQLException {
		int n = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from tbl_cart where cart_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cart_num);
			
			n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return n;
	}
	
	
}















