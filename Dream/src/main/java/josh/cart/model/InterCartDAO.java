package josh.cart.model;

import java.sql.SQLException;
import java.util.List;

public interface InterCartDAO {

	// 장바구니에 담긴 List들을 가져오는 메소드
	List<CartDTO> selectCart(String userid) throws SQLException;

	// 체크 선택한 상품들만 장바구니에서 삭제하는 메소드
	int cartSelectDelete(String cart_num) throws SQLException;

}
