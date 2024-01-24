package term_project;

import java.io.*;

public abstract class Subscriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1735848812985314515L;
	private String name;
	private String address;
	private Subscription subscription;
	
	
	//public constructor using fields
	public Subscriber(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	//getters and setters for fields
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	//to string method
	@Override
	public String toString() {
		return "Subscriber [name=" + name + ", address=" + address + "]";
	}

	//abstract method
	public abstract String getBillingInformation();

}
