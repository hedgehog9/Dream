package jjy.product.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InterProductDAO {
	//필터에 맞는 총 게시물 수를 알아오는 메소드
	int cntAllProduct(Map<String,String> paraMap) throws SQLException;
	
	
	//페이징처리를해서, 제품들을 셀렉트해오는 메소드
	List<ProductDTO> selectAllProduct(Map<String, String> paraMap) throws SQLException;

	/** Map 으로 상품명,상품이미지,대카테고리,소카테고리,가격,할인율,성별,제품상세설명을 전달받아 제품 등록하는 메소드 (insert) */
	int registProduct(Map<String, String> productMap) throws SQLException;

	/** 대카테고리 침구류,수면용품,파자마,조명 구해오기 */
	List<String> getCagegory() throws SQLException;

	/** 대카테고리를 전달받아 그에 맞는 소카테고리 구해오는 메소드 */
	// List<String> getDetailCategory(String category) throws SQLException;

	/** 다음번에 사용될 제품일련번호 채번해오는 메소드 */
	public int getPnumOfProduct() throws SQLException;

	/** Map 으로 상품정보를 전달받아 tbl_product_stock 테이블에 insert 하는 메소드 
	int registPqty(Map<String, String> productMap)throws SQLException; */
	
	
}
