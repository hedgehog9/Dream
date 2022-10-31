package jjy.login.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

import jjy.member.model.MemberDTO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class LoginDAO implements InterLoginDAO {
   // DBCP
   private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection pool)이다.
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private AES256 aes;

   // 생성자
   public LoginDAO() {

      try {
         // connection pool 을 위한 작업
         Context initContext = new InitialContext();
         Context envContext = (Context) initContext.lookup("java:/comp/env");
         ds = (DataSource) envContext.lookup("/jdbc/dream");

         aes = new AES256(SecretMyKey.KEY);// KEY는 스태틱 변수이기때문에 객체생성 필요 x
         // SecretMyKey.KEY 는 우리가 만든 비밀키이다.

      } catch (NamingException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) { // key 가 16글자 미만인경우 발생하는 예외 처리
         e.printStackTrace();
      }
   }

   // 사용한 자원을 반납하는 close() 메소드 생성하기
   private void close() {

      try {
         if (rs != null) {
            rs.close();
            rs = null;
         }
         if (pstmt != null) {
            pstmt.close();
            pstmt = null;
         }
         if (conn != null) {
            conn.close();
            conn = null;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } // end of try ~ catch()---------------------------------

   }

   // 아이디(userid), 비밀번호(passwd) 를 paraMap 에 저장해 일치하는 사용자가 있는지 조회하는 메소드(select) 
   @Override
   public LoginDTO selectOneUser(Map<String, String> userinfoMap) throws SQLException {
      
      LoginDTO loginuser = null;
      try {
         
         conn = ds.getConnection();
         
         String sql = " select userid , passwd , secession , rest_member , update_passwd_date,  "
                    + " trunc( months_between(sysdate, update_passwd_date) ) AS update_passwd_gap "
                    + " from tbl_member_login "
                    + " where userid =  ?  and passwd =  ? ";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, userinfoMap.get("userid"));
         pstmt.setString(2, Sha256.encrypt(userinfoMap.get("passwd")));
         
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            
            loginuser = new LoginDTO();
            
            loginuser.setUserid(rs.getString("userid"));
            loginuser.setPasswd(rs.getString("passwd"));
            loginuser.setSecession(rs.getString("secession"));
            loginuser.setRest_member(rs.getString("rest_member"));
            loginuser.setUpdate_passwd_date(rs.getString("update_passwd_date"));
            
            if(rs.getInt("update_passwd_gap") >= 3) { // 비밀번호 바꾼날짜가 3개월 이상인경우
               loginuser.setRequirePwdChange(true);
            }
            else { // 비밀번호 바꾼지 3개월 미만인경우 
               loginuser.setRequirePwdChange(false);
            } // end of if~ else()------------------------------------------------------
         }
         
      } finally {
         close();
      }

      return loginuser;

   }// end of public LoginDTO selectOneUser(Map<String, String> userinfoMap) throws SQLException {}--------

   
   
   // 연락처(mobile), 이메일(email)을 Map에 담아 일치하는 사용자가 있으면 true, 없으면 false 를 반환하는 메소드 (select) 
   @Override
   public boolean checkMobileEmail(Map<String, String> findPwdMap)throws SQLException {
	      boolean isExistUser = false;
	      try {
	      
	      conn = ds.getConnection();
	      
	      String sql = " select * "
	               + " from tbl_member "
	               + " where mobile = ? and userid =? ";
	      pstmt = conn.prepareStatement(sql);
	      
	      pstmt.setString(1, findPwdMap.get("mobile") );
	      pstmt.setString(2, findPwdMap.get("userid") );
	      
	      rs = pstmt.executeQuery();
	      
	      isExistUser =rs.next();
	
	   } finally {
	      close();
	   }
	      
	   return isExistUser;
   }// end of public boolean checkMobileEmail(Map<String, String> findPwdMap){}-------------
   
   
   // 아이디(userid) 를 전달받아 일치하는 회원이 있으면 사용자 정보, 없으면 null을 반환하는 메소드 (select) 
   @Override
   public MemberDTO userExist(String mobile) throws SQLException {
      
	  MemberDTO findUser = null;
      
      try {
         conn = ds.getConnection();
         
         String sql = " select userid, mobile "
                    + " from tbl_member "
                    + " where mobile = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, mobile);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            findUser = new MemberDTO();
            findUser.setUserid(rs.getString("userid"));
            findUser.setMobile(rs.getString("mobile"));
         }
   } finally {
      close();
   }
      
      return findUser;
      
   }

   
   // 아이디를 입력받아 가장 마지막에 로그인한 날짜를 구해오는 메소드(select) 
   @Override
   public String checkLastLoginDate(String userid) throws SQLException {
      
      String lastLoginDate = null;
      
      try {
         conn = ds.getConnection();
         
         String sql = " select max(login_date) as login_date "
                  + " from tbl_login_record "
                  + " where userid = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userid);
         
         rs = pstmt.executeQuery();
         rs.next();
         lastLoginDate = (String)rs.getString("login_date");
//         System.out.println("DAO에서 확인 마지막 로그인 날짜: "+lastLoginDate);

      } finally {
         close();
      }
      
      return lastLoginDate;
      
   }// end of public String checkLastLoginDate(String userid) throws SQLException {}---------------


   
   // 아이디를 입력받아 로그인 기록 테이블에 기록하는 메소드(insert) 
   @Override
   public int updateLoginDate(Map<String, String> userinfoMap)throws SQLException {
      
      int result = 0;

      try {
         conn = ds.getConnection();
         
         String sql = " insert into tbl_login_record (login_num, userid,client_ip) values "
                   + " (seq_login_history.nextval, ?, ? ) ";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userinfoMap.get("userid"));
         pstmt.setString(2, userinfoMap.get("client_ip"));
         
         pstmt.executeUpdate();
         
      } finally {
         close();
      }
      return result;
   }// end of public int updateLoginDate(Map<String, String> userinfoMap)throws SQLException {---------------

   
   // 마지막 로그인 날짜로부터 1년 이상 지난경우 휴면유저로 바꿔주는 메소드 
   @Override
   public void updateRestMember(String userid) throws SQLException {
      
      try {
         conn = ds.getConnection();
         
         String sql = " update tbl_member_login set rest_member = 1  "
                  + " where userid = ? ";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userid);
         pstmt.executeUpdate();
         
      } finally {
         close();
      }
      
   }// end of public void updateRestMember(String userid) throws SQLException {}--------------

   
   
   //아이디(userid) 를 전달받아 회원가입 날짜를 String 타입으로 반환시켜주는 메소드 
   @Override
   public String checkRegistDate(String userid) throws SQLException {
      String registDate = null;
      
      try {
         conn = ds.getConnection();
         
         String sql = " select joindate  "
                  + " from tbl_member "
                  + " where userid = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userid);
         
         rs = pstmt.executeQuery();
         rs.next();
         registDate = (String)rs.getString("joindate");
//         System.out.println("DAO에서 확인 가입날짜 : "+registDate);

      } finally {
         close();
      }
      
      return registDate;
   }// end of public String checkRegistDate(String userid) throws SQLException {}-----------------------------

   
   
   //아이디, 핸드폰 번호, 임시 비밀번호를 Map 을 전달받아 비밀번호를 변경하는 메소드 (update)
   @Override
   public int updatePassword(HashMap<String, String> findPwdMap) throws SQLException {
      int updtePwdResult = 0;
      
      try {
         conn = ds.getConnection();
         
         String sql = "  update tbl_member_login set passwd = ? "
                    + " ,update_passwd_date = sysdate "
                    + "  where userid = ? ";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, Sha256.encrypt((findPwdMap.get("tempPwd"))));
         pstmt.setString(2, findPwdMap.get("userid"));
         pstmt.executeUpdate();
         
      } finally {
         close();
      }
      return updtePwdResult;
   }

   
   
   // 관리자 아이디, 비밀번호를 map 으로 전달받아 일치하는 관리자가 있는지 조회하는 메소드 
   @Override
   public boolean selectAdmin(Map<String, String> userinfoMap) throws SQLException {
      
      boolean isAdmin = false;
      try {
         
         conn = ds.getConnection();
         
         String sql = " select admin_id  "
                    + " from tbl_admin_login "
                  + " where admin_id =  ?  and admin_passwd =  ? ";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, userinfoMap.get("userid"));
//         pstmt.setString(2, userinfoMap.get("passwd"));
//         암호화 사용시 아래 코드로 교체 
         pstmt.setString(2, Sha256.encrypt(userinfoMap.get("passwd")));
         rs = pstmt.executeQuery();
         // System.out.println("확인용 암호화 된 암호 => "+ Sha256.encrypt(userinfoMap.get("passwd")));
         
         isAdmin = rs.next();
         
      } finally {
         close();
      }
      // System.out.println("DAO 에서 확인 isAdmin: "+ isAdmin);

      return isAdmin;
      
   }// end of public LoginDTO selectAdmin(Map<String, String> userinfoMap) throws SQLException {}-------------------

   
    /** 사용자 아이디를 입력받아 로그인 기록이 있는지 조회하는 메소드 (있을경우 return 1 없을경우 return 0) */
	@Override
	public boolean checkFirstLogin(String userid) throws SQLException {
		
		boolean ckFristLogin = false;
		
		try {
		      
		      conn = ds.getConnection();
		      
		      String sql = " select login_num "
		      		     + " from tbl_login_record "
		      		     + " where userid = ? ";
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1,userid);
		      
		      rs = pstmt.executeQuery();
		      
		      ckFristLogin = rs.next();

		   } finally {
		      close();
		   }
		
		return ckFristLogin;
		
		
	}
}