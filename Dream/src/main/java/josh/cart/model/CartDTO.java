package josh.cart.model;

public class CartDTO {
	private int cart_num;	 // 장바구니 번호
	private String userid;	 // 유저아이디 멤버테이블 외래키
	private int product_num; // 상품아이디 상품테이블 외래키
	private int cart_cnt;	 // 카트에 담긴 제품의 수량
	
	
	// select 용도 field // 
	private String product_name;
	private String product_content; 
	private String product_image; 
	private int price; 
	private float discount_rate; 
	private String product_size; 
	private int size_cnt;
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
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
	public int getCart_cnt() {
		return cart_cnt;
	}
	public void setCart_cnt(int cart_cnt) {
		this.cart_cnt = cart_cnt;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public float getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(float discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getProduct_size() {
		return product_size;
	}
	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}
	public int getSize_cnt() {
		return size_cnt;
	}
	public void setSize_cnt(int size_cnt) {
		this.size_cnt = size_cnt;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	} 
	
	public int getDiscount_price() {
		
		int discountPrice = 0;
		
		discountPrice = (int) (price - (price * discount_rate));
		
		return discountPrice;
		
	}
	
	
	
}
