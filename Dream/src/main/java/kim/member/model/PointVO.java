package kim.member.model;

public class PointVO {

	
	int point_num;
	String userid;
	int point_amount;
	String event_date;
	String status;
	String event_type; 
    String sum;
    String point_number_see;

   

    public String getSum() {
       return sum;
    }
    public void setSum(String sum) {
       this.sum = sum;
    }
    public String getPoint_number_see() {
       return point_number_see;
    }
    public void setPoint_number_see(String point_number_see) {
       this.point_number_see = point_number_see;
    }

	

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

	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	
}
