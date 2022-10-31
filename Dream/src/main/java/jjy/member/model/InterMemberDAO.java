package jjy.member.model;

import java.sql.SQLException;
import java.util.*;

public interface InterMemberDAO {

	/** (update)로그인된 userid를 Map으로 전달받아 tbl_member 테이블에 멤버십 가입 여부, 멤버십 가입 날짜를 업데이트 하는 메소드 */
	int registMembership(Map<String, String> useridMap)throws SQLException;

	/** (update)로그인된 userid를 Map으로 전달받아 tbl_member 테이블에 멤버십 가입 취소하는 메소드  */
	int deleteMembership(String userid)throws SQLException;

	/** (select)로그인된 String 타입 userid 를 전달받아 tbl_member_login 테이블에서 가입일자,멤버십 가입여부,포인트,핸드폰번호,멤버십 가입일자를 가져오는 메소드  */
	MemberDTO selectOneUser(String userid)throws SQLException;

	
}
