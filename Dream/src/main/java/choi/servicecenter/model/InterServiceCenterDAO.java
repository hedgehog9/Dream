package choi.servicecenter.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InterServiceCenterDAO {
	
	// 페이지 번호에 알맞는 공지사항을 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
	List<NoticeDTO> selectAllNotice(Map<String,String> paraMap) throws SQLException;
	
	// 공지사항번호에 알맞는 공지사항 하나를 가져오는 메소드
	NoticeDTO select_one_notice(int notice_num) throws SQLException;

	// 모든 공지사항 갯수를 가져오는 메소드
	int cntAllNotice() throws SQLException;

	
	
	// 모든 qna 갯수를 가져오는 메소드
	int cntAllqna(String title) throws SQLException;
	
	// 하나의 Qna를 가져오는 메소드
	QnaDTO select_one_qna(int faq_num) throws SQLException;
	
	// 페이지 번호에 알맞는 qna를 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
	List<QnaDTO> selectAllqna(Map<String, String> paraMap) throws SQLException;

	
	//공지사항 Insert 해주는 메소드
	int notice_insert(Map<String, String> paraMap) throws SQLException;
	
	
	//공지사항 Update 해주는 메소드
	int notice_Update(Map<String, String> paraMap) throws SQLException;

	//공지사항 Delete 해주는 메소드
	int notice_delete(int notice_num) throws SQLException;
	
	
	//자주묻는 질문 Insert 해주는 메소드
	int faq_insert(Map<String, String> paraMap) throws SQLException;

	
	//자주묻는질문 Update 해주는 메소드
	int faq_Update(Map<String, String> paraMap) throws SQLException;
	
	
	//자주묻는질문 Delete 해주는 메소드
	int faq_delete(int faq_num) throws SQLException;

	
	


}
