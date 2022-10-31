package jjy.purchase.model;

import java.sql.SQLException;
import java.util.*;

public interface InterPurchaseListDAO {

	/** userid 를 입력받아 모든 구매내역을 List 타입으로 반환하는 메소드 */
	List<PurchaseListDTO> allPurchaseList(Map<String, String> purchaseMap) throws SQLException;

	/** 사용자 아이디, 시작일, 종료일, 배송상태를 Map으로 전달받아 출력해야 할 페이지 수를 구해오는 메소드 (select) */
	int getTotalPage(Map<String, String> purchaseMap) throws SQLException;

	/** 페이징처리를 위한 구매내역 조회하기 */
	List<PurchaseListDTO> selectPagingPurchaseList(Map<String, String> purchaseMap)throws SQLException ;

	/** 출력해야 할 구매내역 수 조회하기 */
	int getTotalListCnt(Map<String, String> purchaseMap) throws SQLException ;

	/** 사용자 아이디를 입력받아 배송상태가 "진행중" , "종료" 의 개수 알아오는 메소드 */
	Map<String, String> getOrderCnt(String loginedUserid) throws SQLException ;

	/** 사용자 아이디, 주문번호를 Map 으로 전달받아 해당 주문번호에 해당하는 주문내역을 구해오는 메소드 */
	PurchaseListDTO getDetailPurchaseList(String order_num) throws SQLException ;

	/** 배송 준비중, 배송중, 배송완료 각각의 개수 알아오기 (admin)*/
	Map<String,String> admin_getOrderCnt() throws SQLException ;
	
	/** 배송상태를 전달받아 해당 배송상태의 모든 구매내역을 조회하는 메소드 (admin) */
	List<PurchaseListDTO> getAllPurchaseList(String input_shipping)throws SQLException ;

	/** 페이징 처리를 위한 배송 준비중, 배송중, 배송완료 수를 알아오는 메소드 (amdin) */
	int getTotalListAdminCnt(String shipping) throws SQLException;

	/** 주문번호와 배송상태를 전달받아 배송처리 하는 메소드  */
	int updateShipping(String shipping, String[] orderNumList) throws SQLException;

	/** 주문번호를 전달받아 주문자의 아이디를 알아오는 메소드 */
	String getOrderUserid(String order_num) throws SQLException;

	
}
