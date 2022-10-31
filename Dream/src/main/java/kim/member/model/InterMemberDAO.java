package kim.member.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InterMemberDAO {
	/** 카트 추가 */
	int updateCart(Map<String, String> paraMap) throws SQLException;
	/** 좋아요 수 파악후 내가 안눌렀다면 추가, 눌렀다면 삭제 */
	Map<String, String> likeCheck(Map<String, String> paraMap) throws SQLException;
    /** userid 로 포인트 가져오기*/
	MemberVO pointCheck(String sessionUserid) throws SQLException;
	
	/** userid 로 그 유저의 모든 포인트 내역 가져오기 */
	List<PointVO> allPointList(String userid) throws SQLException;
	
	/** Total point 저장하기 */
	int getTotalPoint(String userid) throws SQLException;
	
	/** 답장하기 눌러서 나에게 보낸 user 를 대상으로 내가 쓴 메시지 insert하기 */
	int reply(Map<String, String> paraMap) throws SQLException;
	
	/** 메시지 번호로 삭제하기  */
	int deletemessage(String messageno, String whoami) throws SQLException;
	
	/** messageno 넣으면 읽음 처리하기 */
	int readmessage(String messageno) throws SQLException;

}
