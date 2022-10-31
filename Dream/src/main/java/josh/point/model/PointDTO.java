package josh.point.model;

public class PointDTO {

	private int point_num;
	private String userid;
	private int product_num;
	private int point_amount;
	private String event_date;
	private String status;
	private String point_exp_period;
	
	public int getPoint_num() {
		return point_num;
	}
	public void setPoint_num(int point_num) {
		this.point_num = point_num;
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
	public int getPoint_amount() {
		return point_amount;
	}
	public void setPoint_amount(int point_amount) {
		this.point_amount = point_amount;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoint_exp_period() {
		return point_exp_period;
	}
	public void setPoint_exp_period(String point_exp_period) {
		this.point_exp_period = point_exp_period;
	}
	
	
	
}
