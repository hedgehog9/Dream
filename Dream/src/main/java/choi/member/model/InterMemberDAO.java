package choi.member.model;

import java.sql.SQLException;
import java.util.Map;

public interface InterMemberDAO {
	
	
	// ID 중복검사 (tbl_member 테이블에서 userid 가 존재하면 true를 리턴해주고, userid 가 존재하지 않으면 false를 리턴한다)
	boolean idDuplicateCheck(String userid) throws SQLException;

	
	// 맵에 회원 아이디와 비밀번호를 받아와서, 회원테이블과 회원로그인테이블에 insert를 해주는 메서드
	int memberJoin(Map<String, String> paraMap) throws SQLException;


	// 회원 아이피를 파라미터로 받아서, 로그인기록테이블에서 유저아이디를 검색해오는 메소드
	String select_ip(String ipAddress) throws SQLException;

	
	// 회원 아이피를 파라미터로 받아서, 회원테이블에서 유저이름를 검색해오는 메소드
	String select_username(String userid) throws SQLException;

	// mobile 중복검사 (tbl_member 테이블에서 mobile이 존재하면 true를 리턴해주고, mobile 가 존재하지 않으면 false를 리턴한다)
	boolean mobileDuplicateCheck(String mobile) throws SQLException;
	
}



