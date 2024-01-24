package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;
import org.junit.*;

import term_project.Corporation;
import term_project.DateInfo;
import term_project.Distributor;
import term_project.Individual;
import term_project.Journal;
import term_project.JournalNotFoundException;
import term_project.PaymentInfo;
import term_project.Subscription;

public class DistributorTest {

	
	private Distributor distributor;
	private Individual individual;
	private Journal journal;
	private Corporation corporation;

    @Before
    public void setUp() {
        Hashtable<String, Journal> journals = new Hashtable<>();
		Vector<term_project.Subscriber> subscribers = new Vector<>();
        distributor = new Distributor(journals, subscribers);
    }

    @Test
    public void testAddJournal() {
        journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
        assertTrue(distributor.addJournal(journal));
    }

    @Test
    public void testSearchJournalFound() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	distributor.addJournal(journal);
        try {
            assertEquals(journal, distributor.searchJournal("1234-5678"));
        } catch (JournalNotFoundException e) {
            fail("Exception not expected here.");
        }
    }

    @Test
    public void testSearchJournalNotFound() {
        try {
            distributor.searchJournal("NonExistentISSN");
            fail("Expected JournalNotFoundException was not thrown.");
        } catch (JournalNotFoundException e) {
            assertEquals("Could not find journal with ISSN: NonExistentISSN", e.getMessage());
        }
    }

    @Test
    public void testAddSubscriber_Individual_case() {
		individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        assertTrue(distributor.addSubscriber((term_project.Subscriber) individual));
    }

    @Test
    public void testSearchSubscriberFound_Individual_case() {
        individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        distributor.addSubscriber((term_project.Subscriber) individual);
        assertEquals(individual, distributor.searchSubscriber("Koço Fani"));
    }

    @Test
    public void testAddSubscriber_Corporation_case() {
		corporation = new Corporation("Corporation X", "New York, USA");
        assertTrue(distributor.addSubscriber((term_project.Subscriber) corporation));
    }

    @Test
    public void testSearchSubscriberFound_Corporation_case() {
        corporation = new Corporation("Corporation X", "New York, USA");
        distributor.addSubscriber((term_project.Subscriber) corporation);
        assertEquals(corporation, distributor.searchSubscriber("Corporation X"));
    }
    
    @Test
    public void testSearchSubscriberNotFound() {
        assertNull(distributor.searchSubscriber("NonExistentName"));
    }
    
    @Test
    public void testAddSubscription() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));
    }

    // Test listAllSendingOrders method
    @Test
    public void testListAllSendingOrders() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));

        distributor.listAllSendingOrders(1, 2023);
    }

    // Test listSendingOrders method
    @Test
    public void testListSendingOrders() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));

        distributor.listSendingOrders("1234-5678", 1, 2023); 
       }
    
    @Test
    public void testListIncompletePayments() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));

        // Mark payment as incomplete
        subscription.setPayment(new PaymentInfo(2.0));
        subscription.acceptPayment(2.0); 
        distributor.listIncompletePayments(); 
    }

    @Test
    public void testListSubscriptions() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));

        distributor.listSubscriptions("1234-5678"); 
    }

    @Test
    public void testSaveAndLoadState() {
    	journal = new Journal("Car Magazine", "1234-5678", 12, 10.0);
    	individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
        Subscription subscription = new Subscription(new DateInfo(01, 11, 2023), 12, journal, individual);

        assertTrue(distributor.addJournal(journal));
        assertTrue(distributor.addSubscriber(individual));
        assertTrue(distributor.addSubscription("1234-5678", individual, subscription));

        String fileName = "database.ser";

        distributor.saveState(fileName); 
        distributor.loadState(fileName); 
    }
}
