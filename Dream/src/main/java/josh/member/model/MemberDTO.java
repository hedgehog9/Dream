package josh.member.model;

public class MemberDTO {

	private String userid;		// 유저아이디
	private String joindate;		// 가입일자
	private int membership;		// 멤버쉽 가입여부
	private int point;			// 포인트
	private String mobile;			// 전화번호
	private String username;		// 유저 닉네임
	
	// == select 용 == //
	private String passwd;
	private int Shipping;
	private int secession;
	private int rest_member;
	
	
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
	public int getShipping() {
		return Shipping;
	}
	public void setShipping(int shipping) {
		Shipping = shipping;
	}
	public int getSecession() {
		return secession;
	}
	public void setSecession(int secession) {
		this.secession = secession;
	}
	public int getRest_member() {
		return rest_member;
	}
	public void setRest_member(int rest_member) {
		this.rest_member = rest_member;
	}
	
}
