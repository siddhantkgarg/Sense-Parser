package Model;

public class Department {
	String department_id;//: department identifier
	String department;//: the name of the department
	
	
	public Department(String department_id, String department) {
		super();
		this.department_id = department_id;
		this.department = department;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
