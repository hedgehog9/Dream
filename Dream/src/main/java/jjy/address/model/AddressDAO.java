package jjy.address.model;

import java.io.UnsupportedEncodingException;
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


import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;


public class AddressDAO implements InterAddressDAO {
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

	// 주소록을 등록해주는 메소드
	@Override
	public int registerAddress(AddressDTO address1) throws SQLException {

		int result = 0;

		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_address (address_num,userid,basic_address,address,detail_address,post_code,order_name,mobile) "
					   + " values (seq_address_num.nextval,'josh@gmail.com','0',?,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address1.getAddress());
			pstmt.setString(2, address1.getDetail_address());
			pstmt.setString(3, address1.getPost_code());
			pstmt.setString(4, address1.getOrder_name());
			pstmt.setString(5, address1.getMobile()); // 암호를 SHA256 알고리즘으르 단방향 암호화 시킨다.

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}// end of public int registerAddress(AddressDTO address) throws SQLException --------------------------
	
	
	// 기본배송지가 없다면 기본배송지로 배송지 추가를 해주는 메소드
	@Override
	public int registerBasicAddress(AddressDTO address1) throws SQLException {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_address (address_num,userid,basic_address,address,detail_address,post_code,order_name,mobile) "
					   + " values (seq_address_num.nextval,'josh@gmail.com','1',?,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address1.getAddress());
			pstmt.setString(2, address1.getDetail_address());
			pstmt.setString(3, address1.getPost_code());
			pstmt.setString(4, address1.getOrder_name());
			pstmt.setString(5, address1.getMobile()); // 암호를 SHA256 알고리즘으르 단방향 암호화 시킨다.

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;

	}//end of public int registerBasicAddress(AddressDTO address1) throws SQLException-----

	
				
				
				

	
	// 기본배송지를 셀렉트 해오는 메소드
	@Override
	public AddressDTO select_basic_address(String userid) throws SQLException {

		AddressDTO adto = null;

		try {
			conn = ds.getConnection();

			String sql = " select * from tbl_address " 
					   + " where userid = ? and basic_address = 1 ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				adto = new AddressDTO();

				adto.setAddress_num(rs.getString("ADDRESS_NUM"));
				adto.setAddress(rs.getString("ADDRESS"));
				adto.setDetail_address(rs.getString("DETAIL_ADDRESS"));
				adto.setMobile(rs.getString("MOBILE"));
				adto.setOrder_name(rs.getString("ORDER_NAME"));
				adto.setPost_code(rs.getString("POST_CODE"));
			} // end of if

		} finally {
			close();
		}
		return adto;

	}
	  
	  
    // 기본배송지가 아닌 주소록 목록을 불러오기
	@Override
	public List<AddressDTO> selectAddress(Map<String, String> paraMap) throws SQLException {

		List<AddressDTO> addressList = new ArrayList<>();
		int page = Integer.parseInt(paraMap.get("page"));
		int display_cnt = Integer.parseInt(paraMap.get("display_cnt"));
		String userid = paraMap.get("userid");

		try {

			conn = ds.getConnection();

		  String sql = " select address_num, "
		  		     + "       userid, "
		  		     + "       basic_address, "
		  		     + "       address, "
		  		     + "       detail_address, "
		  		     + "       post_code, "
		  		     + "       order_name, "
		  		     + "       mobile "
		  		     + " from "
		  		     + " ( "
		  		     + " select rownum R, "
		  		     + "       address_num, "
		  		     + "       userid, "
		  		     + "       basic_address, "
		  		     + "       address, "
		  		     + "       detail_address, "
		  		     + "       post_code, "
		  		     + "       order_name, "
		  		     + "       mobile "
		  		     + " from tbl_address "
		  		     + " where userid = ? and basic_address = '0' order by address_num "
		  		     + " ) V"
		  		     + " where R between (? * ?) - (? - 1) and (? * ?) ";
//				  (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
		  
		  pstmt = conn.prepareStatement(sql); 
		  pstmt.setString(1,userid);
		  pstmt.setInt(2,page);
		  pstmt.setInt(3,display_cnt);
		  pstmt.setInt(4,display_cnt);
		  pstmt.setInt(5,page);
		  pstmt.setInt(6,display_cnt);
		  
		  rs = pstmt.executeQuery();

			while (rs.next()) {

				AddressDTO adto = new AddressDTO();

				adto.setAddress_num(rs.getString("ADDRESS_NUM"));
				adto.setAddress(rs.getString("ADDRESS"));
				adto.setDetail_address(rs.getString("DETAIL_ADDRESS"));
				adto.setMobile(rs.getString("MOBILE"));
				adto.setOrder_name(rs.getString("ORDER_NAME"));
				adto.setPost_code(rs.getString("POST_CODE"));

				addressList.add(adto);

			} // end of if

		} finally {
			close();
		}

		return addressList;
	}
	
	// 주소록 삭제하기
	@Override
	public int deleteAddress(AddressDTO address2) throws SQLException {

		int result = 0;

		try {
			conn = ds.getConnection();

			String sql = " delete from tbl_address " + " where address_num = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address2.getAddress_num());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}// end of public int deleteAddress(AddressDTO address2) throws SQLException {}---------------------------------
	
	
	
	
	// 주소록 수정하기
	@Override
	public int editAddress(AddressDTO address3) throws SQLException {

		int result = 0;

		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_address "
					   + " set order_name = ?, post_code = ?, detail_address = ?, address = ?, mobile = ? "
					   + " where address_num = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address3.getOrder_name());
			pstmt.setString(2, address3.getPost_code());
			pstmt.setString(3, address3.getDetail_address());
			pstmt.setString(4, address3.getAddress());
			pstmt.setString(5, address3.getMobile());
			pstmt.setString(6, address3.getAddress_num());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}
	

	
	// 기본배송지로 변경해주기
	@Override
	public int gobasicAddress(AddressDTO address4) throws SQLException {

		int result = 0;

		try {
			conn = ds.getConnection();

			String sql = " update tbl_address " 
			           + " set basic_address = '0' " 
					   + " where basic_address = '1' ";

			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();

			sql = " update tbl_address " 
			    + " set basic_address = '1' " 
				+ " where address_num = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, address4.getAddress_num());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}
	
	
	
	// 기본배송지를 제외한 총 배송지 갯수를 알아오는 메소드
	@Override
	public int cntAllAddress() throws SQLException {
		int total_cnt = 0;
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) "
					    + " from tbl_address "
					    + " where basic_address=0 ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
		
	}//end of public int cntAllAddress() throws SQLException---------

	
	// 기본배송지가 있는지 없는지 여부를 검사해주는 메소드
	@Override
	public boolean check_basic(String userid) throws SQLException {
		boolean check_basic_address = false;
		
		try {
			conn = ds.getConnection();
			
			String sql =  " select * from tbl_address"
					    + " where userid= ? and basic_address=1 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid); 
			rs = pstmt.executeQuery();
			
			check_basic_address = rs.next();	//읽어온 데이터가 있으면 rs.next()는 true, 없으면 false
			
		} finally {
			close();
		}
		return check_basic_address;
		
	}//end of public boolean check_basic(String userid) throws SQLException {}------------------------------------------


	
	/** 주문자 아이디, 주문일련번호를 Map으로 전달받아 아이디, 받는사람명,전화번호,주소,상세주소,우편번호를 조회하는 메소드 */
	@Override
	public AddressDTO getOrderInfo(Map<String, String> paraMap) throws SQLException {

		AddressDTO adto = null;
		
		try {
				
			conn = ds.getConnection();
			
			String sql = " SELECT address_num, A.userid, address,detail_address, post_code, order_name, mobile "
					   + " FROM tbl_address A JOIN tbl_buylist B "
					   + " ON A.address_num  = B.fk_address_num "
					   + " WHERE A.userid = ? AND order_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("odruserid"));
			pstmt.setString(2, paraMap.get("ordernum"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				adto = new AddressDTO();
				
				adto.setUserid(rs.getString("userid"));
				adto.setAddress(rs.getString("address"));
				adto.setDetail_address(rs.getString("detail_address"));
				adto.setPost_code(rs.getString("post_code"));
				adto.setOrder_name(rs.getString("order_name"));
				adto.setMobile(rs.getString("mobile"));
			}
			
		} finally {
			close();
		}
		
		return adto;
		
	}// end of public AddressDTO getOrderInfo(Map<String, String> paraMap) throws SQLException {}--------------
	
	

			
}
