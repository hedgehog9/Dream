package jjy.login.model;

public class LoginDTO {

	private String userid;               //사용자 아이디 (이메일)
	private String passwd;               //비밀번호 (암호화 필요)
	private String secession;            // 회원 탈퇴여부 
	private String rest_member;          // 회원 휴면여부
	private String update_passwd_date;   // 마지막으로 비밀번호 변경한 날짜,시간
	
	// 파라미터 있는 생성자 생성
	/////////////////////////////////////////////////////////////////
	public LoginDTO( String userid, String passwd, 
					 String secession, String rest_member, 
					 String update_passwd_date) { 
		this.userid = userid;
		this.passwd = passwd;
		this.secession = secession;
		this.rest_member = rest_member;
		this.update_passwd_date = update_passwd_date;
		
	}
	
	// 삭제된 파라미터가 없는 기본 생성자 생성
	public LoginDTO() {}
	//////////////////////////////////////////////////////////////////
	
	private boolean requirePwdChange = false;
	// 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지났으면 true
	// 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지나지 않았으면 false


	//Getter , Setter 시작 ///////////////////////////////////////////
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSecession() {
		return secession;
	}

	public void setSecession(String secession) {
		this.secession = secession;
	}

	public String getRest_member() {
		return rest_member;
	}

	public void setRest_member(String rest_member) {
		this.rest_member = rest_member;
	}

	public String getUpdate_passwd_date() {
		return update_passwd_date;
	}

	public void setUpdate_passwd_date(String update_passwd_date) {
		this.update_passwd_date = update_passwd_date;
	}
	//Getter , Setter 끝 ///////////////////////////////////////////
	
	
	public boolean isRequirePwdChange() {
		return requirePwdChange;
	}

	public void setRequirePwdChange(boolean requirePwdChange) {
		this.requirePwdChange = requirePwdChange;
	}
	
	
	
	
}
