package jjy.purchase.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jjy.address.model.AddressDTO;
import jjy.product.model.ProductDTO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class PurchaseListDAO implements InterPurchaseListDAO {

	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;

	// 생성자
	public PurchaseListDAO() {

		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("/jdbc/dream");

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

	// 전체 구매내역 구해오는 메소드 
	@Override
	public List<PurchaseListDTO> allPurchaseList(Map<String, String> purchaseMap)throws SQLException {

		List<PurchaseListDTO> purchaseList = new ArrayList<>();

		try {
			conn = ds.getConnection();
			
//			System.out.println("확인용 : "+purchaseMap.get("userid"));
//			System.out.println("확인용 : "+purchaseMap.get("input_shipping"));
//			System.out.println("확인용 : "+purchaseMap.get("start_date"));
//			System.out.println("확인용 : "+purchaseMap.get("end_date"));
//			System.out.println("확인용 : "+purchaseMap.get("sort"));
			
			
			String sql = " select order_num, userid, B.product_num, buy_cnt , buy_date , shipping, product_name, product_image, rownum as rno "+
					     " from tbl_buylist B left join tbl_product P "+
					     " on B.product_num = P.product_num "+
					     " where userid = ? and shipping = ? "+
					     " and buy_date between TO_DATE( ?  , 'YYYY/MM/DD') AND TO_DATE( ? , 'YYYY/MM/DD') "+
					     " order by order_num "+purchaseMap.get("sort");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, purchaseMap.get("userid"));
			pstmt.setString(2, purchaseMap.get("input_shipping") );
			pstmt.setString(3, purchaseMap.get("end_date") );
			pstmt.setString(4, purchaseMap.get("start_date") );
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseListDTO pdto = new PurchaseListDTO();

				pdto.setOrder_num(rs.getInt("order_num") );     	  // 일련번호
				pdto.setUserid(rs.getString("userid"));         	  // 주문자 아이디
				pdto.setProduct_num(rs.getInt("product_num"));  	  // 주문 제품
				pdto.setBuy_cnt(rs.getInt("buy_cnt"));          	  // 구매 수량
				pdto.setBuy_date(rs.getString("buy_date"));     	  // 구매날짜
				pdto.setShipping(rs.getInt("shipping"));              // 배송상태
				
				ProductDTO prodDTO = new ProductDTO();				  //ProductDTO에 있는 제품이름, 제품이미지 저장
				prodDTO.setProduct_name(rs.getString("product_name")); // 제품이름
				prodDTO.setProduct_image(rs.getString("product_image"));// 제품이미지
				pdto.setProdDTO(prodDTO);
				
				
				purchaseList.add(pdto);
//				System.out.println("확인용 prdoDTO name : "+prodDTO.getProduct_name());
//				System.out.println("확인용 prdoDTO image :"+prodDTO.getProduct_image());
			}
		} finally {
			close();
		}

		return purchaseList;

	}

	
	// 사용자 아이디, 시작일, 종료일, 배송상태를 Map으로 전달받아 출력해야 할 페이지 수를 구해오는 메소드 (select) 
	@Override
	public int getTotalPage(Map<String, String> purchaseMap) throws SQLException {
		
		int totalPage = 0;
		
		
		try {
			conn = ds.getConnection();
			
			String sql = "select ceil(count(*)/10) " // 한페이지에 보여줄 구매내역 10개로 고정
					   + "from tbl_buylist B left join tbl_product P "
					   + "on B.product_num = P.product_num "
					   + "where userid = ? and shipping = ?  "
					   + "and buy_date between TO_DATE( ? , 'YYYY/MM/DD') AND TO_DATE( ? , 'YYYY/MM/DD') "
					   + "order by buy_date " +purchaseMap.get("sort");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, purchaseMap.get("userid"));
			pstmt.setString(2, purchaseMap.get("input_shipping") );
			pstmt.setString(3, purchaseMap.get("end_date") );
			pstmt.setString(4, purchaseMap.get("start_date") );
			
			rs = pstmt.executeQuery();

			rs.next();
			
			totalPage = rs.getInt(1);

		} finally {
			close();
		}
		
		return totalPage;
	}

	
	
	// 페이징처리를 위한 구매내역 조회하기 
	@Override
	public List<PurchaseListDTO> selectPagingPurchaseList(Map<String, String> purchaseMap) throws SQLException {
		
		List<PurchaseListDTO> pagingPurchaseList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			// 내일 여기 수정 (desc asc 변수처리 ) ==> 수정 완료 
			String sql = " select order_num, userid, product_num, buy_cnt , buy_date , shipping, product_name, product_image, rno "+
					     " from "+
					     " ( "+
					     "     select order_num, userid, B.product_num as product_num, buy_cnt , buy_date , shipping, product_name, product_image,"+
					     "     row_number() over(order by order_num "+ purchaseMap.get("sort") +") as rno "+
					     "     from tbl_buylist B left join tbl_product P "+
					     "     on B.product_num = P.product_num "+
					     "     where userid = ? ";
					     
						 if( Integer.parseInt(purchaseMap.get("input_shipping")) == 0) {
							   sql += " and shipping between 0 and 1 ";
						 }	
						 else {
							   sql += " and shipping = 2 ";
						 }
					     
//					     sql +="     and buy_date between TO_DATE( ? , 'YYYY/MM/DD HH24') AND TO_DATE( ? , 'YYYY/MM/DD') "+
			    		 sql +="     and buy_date between TO_DATE( ? , 'YYYY/MM/DD HH24') AND TO_DATE(' "+purchaseMap.get("start_date")+" 23:59' , 'YYYY/MM/DD hh24:mi:ss') "+
					     " )v "+
					     " where rno between ? and ? ";
					     /* + " order by order_num "+purchaseMap.get("sort");*/
					     
					    //   System.out.println("dao 에서 확인 확인용 sql = "+sql);
			 
			 // === 페이징처리의 공식 ===
			 // where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수); 
			
			 int sizePerPage = 5; // 한 페이지당 화면상에 보여줄 제품의 개수는 5로 고정
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, purchaseMap.get("userid") );
//			 pstmt.setString(2, purchaseMap.get("input_shipping") );
			 pstmt.setString(2, purchaseMap.get("end_date") );
//			 pstmt.setString(3, purchaseMap.get("start_date") );
			 pstmt.setString(3, purchaseMap.get("start") );
			 pstmt.setString(4, purchaseMap.get("end") );
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
		
				 PurchaseListDTO purchasedto = new PurchaseListDTO();
				 
				 purchasedto.setOrder_num(rs.getInt("order_num") );     	  // 일련번호
				 purchasedto.setUserid(rs.getString("userid"));         	  // 주문자 아이디
				 purchasedto.setProduct_num(rs.getInt("product_num"));  	  // 주문 제품
				 purchasedto.setBuy_cnt(rs.getInt("buy_cnt"));          	  // 구매 수량
				 purchasedto.setBuy_date(rs.getString("buy_date"));     	  // 구매날짜
				 purchasedto.setShipping(rs.getInt("shipping"));              // 배송상태
				 	
				 ProductDTO prodDTO = new ProductDTO();				  //ProductDTO에 있는 제품이름, 제품이미지 저장
				 prodDTO.setProduct_name(rs.getString("product_name")); // 제품이름
				 prodDTO.setProduct_image(rs.getString("product_image"));// 제품이미지
				 purchasedto.setProdDTO(prodDTO);
				 	
				 pagingPurchaseList.add(purchasedto);
				 
			 }
		} finally {
			close();
		}
		
		return pagingPurchaseList;
		
	} // end of public List<PurchaseListDTO> selectPagingPurchaseList(Map<String, String> purchaseMap) throws SQLException {}--------------

	
	// 출력해야 할 구매내역 수 조회하기 
	@Override
	public int getTotalListCnt(Map<String, String> purchaseMap) throws SQLException {
		
		int listCnt = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select count(*) " // 한페이지에 보여줄 구매내역 10개로 고정
					   + " from tbl_buylist B left join tbl_product P "
					   + " on B.product_num = P.product_num "
					   + " where userid = ? ";
					   
					   if( Integer.parseInt(purchaseMap.get("input_shipping")) == 0) {
						   sql += " and shipping between 0 and 1 ";
					   }	
					   else {
						   sql += " and shipping = 2 ";
					   }
			
				   sql += " and buy_date between TO_DATE( ? , 'YYYY/MM/DD') AND TO_DATE( ? , 'YYYY/MM/DD') "
					   +  " order by buy_date " +purchaseMap.get("sort");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, purchaseMap.get("userid"));
//			pstmt.setString(2, purchaseMap.get("input_shipping") );
			pstmt.setString(2, purchaseMap.get("end_date") );
			pstmt.setString(3, purchaseMap.get("start_date") );
			
			rs = pstmt.executeQuery();

			rs.next();
			
			listCnt = rs.getInt(1);

		} finally {
			close();
		}
		
		return listCnt;
		
	}// public int getTotalListCnt(Map<String, String> purchaseMap) throws SQLException {}----------------------------------

	
	/** 사용자 아이디를 입력받아 배송상태가 "진행중" , "종료" 의 개수 알아오는 메소드 */
	@Override
	public Map<String, String> getOrderCnt(String loginedUserid)throws SQLException {
		
		Map<String,String> orderCntMap = new HashMap<>();
		
		try {
			conn = ds.getConnection();
			
			// "배송준비중", "진행중"인 주문의 개수를 알아오는 sql 
			String sql = " select count(*) "
					   + " from tbl_buylist "
					   + " where userid = ? and shipping between 0 and 1 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginedUserid);
			rs = pstmt.executeQuery();
			rs.next();
			orderCntMap.put("shipping", rs.getString(1));
			
			// 배송상태가 "종료"인 주문의 개수를 알아오는 sql 
			sql = " select count(*) "
				+ " from tbl_buylist "
				+ " where userid = ? and shipping = 2 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginedUserid);
			rs = pstmt.executeQuery();
			rs.next();
			
			orderCntMap.put("shipping_end", rs.getString(1));
			

		} finally {
			close();
		}
		
		return orderCntMap;
		
	}// end of public Map<String, String> getOrderCnt(String loginedUserid) {}-------------------------------------------------------------

	
	//주문번호 으로 전달받아 해당 주문번호에 해당하는 주문내역을 구해오는 메소드 
	@Override
	public PurchaseListDTO getDetailPurchaseList(String order_num) throws SQLException {
		
		PurchaseListDTO purchaseListDTO = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT order_num, address_num , address, detail_address , order_name , mobile , A.userid "
					   + "       , B.product_num, buy_cnt , buy_date, shipping, fk_address_num "
					   + "       , product_image, product_name, price, discount_rate, post_code "
					   + " FROM tbl_address A "
					   + " join tbl_buylist B "
					   + " ON A.address_num = b.fk_address_num "
					   + " JOIN tbl_product P "
					   + " ON B.product_num = P.product_num "
					   + " where order_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				purchaseListDTO = new PurchaseListDTO();
				
				purchaseListDTO.setBuy_cnt(rs.getInt("buy_cnt"));      // 구매수량 
				purchaseListDTO.setShipping(rs.getInt("shipping"));    // 배송진행 상황
				purchaseListDTO.setBuy_date(rs.getString("buy_date")); // 거래일시 
				
				ProductDTO prodDTO = new ProductDTO();
				prodDTO.setProduct_num(rs.getInt("product_num"));  // 제품번호
				prodDTO.setProduct_name(rs.getString("product_name"));  // 제품이름
				prodDTO.setProduct_image(rs.getString("product_image"));// 제품이미지
				prodDTO.setDiscount_rate(rs.getFloat("discount_rate")); // 할인율
				prodDTO.setPrice(rs.getInt("price"));                   // 판매가
				purchaseListDTO.setProdDTO(prodDTO);
				
				AddressDTO addrDTO = new AddressDTO();
				addrDTO.setOrder_name(rs.getString("order_name"));         // 받는사람
				addrDTO.setMobile(rs.getString("mobile"));                 // 휴대폰번호
				addrDTO.setAddress(rs.getString("address"));  			   // 주소 
				addrDTO.setDetail_address(rs.getString("detail_address")); // 상세주소 
				addrDTO.setPost_code(rs.getString("post_code"));      // 우편번호 
				
				purchaseListDTO.setAddressDTO(addrDTO);
			}

		} finally {
			close();
		}
		
		return purchaseListDTO;
		
	}// end of public PurchaseListDTO getDetailPurchaseList(Map<String, String> purchaseMap) throws SQLException {}-------------------------

	
	// 배송 준비중, 배송중, 배송완료 각각의 개수 알아오기 
	@Override
	public Map<String,String> admin_getOrderCnt() throws SQLException {
		
		Map<String,String> OrderCntMap = new HashMap<>();
		String shipping = "";
		
		try {
			conn = ds.getConnection();
			
			String sql = " select shipping, count(shipping) as cnt "
					   + " from tbl_buylist "
					   + " group by shipping "
					   + " order by shipping  ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				switch (rs.getString("shipping")) {
					case "0":
						shipping = "shipping_ready";
						break;
					case "1":
						shipping = "shipping";
						break;
					case "2":
						shipping = "shipping_complete";
						break;
				}
				
				OrderCntMap.put(shipping,rs.getString("cnt"));
			}
			
		} finally {
			close();
		}
		
		return OrderCntMap;
		
	}// end of public List<Map<String, Integer>> admin_getOrderCnt() {}-----------------------

	
	/** 배송상태를 전달받아 해당 배송상태의 모든 구매내역을 조회하는 메소드 (admin) */
	@Override
	public List<PurchaseListDTO> getAllPurchaseList(String input_shipping)throws SQLException {
		
		List<PurchaseListDTO> purchaseList = new ArrayList<>();
		PurchaseListDTO purchaseDTO = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT B.order_num , B.userid, B.product_num, B.buy_cnt, B.buy_date, B.shipping, "
					   + "       P.product_num , P.product_name, P.product_image, P.price, P.discount_rate "
					   + " FROM tbl_buylist B LEFT JOIN tbl_product P "
					   + " ON B.product_num = P.product_num "
					   + " where shipping = ? "
					   + " order by order_num desc ";
					   
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_shipping);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				purchaseDTO = new PurchaseListDTO();
				
				purchaseDTO.setOrder_num(rs.getInt("order_num"));
				purchaseDTO.setUserid(rs.getString("userid"));
				purchaseDTO.setProduct_num(rs.getInt("product_num"));
				purchaseDTO.setBuy_cnt(rs.getInt("buy_cnt"));
				purchaseDTO.setBuy_date(rs.getString("buy_date"));
				purchaseDTO.setShipping(rs.getInt("shipping"));
				
				ProductDTO prodDTO = new ProductDTO();
				prodDTO.setProduct_name(rs.getString("product_name"));
				prodDTO.setProduct_image(rs.getString("product_image"));
				prodDTO.setPrice(rs.getInt("price"));
				prodDTO.setDiscount_rate(rs.getFloat("discount_rate"));
				
				purchaseDTO.setProdDTO(prodDTO);
				purchaseList.add(purchaseDTO);
			}
			

		} finally {
			close();
		}
		return purchaseList;
	} // end of public List<PurchaseListDTO> getAllPurchaseList(String input_shipping)throws SQLException {}-------------------------------
	
	// 출력해야 할 구매내역 수 조회하기 (관리자)
		@Override
		public int getTotalListAdminCnt(String shipping) throws SQLException {
			
			int cnt = 0;
			
			try {
				conn = ds.getConnection();
				
				String sql = "select count(*) "
						   + "from tbl_buylist "
						   + "where shipping = ? ";
						   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shipping);
				
				rs = pstmt.executeQuery();
				rs.next();
				cnt = rs.getInt(1);

			} finally {
				close();
			}
			
			return cnt;
			
		}// public int getTotalListCnt(Map<String, String> purchaseMap) throws SQLException {}----------------------------------

		
		
		// 주문번호와 배송상태를 전달받아 배송처리 하는 메소드  
		@Override
		public int updateShipping(String shipping, String[] orderNumList) throws SQLException {
			
			int result = 0;
			
			try {
				for(int i = 0; i<orderNumList.length; i++) {
					conn = ds.getConnection();
					
					String sql = " update tbl_buylist set shipping = ?  "
							   + " where order_num = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, String.valueOf((Integer.parseInt(shipping)+1)) );
					pstmt.setString(2, orderNumList[i]);
					result = pstmt.executeUpdate();
				}
				
			} finally {
				close();
			}
			
			return result;
			
		}// public int updateShipping(String shipping, String[] orderNumList) throws SQLException {}-----------------

		
		// 주문번호를 전달받아 주문자의 아이디를 알아오는 메소드 
		@Override
		public String getOrderUserid(String order_num) throws SQLException {
			String orderUserid = "";
			try {
				conn = ds.getConnection();
				
				String sql = " select userid "
						   + " from tbl_buylist "
						   + " where order_num = ? ";
						   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, order_num);
				
				rs = pstmt.executeQuery();
				rs.next();
				orderUserid = rs.getString(1);

			} finally {
				close();
			}
			return orderUserid;
			
		}// end of public String getOrderUserid(String order_num) throws SQLException {}---------------------------

	
	
}
