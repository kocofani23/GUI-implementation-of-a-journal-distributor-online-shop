package term_project;

import java.io.*;

public class PaymentInfo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3388525846403643920L;
	private static final double discountRatio = 0.1;
	private double recievedPayment;
	
	public PaymentInfo(double recievedPayment) {
		super();
		this.recievedPayment = recievedPayment;
	}
	
	//getters and setters for fields	
	public double getRecievedPayment() {
		return recievedPayment;
	}
	public void setRecievedPayment(double recievedPayment) {
		this.recievedPayment = recievedPayment;
	}
	public static double getDiscountRatio() {
		return discountRatio;
	}
	
	//function to increase payment of individual subscriber
	public void increasePayment(double amount) {
		if(amount >=  0) {
			setRecievedPayment(getRecievedPayment() + amount);
		}
	}	
}
