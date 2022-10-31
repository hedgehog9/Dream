package choi.product.model;

import java.sql.SQLException;
import java.util.Map;

public interface InterLikeDAO {
	
	//로그인중인 회원의 아이디와 제품번호를받아서 좋아요를 눌렀는지 체크하는 메소드
	boolean likeCheck(Map<String, String> paraMap) throws SQLException;

	//로그인중인 회원의 아이디를 파라미터로 받아서, 좋아요 테이블에 delete 해주는 메소드
	int likeDelete(Map<String, String> paraMap) throws SQLException;
	
	//로그인중인 회원의 아이디를 파라미터로 받아서, 좋아요 테이블에 insert 해주는 메소드
	int likeinsert(Map<String, String> paraMap) throws SQLException;

}
