package choi.member.model;

public class MemberDTO {

   private String userid;      // 유저아이디
   private String joindate;      // 가입일자
   private int membership;      // 멤버쉽 가입여부
   private String mobile;         // 전화번호
   private String username;      // 유저 닉네임
   
   // == select 용 == //
   private String passwd;
   
   
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public String getJoindate() {
      return joindate;
   }
   public void setJoindate(String joindate) {
      this.joindate = joindate;
   }
   public int getMembership() {
      return membership;
   }
   public void setMembership(int membership) {
      this.membership = membership;
   }
   public String getMobile() {
      return mobile;
   }
   public void setMobile(String mobile) {
      this.mobile = mobile;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getPasswd() {
      return passwd;
   }
   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }
   
}
