package josh.product.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.security.AES256;
import util.security.SecretMyKey;

public class ProductDAO implements InterProductDAO {

	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	
	// 생성자 
	public ProductDAO() {
		
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

	@Override
	public int totalDiscount() throws SQLException {
		
		int totalDiscount = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select count(*) "
					   + " from tbl_product "
					   + " where discount_rate > 0 ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			totalDiscount = rs.getInt(1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		return totalDiscount;
	}

	@Override
	public List<ProductDTO> selectDiscountprd(Map<String, String> paraMap) throws SQLException{
		
		List<ProductDTO> discountList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql =  " select product_num, product_name, product_content, product_image, price, discount_rate, category"
					    + " from "
					    + " ( "
					    + " select rownum rno, product_num, product_name, product_content, product_image, price, discount_rate, category "
						+ " from "
						+ " ( "
						+ " select discount_rate, product_num, product_name, product_content, product_image, price, category  "
						+ " from tbl_product "
						+ " where discount_rate > 0 "
						+ " order by discount_rate desc "
						+ " ) V "
						+ " ) T "
						+ " where rno between ? and ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("start"));
			pstmt.setString(2, paraMap.get("end"));
			
			//System.out.println("확인용 start =>" + paraMap.get("start"));
			//System.out.println("확인용 end =>" + paraMap.get("end"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO pdto = new ProductDTO();
				pdto.setProduct_num(rs.getInt(1));
				pdto.setProduct_name(rs.getString(2));
				pdto.setProduct_content(rs.getString(3));
				pdto.setProduct_image(rs.getString(4));
				pdto.setPrice(rs.getInt(5));
				pdto.setDiscount_rate(rs.getFloat(6));
				pdto.setCategory(rs.getString(7));
				
				discountList.add(pdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		return discountList;
	}
	
	// cart 리스트에 상품 구매후 테이블 update 및 insert
	@Override
	public int cartPurchaseUpdate(Map<String, String> paraMap) throws SQLException{
		int n = 0;
		String cart_cnt = paraMap.get("cart_cnt");
		
		try {
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			// 장바구니 테이블에서 구매한 상품을 delete
			String sql = " delete from tbl_cart "
					   + " where cart_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("cart_num"));
			n += pstmt.executeUpdate();
			
			// 재고테이블에서 (update) 수량삭제 ==> 필요한 것(product_num+size 복합키)
			sql = " update tbl_product_stock set size_cnt = size_cnt-" +cart_cnt+ " " 
				+ " where product_num = ? and product_size = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("product_num"));
			pstmt.setString(2, paraMap.get("product_size"));
			
			n += pstmt.executeUpdate();
			
			// 구매내역 테이블 insert (상품마다 insert 해야됨) product_num + size
			sql = " insert into tbl_buylist(order_num, userid, product_num, buy_cnt, shipping, fk_address_num, product_size) values(seq_buylist.nextval,?,?,?,0,?,?) ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("product_num"));
			pstmt.setString(3, paraMap.get("cart_cnt"));
			pstmt.setString(4, paraMap.get("fk_address_num"));
			pstmt.setString(5, paraMap.get("product_size"));
			
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

	
	
	// cart의 수량을 업데이트하는 메소드
	@Override
	public int updateCart(String cart_num, String cart_qty) throws SQLException {
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " update tbl_cart set cart_cnt = ? "
					   + " where cart_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cart_qty);
			pstmt.setString(2, cart_num);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return result;
	}
	
	
}




























