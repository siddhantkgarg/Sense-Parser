package Model;

public class Order {
	String order_id;//: order identifier
	String user_id;//: customer identifier
	String eval_set;//;;: which evaluation set this order belongs in (see SET described below)
	String order_number;//: the order sequence number for this user (1 = first, n = nth)
	String order_dow;//: the day of the week the order was placed on
	String order_hour_of_day;//: the hour of the day the order was placed on
	String days_since_prior;
	
	public Order(String order_id, String user_id, String eval_set, String order_number, String order_dow, String order_hour_of_day,
			String days_since_prior) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.eval_set = eval_set;
		this.order_number = order_number;
		this.order_dow = order_dow;
		this.order_hour_of_day = order_hour_of_day;
		this.days_since_prior = days_since_prior;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEval_set() {
		return eval_set;
	}
	public void setEval_set(String eval_set) {
		this.eval_set = eval_set;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getOrder_dow() {
		return order_dow;
	}
	public void setOrder_dow(String order_dow) {
		this.order_dow = order_dow;
	}
	public String getOrder_hour_of_day() {
		return order_hour_of_day;
	}
	public void setOrder_hour_of_day(String order_hour_of_day) {
		this.order_hour_of_day = order_hour_of_day;
	}
	public String getDays_since_prior() {
		return days_since_prior;
	}
	public void setDays_since_prior(String days_since_prior) {
		this.days_since_prior = days_since_prior;
	}
	
	
}
