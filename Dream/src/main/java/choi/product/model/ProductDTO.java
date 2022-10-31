package choi.product.model;

public class ProductDTO {
	
	
	//fields
	private String register_date;	//등록날짜
	private String product_name;	//상품명
	private String product_image;	//상품이미지파일
	private String category;    	//카테고리
	private String detail_category;	//상세카테고리
	private String gender;      	//성별
	private String bestyn;      	//베스트상품인지
	private int discount_rate;	    //할인율
	private int product_num;        //상품번호
	private int price;          	//가격
	private int real_price;			//계산된 가격
	
	// select용
	private int product_like_cnt;	//로그인한 사람이 좋아요를 눌렀는지 알아내기위한 컬럼(0 아니면 1);
	private int like_cnt;	//로그인한 사람이 좋아요를 눌렀는지 알아내기위한 컬럼(0 아니면 1);
	
	//method
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getReal_price() {
		return real_price;
	}
	public void setReal_price(int real_price) {
		this.real_price = real_price;
	}
	public String getBestyn() {
		return bestyn;
	}
	public void setBestyn(String bestyn) {
		this.bestyn = bestyn;
	}
	
	
	
	//select용 method
	public int getProduct_like_cnt() {
		return product_like_cnt;
	}
	public void setProduct_like_cnt(int product_like_cnt) {
		this.product_like_cnt = product_like_cnt;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	
}
