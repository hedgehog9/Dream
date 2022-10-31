package hgb.address.model;

import java.sql.SQLException;
import java.util.*;





public interface InterAddressDAO {
	
	
	
	
	
	
	 // 기본배송지를 셀렉트 해오는 메소드 
	   AddressDTO select_basic_address(String userid) throws SQLException;
	 
	
	
	// 주소록을 등록해주는 메소드
	int registerAddress(AddressDTO address) throws SQLException;


	// 기본배송지가 아닌 주소록 목록을 불러오기
	List<AddressDTO> selectAddress(Map<String,String> paraMap) throws SQLException;


    // 주소록 삭제하기
	int deleteAddress(AddressDTO address2) throws SQLException;


    // 주소록 수정하기
	int editAddress(AddressDTO address3) throws SQLException;


    // 기본배송지로 변경해주기
	int gobasicAddress(AddressDTO address4) throws SQLException;


    // 기본배송지를 제외한 총 배송지 갯수를 알아오는 메소드
	int cntAllAddress(String userid) throws SQLException;


    // 기본배송지가 있는지 없는지 여부를 검사해주는 메소드
	boolean check_basic(String userid) throws SQLException;


    // 기본배송지가 없다면 기본배송지로 배송지 추가를 해주는 메소드
	int registerBasicAddress(AddressDTO address1) throws SQLException;
	
	

	


	
	 
}
