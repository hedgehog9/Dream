package kim.member.model;

public class MemberVO {

	private String userid;   // 사용자 아이디 
	private String joindate; // 가입일자
	private int membership;  // 멤버십 가입여부 0: 가입 / 1: 미가입
	private int point;       // 포인트
	private String username; 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String mobile;   // 핸드폰 번호 
	
	
	
	// Getter, Setter  ////////////////////////////////////////////////////////////
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
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	// Getter, Setter  ////////////////////////////////////////////////////////////
	
	
	
}
