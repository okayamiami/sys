package bean;

public class Absence {

	private String absence_id;
	private String absence_main;
	private String child_id;
	private String absence_date;
	private String facility_id;
	private boolean abs_is_attend;

	public String getAbsence_id() {
		return absence_id;
	}
	public void setAbsence_id(String absence_id) {
		this.absence_id = absence_id;
	}
	public String getAbsence_main() {
		return absence_main;
	}
	public void setAbsence_main(String absence_main) {
		this.absence_main = absence_main;
	}
	public String getChild_id() {
		return child_id;
	}
	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}
	public String getAbsence_date() {
		return absence_date;
	}
	public void setAbsence_date(String absence_date) {
		this.absence_date = absence_date;
	}
	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public boolean getAbs_is_attend() {
		return abs_is_attend;
	}
	public void setAbs_is_attend(boolean abs_is_attend) {
		this.abs_is_attend = abs_is_attend;
	}
}