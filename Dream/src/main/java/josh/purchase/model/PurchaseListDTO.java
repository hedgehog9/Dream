package josh.purchase.model;

public class PurchaseListDTO {
	
	private int order_num;
	private String userid;
	private int product_num;
	private int buy_cnt;
	private String buy_date;
	private int shipping;
	
	// Getter, Setter 시작
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
	// Getter, Setter 끝
	
	
	
	
	
	
	
}
