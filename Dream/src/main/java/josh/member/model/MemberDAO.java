package josh.member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import josh.purchase.model.PurchaseListDTO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO implements InterMemberDAO{
	// DBCP
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	
	
	// 생성자 
	public MemberDAO() {
		
		try {
			// connection pool 을 위한 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("/jdbc/dream");
			
			aes = new AES256(SecretMyKey.KEY);// KEY는 스태틱 변수이기때문에 객체생성 필요 x
			// SecretMyKey.KEY 는 우리가 만든 비밀키이다.
			
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) { // key 가 16글자 미만인경우 발생하는 예외 처리
			e.printStackTrace();
		}
	}
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		
		try {
			if(rs != null)    { rs.close();    rs = null; }
			if(pstmt != null) { pstmt.close(); pstmt = null; }
			if(conn != null)  { conn.close();  conn = null; }
		}catch(SQLException e){
			e.printStackTrace();
		}// end of try ~ catch()---------------------------------
		
	}

	@Override
	public boolean mobileDuplicateCheck(String mobile) throws SQLException{
		
		boolean isExists = false;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = " select mobile "
					   + " from tbl_member "
					   + " where mobile = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mobile);
			
			rs = pstmt.executeQuery();
			
			isExists = rs.next(); // 행이 있으면(중복된 email) true ,
			                      // 행이 없으면(사용가능한 email) false 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
			
		return isExists;
	}

	// 멤버한명의 정보들을 select 해오는 메소드
	@Override
	public MemberDTO selectOne(String userid) throws SQLException {
		
		MemberDTO mdto = new MemberDTO();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select A.userid, mobile, username, passwd, membership "
					    + " from "
					    + " ( "
						+ " select userid, mobile, username, membership "
						+ " from tbl_member "
						+ " )A "
						+ " join "
						+ " ( "
						+ " select userid, passwd "
						+ " from tbl_member_login "
						+ " )B "
						+ " on A.userid = B.userid "
						+ " where A.userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mdto.setUserid(rs.getString(1));
				mdto.setMobile(rs.getString(2));
				mdto.setUsername(rs.getString(3));
				mdto.setPasswd(rs.getString(4));
				mdto.setMembership(rs.getInt(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return mdto;
	}

	
	// 멤버 개인정보 수정하는 메소드
	@Override
	public int updateInfo(Map<String, String> paraMap) throws SQLException {
		int n = 0;
		
		//System.out.println("dao에 넘어옴");
		
		int passwd_store_cnt = Integer.parseInt(paraMap.get("passwd_store_cnt"));
		int mobile_store_cnt = Integer.parseInt(paraMap.get("mobile_store_cnt"));
		// System.out.println("passwd_store_cnt 확인용 >>> "+ passwd_store_cnt );
		//System.out.println("userid_store_cnt 확인용 >>> "+ userid_store_cnt );
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " update tbl_member set userid = ?, username = ?, mobile = ?"
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("username"));
			pstmt.setString(3, paraMap.get("mobile"));
			pstmt.setString(4, paraMap.get("userid"));
			//pstmt.setString(4, paraMap.get("sessionUserid"));
			
			n = pstmt.executeUpdate();
			
			
			if(passwd_store_cnt > 0) {
				sql = " update tbl_member_login set passwd = ? "
					+ " where userid = ? ";
					
				pstmt = conn.prepareStatement(sql);
					
				pstmt.setString(1, Sha256.encrypt(paraMap.get("passwd")));
				pstmt.setString(2, paraMap.get("userid"));
				
				n = pstmt.executeUpdate();
				//System.out.println("확인용!!!!!!! 두번째 꺼 ==> "+n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		return n;
	}

	// 마이페이지 진입할때 로그인한 회원의 정보
	@Override
	public HashMap<String, Integer> shippingCnt(String userid) throws SQLException {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select shipping, count(*) as count "
						+ " from tbl_member m join tbl_buylist b "
						+ " on m.userid = b.userid "
						+ " where m.userid = ? and shipping between '0' and '2' "
						+ " group by shipping "
						+ " order by shipping asc ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				if(rs.getInt(1) == 0) {
					map.put("shipping_ready", rs.getInt(2));
				}
				if(rs.getInt(1) == 1) {
					map.put("shipping_now", rs.getInt(2));
				}
				if(rs.getInt(1) == 2) {
					map.put("shipping_end", rs.getInt(2));
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}// end of public HashMap<String, Integer> shippingCnt(String userid) throws SQLException

	
	// Point 및 이메일 등등 가져오는 메소드
	@Override
   public MemberDTO pointCheck(String sessionUserid) throws SQLException {


		MemberDTO mvo = new MemberDTO();

      try {
            
            // 여기서부터 포인트전까지 조쉬거 훔쳐옴
            conn = ds.getConnection();
            
            String sql = " select A.userid, mobile, username, membership\n"
                      + " from\n"
                      + " (\n"
                     + " select userid, mobile, username, membership\n"
                     + " from tbl_member\n"
                     + " )A\n"
                     + " join\n"
                     + " (\n"
                     + " select userid, passwd\n"
                     + " from tbl_member_login\n"
                     + " )B\n"
                     + " on A.userid = B.userid\n"
                     + " where A.userid = ? ";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, sessionUserid);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               mvo.setUserid(rs.getString(1));
               mvo.setMobile(rs.getString(2));
               mvo.setUsername(rs.getString(3));
               mvo.setMembership(rs.getInt(4));
            }
            
            // 포인트
            
            conn = ds.getConnection();
            
            String sql2 = " SELECT SUM(CASE WHEN  status = '적립' THEN point_amount "+
                  "        WHEN  status = '차감' THEN -point_amount "+
                  "        else 0 END) "+
                  " FROM tbl_point "+
                  " where userid = ? ";
            
            pstmt = conn.prepareStatement(sql2);
            
            pstmt.setString(1, sessionUserid); // 세션 아이디값

            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               // 내역 없어도 sum 이라 한행이 나옴
               // 근데 내역이 하나도 없으면 null 나옴
               
               if( rs.getString(1) != null) {
                  // 내역이 존재한 경우에는 총합값을 저장
                  mvo.setPoint(rs.getInt(1));
               }
               else {
                  // 내역이 없는 경우에는 0 을 저장
                  mvo.setPoint(0);
               }
               
            }
         
         
      } catch (Exception e) {
    	  	  e.printStackTrace();
      } finally {
          close();
       }
      
      
      
      return mvo;
      
      
      
   }

	// 회원목록 관리자페이지에서 총 회원수 
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		int totalPage = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select ceil(count(*)/10) "
					   + " from tbl_member M JOIN tbl_member_login L "
					   + " on M.userid = L.userid "
					   + " where M.userid != 'admin' ";
			
			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			String start_date = paraMap.get("start_date");
			String end_date = paraMap.get("end_date");
			
			if( searchWord != null && !searchWord.trim().isEmpty() ) { // 검색어가 있는 경우
				 
				 if("secession".equals(colname) || "rest_member".equals(colname) ) { // 검색어가 있을 경우 회원탈퇴인 회원중에 검색어가 들어가는 회원만 조회
					 sql += " and M.userid like '%'|| ? ||'%' and L."+colname+" = 1";
				 }
				 else if("membership".equals(colname)) {
					 sql += " and M.userid like '%'|| ? ||'%' and M."+colname+" = 1";
				 }
				 else if("joindate".equals(colname)) {
					 sql += " and M.userid like '%'|| ? ||'%' and M."+colname+" between to_date('"+start_date+"','YYYY-MM-DD') and to_date('"+end_date+"','YYYY-MM-DD')";
				 }
				 else {
					 sql += " and M."+colname+" like '%'|| ? ||'%' ";
				 }
					 	
				 
			 }
			 
			 else { // 검색어가 없는 경우
				 if("secession".equals(colname) || "rest_member".equals(colname) ) { // 검색어가 있을 경우 회원탈퇴인 회원중에 검색어가 들어가는 회원만 조회
					 sql += " and L."+colname+" = 1";
				 }
				 else if("membership".equals(colname)) {
					 sql += " and M."+colname+" = 1";
				 }
				 else if("joindate".equals(colname)) {
					 sql += "and M."+colname+" between to_date('"+start_date+"','YYYY-MM-DD') and to_date('"+end_date+"','YYYY-MM-DD')";
				 }
			 }
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchWord != null && !searchWord.trim().isEmpty()) {
				pstmt.setString(1, searchWord);
			}
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			totalPage = rs.getInt(1);
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return totalPage;
	}

	
	// 회원목록 관리자 페이지에서 회원들의 리스트
	@Override
	public List<MemberDTO> selectAllMember(Map<String, String> paraMap) throws SQLException {
		List<MemberDTO> memberList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			
			 String sql = " select userid, to_char(joindate,'yyyy-mm-dd') AS joindate, membership, username, mobile, secession, rest_member "+
						  " from "+
						  "  ( "+
						  "      select rownum AS RNO, userid, joindate, membership, username, mobile, secession, rest_member "+
						  "      from "+
						  "      ( "+
						  "          select M.userid, joindate, membership, username, mobile, secession, rest_member  "+
						  "          from tbl_member M join tbl_member_login L "+
						  "          on M.userid = L.userid "+
						  "          where M.userid != 'admin' ";
						 
			 String colname = paraMap.get("searchType");
			 String searchWord = paraMap.get("searchWord");
			 String start_date = paraMap.get("start_date");
		     String end_date = paraMap.get("end_date");
			 
			 if( searchWord != null && !searchWord.trim().isEmpty() ) { // 검색어가 있는 경우
			 
				 if("secession".equals(colname) || "rest_member".equals(colname) || "membership".equals(colname)) { // 검색어가 있을 경우 회원탈퇴인 회원중에 검색어가 들어가는 회원만 조회
					 sql += " and M.userid like '%'|| ? ||'%' and "+colname+" = 1";
				 }
				 else {
					 if("userid".equals(colname)) {
						 sql += " and M."+colname+" like '%'|| ? ||'%' ";
					 }
					 else if("joindate".equals(colname)) {
						 sql += " and M.userid like '%'|| ? ||'%' and M."+colname+" between to_date('"+start_date+"','YYYY-MM-DD') and to_date('"+end_date+"','YYYY-MM-DD')";
					 }
					 else {
						 sql += " and "+colname+" like '%'|| ? ||'%' ";
					 }
					 	
				 }
			 }
			 
			 else { // 검색어가 없는 경우
				 if("secession".equals(colname) || "rest_member".equals(colname) || "membership".equals(colname)) {
					 sql += " and "+colname+"= 1 ";
				 }
				 else if("joindate".equals(colname)) {
					 sql += " and M."+colname+" between to_date('"+start_date+"','YYYY-MM-DD') and to_date('"+end_date+"','YYYY-MM-DD')";
				 }
				 
			 }
						 
			 sql +=  "          order by joindate desc "+
					 "      ) A "+
					 "  ) B "+
					 "  where RNO between ? and ? ";
			 // === 페이징처리의 공식 ===
			 // where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수); 
			
			 int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			 int sizePerPage = 10;
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 if( searchWord != null && !searchWord.trim().isEmpty() ) {
				 pstmt.setString(1, searchWord);
				 pstmt.setInt(2, (currentShowPageNo*sizePerPage) - (sizePerPage - 1) ); // 공식 
				 pstmt.setInt(3, (currentShowPageNo*sizePerPage) ); // 공식
			 }
			 else {
				 pstmt.setInt(1, (currentShowPageNo*sizePerPage) - (sizePerPage - 1) ); // 공식 
				 pstmt.setInt(2, (currentShowPageNo*sizePerPage) ); // 공식
			 }
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 MemberDTO mvo = new MemberDTO();
				 mvo.setUserid(rs.getString(1));
				 mvo.setJoindate(rs.getString(2));
				 mvo.setMembership(rs.getInt(3)); 
				 mvo.setUsername(rs.getString(4));
				 mvo.setMobile(rs.getString(5));
				 mvo.setSecession(rs.getInt(6));
				 mvo.setRest_member(rs.getInt(7));
				 
				 memberList.add(mvo);
			 }// end of while------------------------
			 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return memberList;
	}

	
	// 회원탈퇴하는 메소드
	@Override
	public int deleteMember(String userid) throws SQLException {
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " update tbl_member_login set secession = 1 "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		
		return result;
	}
		
		
		
		
		
		
		
		
}


























