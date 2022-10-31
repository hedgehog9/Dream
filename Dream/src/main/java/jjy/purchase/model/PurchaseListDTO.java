package jjy.purchase.model;

import jjy.address.model.AddressDTO;
import jjy.member.model.MemberDTO;
import jjy.product.model.ProductDTO;

public class PurchaseListDTO {
	
	private int order_num;   //주문일련번호
	private String userid;	 //주문자 아이디
	private int product_num; //제품번호
	private int buy_cnt;     //구매수량
	private String buy_date; //구매일자
	private int shipping;    //배송상태
	
	// 제품 이름, 제품이미지명 갖고오는 용도
	///////////////////////////////////////////////////////////
	private ProductDTO prodDTO = new ProductDTO();    // 상품테이블
	private MemberDTO memberDTO = new MemberDTO();    // 회원테이블 
	private AddressDTO addressDTO = new AddressDTO(); // 주소록테이블
	
	
	
	// JOIN 용 DTO ===================================================// 
	// ===== MemberDTO ===== // 
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	// ===== AddressDTO ===== // 
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	// ===== product DTO ===== // 
	public ProductDTO getProdDTO() {
		return prodDTO;
	}
	public void setProdDTO(ProductDTO prodDTO) {
		this.prodDTO = prodDTO;
	}
	// JOIN 용 DTO ===================================================// 

	
	
	
	// Getter, Setter 시작 ===========================================//
	public int getOrder_num() {
		return order_num;
	}
	
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getProduct_num() {
		return product_num;
	}
	
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	
	public int getBuy_cnt() {
		return buy_cnt;
	}
	
	public void setBuy_cnt(int buy_cnt) {
		this.buy_cnt = buy_cnt;
	}
	
	public String getBuy_date() {
		return buy_date;
	}
	
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	
	public int getShipping() {
		return shipping;
	}
	
	public void setShipping(int shipping) {
		this.shipping = shipping;
	}
	// Getter, Setter 끝 ===========================================//
	
	
	
	
	
}
