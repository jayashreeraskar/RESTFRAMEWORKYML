package applicationPOJO;

public class Inventory 
{
	String available="available";
	String sold="sold";
	String pending;
	String string;
	public String getAvaialble() {
		return available;
	}
	public void setAvaialble(String avaialble) {
		this.available = avaialble;
	}
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

}
