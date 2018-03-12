package Model;

public class OrderProduct {

	String order_id;//: foreign key
	String product_id;//: foreign key
	String add_to_cart_order;//: order in which each product was added to cart
	String reordered;//
	
	
	public OrderProduct(String order_id, String product_id, String add_to_cart_order, String reordered) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.add_to_cart_order = add_to_cart_order;
		this.reordered = reordered;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getAdd_to_cart_order() {
		return add_to_cart_order;
	}
	public void setAdd_to_cart_order(String add_to_cart_order) {
		this.add_to_cart_order = add_to_cart_order;
	}
	public String getReordered() {
		return reordered;
	}
	public void setReordered(String reordered) {
		this.reordered = reordered;
	}
	
}
