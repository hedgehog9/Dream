package choi.product.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InterProductDAO {
	//필터에 맞는 총 게시물 수를 알아오는 메소드
	int cntAllProduct(Map<String,String> paraMap) throws SQLException;
	
	
	//페이징처리를해서, 제품들을 셀렉트해오는 메소드
	List<ProductDTO> selectAllProduct(Map<String, String> paraMap) throws SQLException;

	// 키워드를 입력받아서, 키워드로 검색한 총게시물의 수를 알아오는 메소드
	int cntkeywordProduct(String keyword) throws SQLException;
	
	
	
	// 키워드,페이지번호,한페이지당 보여줄 게시물수,유저아이디를 맵으로 입력받아서, 키워드로 검색한 게시물의 리스트들을 받아오는 메소드
	List<ProductDTO> selectKeywordProduct(Map<String, String> paraMap) throws SQLException;

	// 키워드로 검색한 결과가 1건이라도 있으면 키워드,클라이언트 아이피를 입력받아서 검색어 테이블에 insert 하는 메소드
	int insertKeyword(Map<String, String> keywordMap) throws SQLException;

	// 최근시간 00분 00초부터 한시간전까지의 인기검색어 10개를 불러오는 메소드
	List<String> selectBestKeyword() throws SQLException;

	// 현재시간을 알아오는 메소드
	String selectCurrentHour() throws SQLException;

	// 추천검색어 리스트를 알아오는 메소드
	List<String> selectRecommendKeyword() throws SQLException;

	// 키워드를 파라미터로 받아서 추천검색어를 Insert 하는 메소드
	int insertRecommendKeyword(String keyword) throws SQLException;

	// 키워드를 파라미터로 받아서 추천검색어를 Delete 하는 메소드
	int DeleteRecommendKeyword(String keyword) throws SQLException;

	// 유저아이디를 입력받아서 좋아요한 상품들 갯수를 알아오는 메소드
	int cntLikeAllProduct(String userid) throws SQLException;

	// 유저아이디를 입력받아서 좋아요한 상품들 리스트를 알아오는 메소드
	List<ProductDTO> selectLikeAllProduct(Map<String, String> paraMap) throws SQLException;

	// 유저아이디를 입력받아서 회원맞춤 검색어 리스트를 알아오는 메소드
	List<String> selectpersonalizedKeyword(String userid) throws SQLException;


}
