package bean;

public class Facility {

	private String facility_id;
	private String facility_name;
	private String facility_address;
	private int facility_tel;
	private String facility_mail;
	private Boolean plan_is_attend;

	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getFacility_address() {
		return facility_address;
	}
	public void setFacility_address(String facility_address) {
		this.facility_address = facility_address;
	}
	public int getFacility_tel() {
		return facility_tel;
	}
	public void setFacility_tel(int facility_tel) {
		this.facility_tel = facility_tel;
	}
	public String getFacility_mail() {
		return facility_mail;
	}
	public void setFacility_mail(String facility_mail) {
		this.facility_mail = facility_mail;
	}
	public boolean getPlan_is_attend() {
		return plan_is_attend;
	}
	public void setPlan_is_attend(boolean plan_is_attend) {
		this.plan_is_attend = plan_is_attend;
	}

}