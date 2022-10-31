package josh.purchase.model;

import java.sql.SQLException;
import java.util.*;

public interface InterPurchaseListDAO {

	// userid 를 입력받아 모든 구매내역을 List 타입으로 반환하는 메소드
	List<PurchaseListDTO> allPurchaseList(String userid) throws SQLException;


	
}
