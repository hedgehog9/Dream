package choi.servicecenter.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ServiceCenterDAO implements InterServiceCenterDAO{
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	// 생성자 
	public ServiceCenterDAO() {
		
		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("/jdbc/dream");
		} catch(NamingException e) {
			e.printStackTrace();
		}//end of try-catch
	}//end of public ServiceCenterDAO()
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		
		try {
			if(rs != null)    { rs.close();    rs = null; }
			if(pstmt != null) { pstmt.close(); pstmt = null; }
			if(conn != null)  { conn.close();  conn = null; }
		}catch(SQLException e){
			e.printStackTrace();
		}// end of try ~ catch()---------------------------------
		
	}//end of private void close()
			
			
			
			
	// 페이지 번호에 알맞는 게시물을 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
	@Override
	public List<NoticeDTO> selectAllNotice(Map<String,String> paraMap) throws SQLException{
		List<NoticeDTO> noticeList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql = " select notice_num "
					   + " ,notice_title "
					   + " ,to_char(write_date,'yy-mm-dd hh:mi') write_date "
					   + " from  "
					   + " ( "
					   + " select rownum R,notice_num,notice_title,write_date "
					   + " from tbl_notice "
					   + " order by notice_num desc "
					   + " )A  "
					   + " where R between (?*?)-(?-1) and (?*?) ";
//			(조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(2,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(3,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(4,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(5,Integer.parseInt(paraMap.get("display_cnt")));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDTO ndto = new NoticeDTO();	//dto 하나 생성
				ndto.setNotice_num(rs.getInt("NOTICE_NUM"));
				ndto.setNotice_title(rs.getString("NOTICE_TITLE"));;
				
				noticeList.add(ndto);	//NoticeDTO들만 들어갈 수 있는 리스트에 담기
			}
		} finally {
			close();
		}
		return noticeList;
	}//end of public List<NoticeDTO> selectAllNotice() {}----
	
	
	//글번호를 입력받아서 한개의 글을 select 하는 메소드
	@Override
	public NoticeDTO select_one_notice(int notice_num) throws SQLException{
		NoticeDTO ndto = null;
		try {
			conn = ds.getConnection();
			
			String sql =  " select notice_title "
						+ "      , notice_content "
						+ "      , to_char(write_date,'yy-mm-dd') write_date "
						+ " from tbl_notice "
						+ " where notice_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,notice_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ndto = new NoticeDTO();
				
				ndto.setNotice_title(rs.getString("notice_title"));
				ndto.setNotice_content(rs.getString("notice_content"));
				ndto.setWrite_date(rs.getString("write_date"));
			}
		} finally {
			close();
		}
		return ndto;
		
	}//end of public void select_one_notice(int notice_num)------

	
	//모든 공지사항 갯수를 가져오는 메소드
	@Override
	public int cntAllNotice() throws SQLException {
		int total_cnt = 0;
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) from tbl_notice ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
	}

	
	
	// 모든 qna 갯수를 가져오는 메소드
	@Override
	public int cntAllqna(String title) throws SQLException{
		int total_cnt = 0;
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) from tbl_faq ";
			
			if(!("전체".equals(title))) {	//title이 null이 아니고 전체이지 않을 경우
				sql += " where faq_title = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			if(!("전체".equals(title))) { //title이 null이아니고 전체이지 않을 경우
				pstmt.setString(1,title);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				total_cnt = rs.getInt(1);
			}
			
		} finally {
			close();
		}
		return total_cnt;
	}

	
	// 페이지 번호에 알맞는 qna를 한 페이지에 보여줄 게시물 수만큼가져오는 메소드
	@Override
	public List<QnaDTO> selectAllqna(Map<String, String> paraMap) throws SQLException {
		List<QnaDTO> qnaList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			
			String sql = " select FAQ_NUM "
					   + "       ,FAQ_TITLE "
					   + "       ,FAQ_CONTENT "
					   + "       ,FAQ_SUBJECT"
					   + " from "
					   + " ("
					   + " select FAQ_NUM "
					   + "       ,FAQ_TITLE "
					   + "       ,FAQ_CONTENT"
					   + "       ,FAQ_SUBJECT"
					   + " from  "
					   + " ( "
					   + " select rownum R,FAQ_NUM,FAQ_TITLE,FAQ_CONTENT,FAQ_SUBJECT"
					   + " from tbl_faq "
					   + " order by faq_num desc "
					   + " )V "
					   + " where R between (?*?)-(?-1) and (?*?) "
					   + " )A";
			if(paraMap.get("title")!= null && !("전체".equals(paraMap.get("title"))) ) {	//title이 null이 아니고 전체이지 않을 경우
				sql += " where faq_title = ? ";
			}
//			(조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(2,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(3,Integer.parseInt(paraMap.get("display_cnt")));
			pstmt.setInt(4,Integer.parseInt(paraMap.get("page")));
			pstmt.setInt(5,Integer.parseInt(paraMap.get("display_cnt")));
			if(paraMap.get("title")!= null && !("전체".equals(paraMap.get("title"))) ) { //title이 null이아니고 전체이지 않을 경우
				pstmt.setString(6,paraMap.get("title") );
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaDTO qdto = new QnaDTO();	//dto 하나 생성
				qdto.setFaq_num(rs.getInt("FAQ_NUM"));
				qdto.setFaq_title(rs.getString("FAQ_TITLE"));
				qdto.setFaq_subject(rs.getString("FAQ_SUBJECT"));
				qdto.setFaq_content(rs.getString("FAQ_CONTENT"));
				
				qnaList.add(qdto);	//NoticeDTO들만 들어갈 수 있는 리스트에 담기
			}
		} finally {
			close();
		}
		return qnaList;
	}
	
	
	//공지사항 insert 해주는 메소드
	@Override
	public int notice_insert(Map<String, String> paraMap) throws SQLException{
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_notice(notice_num,notice_title,notice_content,admin_id) "
					   + " values(seq_notice_num.nextval, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,paraMap.get("notice_title"));
			pstmt.setString(2,paraMap.get("notice_content"));
			pstmt.setString(3,paraMap.get("userid"));
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}

	
	//공지사항 update 해주는 메소드
	@Override
	public int notice_Update(Map<String, String> paraMap) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_notice "
					   + " set notice_title = ? , notice_content = ? "
					   + " where notice_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,paraMap.get("notice_title"));
			pstmt.setString(2,paraMap.get("notice_content"));
			pstmt.setString(3,paraMap.get("notice_num"));
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}
	
	
	//공지사항 delete 해주는 메소드
	@Override
	public int notice_delete(int notice_num) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " delete from tbl_notice where notice_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,notice_num);
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}

	
	
	
	
	//자주묻는 질문 Insert 해주는 메소드
	@Override
	public int faq_insert(Map<String, String> paraMap) throws SQLException{
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_faq(faq_num,faq_title,faq_subject,faq_content,admin_id) "
					   + " values(seq_faq_num.nextval, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,paraMap.get("faq_title"));
			pstmt.setString(2,paraMap.get("faq_subject"));
			pstmt.setString(3,paraMap.get("faq_content"));
			pstmt.setString(4,paraMap.get("userid"));
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}

	
	
	// 하나의 Qna를 가져오는 메소드
	@Override
	public QnaDTO select_one_qna(int faq_num) throws SQLException {
		QnaDTO qdto = null;
		try {
			conn = ds.getConnection();
			
			String sql =  " select FAQ_NUM,FAQ_TITLE,FAQ_CONTENT,FAQ_SUBJECT "
						+ " from tbl_faq "
						+ " where faq_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faq_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qdto = new QnaDTO();
				
				qdto.setFaq_subject(rs.getString("FAQ_SUBJECT"));
				qdto.setFaq_title(rs.getString("FAQ_TITLE"));
				qdto.setFaq_content(rs.getString("FAQ_CONTENT"));
			}
		} finally {
			close();
		}
		return qdto;
	}

	
	
	
	//자주묻는 질문 업데이트해주는 메소드
	@Override
	public int faq_Update(Map<String, String> paraMap) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_faq "
					   + " set faq_title = ? ,faq_subject = ?, faq_content = ? "
					   + " where faq_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,paraMap.get("faq_title"));
			pstmt.setString(2,paraMap.get("faq_subject"));
			pstmt.setString(3,paraMap.get("faq_content"));
			pstmt.setString(4,paraMap.get("faq_num"));
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int faq_delete(int faq_num) throws SQLException {
		int n = 0;
		try {
			conn = ds.getConnection();
			
			String sql = " delete from tbl_faq where faq_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,faq_num);
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
	}

}
