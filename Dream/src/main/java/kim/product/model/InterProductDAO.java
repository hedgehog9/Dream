package kim.product.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import kim.member.controller.MessageVO;
import kim.member.model.MemberVO;

public interface InterProductDAO {
	/** 제품상세 페이지에 들어가는 정보들 불러오기용 */ 
	ProductVO getDetail(String product_num) throws SQLException;
	
	/** 제품 번호 넢으면 재고수량 배열로 가져옴 */ 
	Map<String, ArrayList<String>> cnt_check(String productNum) throws SQLException;
	
	/** 카트 거치지 않고 바로구매시 가는 페이지에서 하는 결제 */
	int nocartPurchaseUpdate(Map<String, String> paraMap) throws SQLException;
	
	/** 제품 업데이트 */
	int UpdateProduct(Map<String, String> paraMap) throws SQLException;

	/** uesrid 랑 제품번호로 내가 이미 관심등록 했는지 여부 확인!*/
	int ilikethis(String productnum, String loginuserid) throws SQLException;

	/** userid 넣으면 보낸메일 목록, 받은메일 목록List 를 ArrayList 에 담아옴 */
	Map<String, Object> getAllMessage(String loginuserid, String type) throws SQLException;

	int deleeteproduct(String productNum)throws SQLException;

}
