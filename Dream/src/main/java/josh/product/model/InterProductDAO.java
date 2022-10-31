package josh.product.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InterProductDAO {

	// 할인상품의 전체개수를 얻어오는 함수
	int totalDiscount() throws SQLException;

	// index.dream 진입시 할인상품 보여주는 메소드
	List<ProductDTO> selectDiscountprd(Map<String, String> paraMap) throws SQLException;

	// cart 리스트에 상품 구매후 테이블 update 및 insert
	int cartPurchaseUpdate(Map<String, String> paraMap) throws SQLException;

	// cart 에 수량을 변경하는 메소드
	int updateCart(String cart_num, String cart_qty) throws SQLException;

	
	
}
