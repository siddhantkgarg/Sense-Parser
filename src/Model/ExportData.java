package Model;

public class ExportData {
	String dow;
	String hod;
	String dep;
	public ExportData(String dow, String hod, String dep) {
		super();
		this.dow = dow;
		this.hod = hod;
		this.dep = dep;
	}
	public String getDow() {
		return dow;
	}
	public void setDow(String dow) {
		this.dow = dow;
	}
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dep == null) ? 0 : dep.hashCode());
		result = prime * result + ((dow == null) ? 0 : dow.hashCode());
		result = prime * result + ((hod == null) ? 0 : hod.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExportData other = (ExportData) obj;
		if (dep == null) {
			if (other.dep != null)
				return false;
		} else if (!dep.equals(other.dep))
			return false;
		if (dow == null) {
			if (other.dow != null)
				return false;
		} else if (!dow.equals(other.dow))
			return false;
		if (hod == null) {
			if (other.hod != null)
				return false;
		} else if (!hod.equals(other.hod))
			return false;
		return true;
	}
	
	
}
