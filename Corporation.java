package term_project;

public class Corporation extends Subscriber{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2750829586757208270L;
	private int bankCode;
	private String bankName;
	private int issueDay, issueMonth, issueYear;
	private int accountNumber;
	
	//public constructor 
	public Corporation(String name, String address) {
		super(name, address);
	}
	
	
	//below are the getters and setters needed for after
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(int issueDay) {
		this.issueDay = issueDay;
	}
	public int getIssueMonth() {
		return issueMonth;
	}
	public void setIssueMonth(int issueMonth) {
		this.issueMonth = issueMonth;
	}
	public int getIssueYear() {
		return issueYear;
	}
	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	//billing information about the company
	@Override
	public String getBillingInformation() {
		
		String intro;
		intro = "[Account type : Corporation]\n";
		intro += "Name: " + getName() + "\n";
		intro += "Address: " + getAddress() + "\n";
		intro += "Bank Code: " + bankCode + "\n";
		intro += "Bank Name: " + bankName + "\n";
		intro += "Issue Date: " + issueDay + "/" + issueMonth + "/" + issueYear + "\n";
		intro += "Account Number: " + accountNumber + "\n";
		return intro;
	}
}
