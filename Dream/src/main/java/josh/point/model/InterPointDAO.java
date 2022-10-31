package josh.point.model;

import java.sql.SQLException;
import java.util.Map;

public interface InterPointDAO {

	// 장바구니에서 상품구매시 포인트 적립 및 차감 
	int insertPoint(Map<String, String> paraMap) throws SQLException;


}
