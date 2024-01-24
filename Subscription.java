package term_project;

import java.io.*;

public class Subscription implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5631042858785351548L;
	private final DateInfo dates;
	private PaymentInfo payment;
	private int copies;
	private final Journal journal;
	private final Subscriber subscriber;
	private double expectedPayment;
	
	
	//public constructor using fields
	public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber) {
		super();
		this.dates = dates;
		this.copies = copies;
		this.journal = journal;
		this.subscriber = subscriber;
	}

	
	//getters and setters for non-final fields
	public PaymentInfo getPayment() {
		return payment;
	}
	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public DateInfo getDates() {
		return dates;
	}
	public Journal getJournal() {
		return journal;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	
	@Override
	public String toString() {
		return "Subscription [dates=" + dates + ", payment=" + payment + ", copies=" + copies + ", journal=" + journal
				+ ", subscriber=" + subscriber + ", expectedPayment=" + expectedPayment + "]";
	}


	//method to accept and increase payment of individual subscriber
	public void acceptPayment(double amount) {
		payment.increasePayment(amount);
		System.out.println("Payment successfully added.");
		
	}
	
	
	//method to check whether a specific journal with its issue month can be sent to a subscriber if enough payment is made
	public boolean canSend(int issueMonth) {
		
		if (dates == null || payment== null) {
            return false; 
        }

        if (dates.getStartMonth() > issueMonth || dates.getEndMonth() < issueMonth) {
            return false; 
        }

        expectedPayment = calculateExpectedPayment();

        if (payment.getRecievedPayment() >= expectedPayment) {
            return true; 
        }
        return false; 
	}
	
	
	//private method to calculate expected payment
	private double calculateExpectedPayment() {

		double issuePrice = this.journal.getIssuePrice(); 
        double discountRatio = PaymentInfo.getDiscountRatio();

        double frequency = this.journal.getFrequency(); 

        return issuePrice * frequency * (1 - discountRatio);
    }
	
	//method to check whether payment is complete or not
	public boolean isPaymentComplete() {
		if(payment.getRecievedPayment() < calculateExpectedPayment()) {
			return false;
		}
		return true;
	}
}
