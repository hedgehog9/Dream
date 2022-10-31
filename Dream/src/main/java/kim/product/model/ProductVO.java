package kim.product.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductVO {

	private int product_num;        // 제품번호   
	private String register_date;   // 등록일자           
	private String product_name;    // 제품명       
	private String product_image;   // 제품 이미지 ,로 구분         
	private String category;        // 카테고리    
	

	private String detail_category; // 소카테고리  , 로 구분      
	private int price;           	// 가격
	private float discount_rate;
	private String gender;          // "없음" "남" "여"    
	private String product_content;
	
	
	// 주의!
	private List<String> product_size;    // 제품 사이즈        
	private List<String> product_cnt;     // 제품 수량  
	
	// 불러오기용 값
	private int likeCnt;
	private int discountPrice;
	
	// 위 두 배열은 같은 인덱스 => 관계있는 값임
	 // index0 ==> size=s  cnt=20
	 // index1 ==> size=m  cnt=10
	 // index2 ==> size=l  cnt=40
	
	
	private List<String> order_product_size;    // 주문 제품 사이즈        
	
	private List<String> order_product_cnt; 	  // 주문 제품 수량  
	
	

	private void setDiscountPrice(int discountPrice) {
		// set 불가능하게 막아둠
		this.discountPrice = discountPrice;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	// 게러세러 
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDetail_category() {
		return detail_category;
	}
	public void setDetail_category(String detail_category) {
		this.detail_category = detail_category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public float getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(float discount_rate) {
		this.discount_rate = discount_rate;
	}
	

	
	public List<String> getProduct_size() {
		return product_size;
	}
	public void setProduct_size(List<String> product_size) {
		this.product_size = product_size;
	}
	public List<String> getProduct_cnt() {
		return product_cnt;
	}
	public void setProduct_cnt(List<String> product_cnt) {
		this.product_cnt = product_cnt;
	}
	
	
	
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}

	public List<String> getOrder_product_size() {
		return order_product_size;
	}
	public void setOrder_product_size(List<String> order_product_size) {
		this.order_product_size = order_product_size;
	}
	public List<String> getOrder_product_cnt() {
		return order_product_cnt;
	}
	public void setOrder_product_cnt(List<String> order_product_cnt) {
		this.order_product_cnt = order_product_cnt;
	}


	// 게러세러 끝
	
	
	public int getDiscountPrice() {
		
		discountPrice = (int)(price *(1-discount_rate));
		return discountPrice;
	}
	
	// , 로 들어간 녀석들 배열로 변환
	
	 public List<String> getProduct_image_array	() {

		List<String> resultList = new ArrayList<String>();
		
		if(product_image.trim().isEmpty()) {
			return resultList;
		}
		else {
			resultList = new ArrayList(Arrays.asList(product_image.split(","))); 
			 
			return resultList;
		}
		
		
		
	 }
	 
	


}
