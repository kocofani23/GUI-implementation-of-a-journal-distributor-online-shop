package term_project;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Distributor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1443803143277435087L;
	//private fields
	private Hashtable<String, Journal> journals = new Hashtable<>();
	private Vector<Subscriber> subscribers = new Vector<>();
	private List<Subscription> annualPayments = new ArrayList<>();
	private List<Subscription> expiringSubscriptions = new ArrayList<>();
	
	private final Lock lock = new ReentrantLock();
	private volatile boolean isReportInProgress = false;
	
	//public constructor 
	public Distributor(Hashtable<String, Journal> journals, Vector<Subscriber> subscribers2) {
		super();
		this.journals = journals;
		this.subscribers = subscribers2;
	}
	
	
	//below are the getters and setters
	public Hashtable<String, Journal> getJournals() {
		return journals;
	}
	public void setJournals(Hashtable<String, Journal> journals) {
		this.journals = journals;
	}
	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Vector<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
	public List<Subscription> getAnnualPayments() {
		return annualPayments;
	}
	public void setAnnualPayments(List<Subscription> annualPayments) {
		this.annualPayments = annualPayments;
	}
	public List<Subscription> getExpiringSubscriptions() {
		return expiringSubscriptions;
	}
	public void setExpiringSubscriptions(List<Subscription> expiringSubscriptions) {
		this.expiringSubscriptions = expiringSubscriptions;
	}
	
	
	
	//method to add journal to hashtable
	public boolean addJournal(Journal journal) {
		
		if(journals.contains(journal)) {
			System.out.println("Journal already in  Distributor");
			return false;
		}
		journals.put(journal.getIssn(), journal);
		System.out.println("Journal with ISSN: "+ journal.getIssn()+ " added successfully to Distributor");
		return true;
	}
	
	//method to search for journal given issn as a parameter
	public Journal searchJournal(String issn) throws JournalNotFoundException {
		
		if(journals.containsKey(issn)){
			System.out.println("Journal with ISSN: "+ issn + "is found in Distributor");
			return journals.get(issn);
		}
		else {
			throw new JournalNotFoundException("Could not find journal with ISSN: "+ issn);
		}
		
	}
	
	//method to add subscriber to distributor company. If subscriber has already subscribed before returns false
	public boolean addSubscriber(Subscriber subscriber) {
		
		if(subscribers.contains(subscriber)) {
			System.out.println("Subscriber already has subscribed before.");
			return false;
		}
		subscribers.add(subscriber);
		System.out.println("Subscriber added successfully");
		return true;
		
	}
	
	//method to search for a subscriber given its name
	public Subscriber searchSubscriber(String name){
		
		for(Subscriber person: subscribers) {
			if(person.getName().equalsIgnoreCase(name)) {
				System.out.println("Subscriber found");
				return person;
			}
		}
		System.out.println("Subscriber not found");
		return null;
		
	}
	
	//method to add subscription in journal with given issn to subscriber
	public boolean addSubscription(String issn, Subscriber subscriber, Subscription subscription) {
		
		Journal journal = journals.get(issn);
        if (journal != null && subscribers.contains(subscriber)) {
            for (Subscription existingSubscription : journal.getSubscriptions()) {
                if (existingSubscription.getSubscriber() == subscriber) {
                    existingSubscription.setCopies(existingSubscription.getCopies() + 1);
                    System.out.println("Subscription increased for existing subscriber.");
                    return true;
                }
            }
            subscription.setCopies(subscription.getCopies() + 1);
            journal.addSubscription(subscription);
            System.out.println("Subscription completed successfully for new subscriber.");
            return true;
        }
        System.out.println("Subscription not completed.");
        return false;
		
	}
	
	//method to list all sending orders of a month and year
	public void listAllSendingOrders(int month, int year) {
		
		 System.out.println("Sending Orders for " + month + "/" + year + ":");
		    
		    for (Journal journal : journals.values()) {
		        System.out.println("Journal ISSN: " + journal.getIssn());
		        for (Subscription subscription : journal.getSubscriptions()) {
		            if (subscription.canSend(month) && subscription.getDates().getStartYear() <= year) {
		                System.out.println("Sending order to subscriber: " + subscription.getSubscriber().getName());
		            }
		        }
		    }
	     }
	
	
	//method to list sending orders of a specific journal given its issn
	public void listSendingOrders(String issn, int month, int year) {
		
		Journal journal = journals.get(issn);
		System.out.println("Sending orders for " + month + "/" + year + " of journal with ISSN " + issn + ":");
		for (Subscription subscription : journal.getSubscriptions()) {
            if (subscription.canSend(month) && subscription.getDates().getStartYear() <= year) {
                System.out.println("Sending to subscriber: " + subscription.getSubscriber().getName());
            }
        }
	}
	
	
	//method to list incomplete payments 
	public void listIncompletePayments() {
		
		System.out.println("List of Incomplete Payments:");
	    for (Journal journal : journals.values()) {
	        for (Subscription subscription : journal.getSubscriptions()) {
	            if (!subscription.isPaymentComplete()) {
	                System.out.println("Subscriber: " + subscription.getSubscriber().getName() +  ", Journal: " + journal.getName() +  ", Payment Status: Incomplete");
	            }
	        }
	    }
	}
	
	//method to list all subscriptions of journal with given issn
	public void listSubscriptions(String issn) {

		System.out.println("Subscriptions made to journal with ISSN "+ issn);
		Journal journal = journals.get(issn);
		for(Subscription subscription : journal.getSubscriptions()) {
			System.out.println(subscription);
		}
	}
	
	//method to save this.distributor to file on disk
	public synchronized void saveState(String fileName) {
		
		if (isReportRunning()) {
            try {
                System.out.println("Waiting for the report to complete before saving state...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
		
		 try {
		      File myObj = new File(fileName);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
		 try {
			 
	         FileOutputStream fileOut = new FileOutputStream(fileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         lock.lock();
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved.");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		 finally {
			 lock.unlock();
		 }
	}
	
	//method to read this.distributor from file on disk
	public synchronized void loadState(String fileName) {
		
		if (isReportRunning()) {
            try {
                System.out.println("Waiting for the report to complete before loading state...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
		
		 try {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         lock.lock();
	         Distributor loadedDistributor = (Distributor) in.readObject();
	            this.journals = loadedDistributor.getJournals();
	            this.subscribers = loadedDistributor.getSubscribers();
	            this.annualPayments = loadedDistributor.getAnnualPayments();
	            this.expiringSubscriptions = loadedDistributor.getExpiringSubscriptions();
	            
	            System.out.println("Distributor state loaded from file: " + fileName);
	            
	            if (this.journals.isEmpty()) {
	                System.out.println("No journals in the loaded file.");
	            } else {
	                System.out.println("Journals loaded from file: ");
	                for (Journal journal : this.journals.values()) {
	                    System.out.println(journal);
	                }
	            }
	            if (this.subscribers.isEmpty()) {
	                System.out.println("No subscribers in the loaded file.");
	            } else {
	                System.out.println("Subscribers loaded from file: ");
	                for (Subscriber subscriber : this.subscribers) {
	                    System.out.println(subscriber);
	                }
	            }
	            if (this.annualPayments.isEmpty()) {
	                System.out.println("No annual payments in the loaded file.");
	            } else {
	                System.out.println("Annual payments loaded from file: ");
	                for (Subscription subscription : this.annualPayments) {
	                    System.out.println(subscription);
	                }
	            }
	            if (this.expiringSubscriptions.isEmpty()) {
	                System.out.println("No expiring subscriptions in the loaded file.");
	            } else {
	                System.out.println("Expiring subscriptions loaded from file: ");
	                for (Subscription subscription : this.expiringSubscriptions) {
	                    System.out.println(subscription);
	                }
	            }
	    
	         in.close();
	         fileIn.close();
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		 finally {
			 lock.unlock();
		 }
	}
	
	//method to create gui implementation
	public void report() {
	    if (isReportRunning()) {
	        System.out.println("A report is already in progress. Cannot start a new report.");
	        return;
	    }

	    isReportInProgress = true;
	    ExecutorService executor = Executors.newSingleThreadExecutor();

	    executor.submit(() -> {
	        try {
	            LocalDate givenDate = LocalDate.now().plusMonths(5);
	            int currentMonth = givenDate.getMonthValue();
	            int currentYear = givenDate.getYear();
	            int startYear = 2022;
	            int endYear = 2023;
	            
	            for (Subscriber subscriber : subscribers) {
	                 Subscription subscription = subscriber.getSubscription();
	                    if(subscription != null) {
	                    	if (subscription.getDates().getEndMonth() >= currentMonth && subscription.getDates().getStartYear() <= currentYear) {
		                        expiringSubscriptions.add(subscription);
		                    }
	                    }
	                }
	            
	            for (Subscriber subscriber : subscribers) {
	                Subscription subscription = subscriber.getSubscription();
	                    if(subscription != null) {
	                    	int startDate = subscription.getDates().getStartYear();
		                    if (startDate >= startYear && startDate <= endYear) {
		                        annualPayments.add(subscription);
		                    }
	                    }
	                }

	            listAllSendingOrders(currentMonth, endYear);
	            listIncompletePayments();

	            // Generate report content
	            StringBuilder reportContent = new StringBuilder();
	            reportContent.append("Expiring Subscriptions: \n");
	            for (Subscription subscription : expiringSubscriptions) {
	                reportContent.append(subscription.toString()).append("\n");
	            }
	            reportContent.append("\nAnnual Payments: \n");
	            for (Subscription subscription : annualPayments) {
	                reportContent.append(subscription.toString()).append("\n");
	            }

	            SwingUtilities.invokeLater(() -> {
	                JTextArea reportTextArea = new JTextArea();
	                reportTextArea.setText(reportContent.toString());
	                reportTextArea.setCaretPosition(0); // Scroll to the top of the report conten
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            isReportInProgress = false;
	        }
	    });

	    executor.shutdown();
	}
	
	public boolean isReportRunning() {
		return isReportInProgress;
    }
}
