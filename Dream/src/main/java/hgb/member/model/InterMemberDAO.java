package hgb.member.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import hgb.member.model.MemberDTO;
import jjy.purchase.model.PurchaseListDTO;
import kim.member.model.PointVO;


public interface InterMemberDAO {


	// 회원한명의 상세정보를 보여주는 메소드
	MemberDTO memberOneDetail(String userid) throws SQLException;

	
	// 회원한명의 구매내역을 보여주는 메소드
	ArrayList<PurchaseListDTO> memberOneBuyDetail(String userid) throws SQLException;


	/** 회원한명의 포인트내역을 보여주는 메소드 */
	ArrayList<PointVO> memberOnePointDetail(String userid) throws SQLException;

	/** 회원한명의 정보를 수정해주는 메소드 */
	int editmember(Map<String, String> paraMap) throws SQLException;

	/** 회원목록에서 회원을 삭제시키기 */
	int delMember(String userid) throws SQLException;


	
	
	
}
