package Model;

public class Product {

    String product_id;//: product identifier
	String product_name;//: name of the product
	String aisle_id;//: foreign key
	String department_id;//: foreign key
	
	
	public Product(String product_id, String product_name, String aisle_id, String department_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.aisle_id = aisle_id;
		this.department_id = department_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getAisle_id() {
		return aisle_id;
	}
	public void setAisle_id(String aisle_id) {
		this.aisle_id = aisle_id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	
}
