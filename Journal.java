package term_project;

import java.io.*;
import java.util.*;

public class Journal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7028411947761479911L;
	private String name;
	private String issn;
	private int frequency;
	private double issuePrice;
	private ArrayList<Subscription> subscriptions;
	
	//public constructor using fields
	public Journal(String name, String issn, int frequency, double issuePrice) {
		super();
		this.name = name;
		this.issn = issn;
		this.frequency = frequency;
		this.issuePrice = issuePrice;
		subscriptions = new ArrayList<>();
	}
	
	
	//getters and setters for the private fields
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public double getIssuePrice() {
		return issuePrice;
	}
	public void setIssuePrice(double issuePrice) {
		this.issuePrice = issuePrice;
	}
	public ArrayList<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	@Override
	public String toString() {
		return "Journal [name=" + name + ", issn=" + issn + ", frequency=" + frequency + ", issuePrice=" + issuePrice
				+ "]";
	}


	//method to add subscription to journal. more than one subscription is accepted to a journal
	public void addSubscription(Subscription subscription) {
		subscriptions.add(subscription);
		System.out.println("Subscription added successfully to journal " +name);
	}
}
