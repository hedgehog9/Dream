package josh.product.model;

public class ProductDTO {
	   
	   //fields
	   private String register_date;   //등록날짜
	   private String product_name;   //상품명
	   private String product_content; // 상품설명
	   private String product_image;   //상품이미지파일
	   private String category;       //카테고리
	   private String detail_category;   //상세카테고리
	   private String gender;         //성별
	   private String product_size;   //사이즈
	   private float discount_rate;   //할인율
	   private int product_num;        //상품번호
	   private int price;             //가격
	   private int sale;              //판매여부
	   private int product_cnt;      //수량
	   
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
	   public String getProduct_size() {
	      return product_size;
	   }
	   public void setProduct_size(String product_size) {
	      this.product_size = product_size;
	   }
	   public float getDiscount_rate() {
	      return discount_rate;
	   }
	   public void setDiscount_rate(float discount_rate) {
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
	   public int getSale() {
	      return sale;
	   }
	   public void setSale(int sale) {
	      this.sale = sale;
	   }
	   public int getProduct_cnt() {
	      return product_cnt;
	   }
	   public void setProduct_cnt(int product_cnt) {
	      this.product_cnt = product_cnt;
	   }
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	
	public int getDiscount_price() {
		
		int discountPrice = 0;
		
		discountPrice = (int) (price - (price * discount_rate));
		
		return discountPrice;
		
	}
	
	   
	}

















