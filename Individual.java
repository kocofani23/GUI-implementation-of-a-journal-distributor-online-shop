package term_project;

public class Individual extends Subscriber{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8527052478469999173L;
	private String creditCardNr;
	private int expireMonth, expireYear;
	private int CCV;
	
	
	//public subscriber using fields
	public Individual(String name, String address) {
		super(name, address);
	}
	
	
	//getters and setters for fields
	public String getCreditCardNr() {
		return creditCardNr;
	}
	public void setCreditCardNr(String creditCardNr) {
		this.creditCardNr = creditCardNr;
	}
	public int getExpireMonth() {
		return expireMonth;
	}
	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}
	public int getExpireYear() {
		return expireYear;
	}
	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}
	public int getCCV() {
		return CCV;
	}
	public void setCCV(int CCV) {
		this.CCV = CCV;
	}
	
	
	//billing information about individual subscriber
	@Override
	public String getBillingInformation() {
		
		String intro;
		intro = "[Account type : Individual]\n";
		intro += "Name: " + getName() + "\n";
		intro += "Address: " + getAddress() + "\n";
		intro += "Subscription: " + getSubscription() + "\n";
		return intro;
	}

}
