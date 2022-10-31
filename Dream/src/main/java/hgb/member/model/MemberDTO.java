package hgb.member.model;

public class MemberDTO {

	private String userid;		// 유저아이디
	private String joindate;		// 가입일자
	private int membership;		// 멤버쉽 가입여부
	private int point;			// 포인트
	private String mobile;			// 전화번호
	private String username;		// 유저 닉네임
	
	// == select 용 == //
	private String passwd;
	private String Shipping;
	private int secession;
	private int rest_member;
	private String buy_date;
	private String buy_cnt;
	private String product_num;
	private String order_num;
	
	
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getBuy_cnt() {
		return buy_cnt;
	}
	public void setBuy_cnt(String buy_cnt) {
		this.buy_cnt = buy_cnt;
	}
	public String getProduct_num() {
		return product_num;
	}
	public void setProduct_num(String product_num) {
		this.product_num = product_num;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	
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
	public String getShipping() {
		return Shipping;
	}
	public void setShipping(String shipping) {
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
