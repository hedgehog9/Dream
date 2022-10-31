package hgb.address.model;

public class AddressDTO {
	
	
	private String order_name;             // 배송받을사람 이름(배송지)
	private String mobile;                 // 배송받을사람 전화번호 (SHA-256 암호화 대상)
    private String post_code;              // 우편번호
    private String address;                // 주소 
    private String address_num;            // 주소번호
	


	private String detail_address;         // 상세주소 
	private String basic_address;  
	private String userid;
	
		


	// 불러오기용 전화번호 나누기
    String first_mobile;
	String second_mobile; 
	String third_mobile;
    
	
    
    public AddressDTO() {}
    
    
    public AddressDTO(String order_name, String mobile, String post_code, String address, String detail_address, String address_num, String userid) {
    	
    	super();   	
		this.address_num = address_num;
		this.order_name = order_name;
		this.mobile = mobile;
		this.post_code = post_code;
		this.address = address;		
		this.detail_address = detail_address;						
		this.userid = userid;
    }
    
    
    
    public AddressDTO(String order_name, String mobile, String post_code, String address, String detail_address,String basic_address, String address_num, String userid) {
    	
    	super();   			
		this.order_name = order_name;
		this.post_code = post_code;
		this.address = address;
		this.mobile = mobile;
		this.detail_address = detail_address;		
		this.basic_address = basic_address;
		this.address_num = address_num;
		this.userid = userid;
    }
    
    
    
  
   

	public String getBasic_address() {
		return basic_address;
	}


	public void setBasic_address(String basic_address) {
		this.basic_address = basic_address;
	}


	public AddressDTO(String address_num) {
    	
    	super();   			
		this.address_num = address_num;
					
		
    }
	
	
	
    public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	
	
	public String getFirst_mobile() {
		first_mobile = mobile.substring(0,3);
		return first_mobile;
	}


	private void setFirst_mobile(String first_mobile) {
		this.first_mobile = first_mobile;
	}


	public String getSecond_mobile() {
		second_mobile = mobile.substring(3,4)+ "***";
		return second_mobile;
	}


	private void setSecond_mobile(String second_mobile) {
		this.second_mobile = second_mobile;
	}


	public String getThird_mobile() {
		third_mobile = mobile.substring(7,8)+ "***";
		return third_mobile;
	}


	public String getAddress_num() {
		return address_num;
	}


	public void setAddress_num(String address_num) {
		this.address_num = address_num;
	}


	private void setThird_mobile(String third_mobile) {
		this.third_mobile = third_mobile;
	}
	

	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
	
	

}



