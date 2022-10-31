package jjy.login.model;

import java.sql.SQLException;
import java.util.*;

import jjy.member.model.MemberDTO;

public interface InterLoginDAO {

	/** 아이디(userid), 비밀번호(passwd) 를 Map 에 저장해 일치하는 사용자가 있는지 조회하는 메소드(select)  */
	LoginDTO selectOneUser(Map<String, String> userinfoMap) throws SQLException;

	/** 연락처(mobile), 이메일(email)을 Map에 담아 일치하는 사용자가 있으면 true, 없으면 false 를 반환하는 메소드 (select) */
	boolean checkMobileEmail(Map<String, String> findPwdMap)throws SQLException;

	/** 아이디(userid) 를 전달받아 일치하는 회원이 있으면 회원의 이메일, 연락처를 반환하는 메소드 없으면 null 반환 (select) */
	MemberDTO userExist(String email)throws SQLException;

	/** 아이디를 입력받아 가장 마지막에 로그인한 날짜를 구해오는 메소드(select) */
	String checkLastLoginDate(String userid)throws SQLException;

	/** 아이디를 입력받아 로그인 기록 테이블에 기록하는 메소드(insert) */
	int updateLoginDate(Map<String, String> userinfoMap)throws SQLException;

	/** 마지막 로그인 날짜로부터 1년 이상 지난경우 휴면유저로 바꿔주는 메소드*/
	void updateRestMember(String userid)throws SQLException;

	/** 아이디(userid) 를 전달받아 회원가입 날짜를 String 타입으로 반환, 결과가 없을경우 null을 반환하는 메소드 (select)*/
	String checkRegistDate(String userid)throws SQLException;

	/** 아이디, 핸드폰 번호, 임시 비밀번호를 Map 을 전달받아 비밀번호를 변경하는 메소드 (update) */
	int updatePassword(HashMap<String, String> findPwdMap) throws SQLException;

	/** 관리자 아이디, 비밀번호를 map 으로 전달받아 일치하는 관리자가 있는지 조회하는 메소드  */
	boolean selectAdmin(Map<String, String> userinfoMap) throws SQLException;

	/** 사용자 아이디를 입력받아 로그인 기록이 있는지 조회하는 메소드 (있을경우 return true 없을경우 return false) */
	boolean checkFirstLogin(String userid) throws SQLException;


}
