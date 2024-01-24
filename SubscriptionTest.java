package tests;

import static org.junit.Assert.*;


import org.junit.*;

import term_project.DateInfo;
import term_project.Individual;
import term_project.Journal;
import term_project.PaymentInfo;
import term_project.Subscription;

public class SubscriptionTest {
	
	private Subscription subscription;
	private DateInfo dates;
	private Journal journal;
	private Individual subscriber;

    @SuppressWarnings("serial")
	@Before
    public void setUp() {
        dates = new DateInfo(01, 11, 2023);
        journal = new Journal("Car Magazine", "1234-5678", 12, 15.99);
        subscriber = new Individual("Koço Fani", "Bagcilar, Istanbul") {
			@Override
            public String getBillingInformation() {
                return subscriber.getBillingInformation();
            }
        };
        subscription = new Subscription(dates, 2, journal, subscriber);
    }

    @Test
    public void testGetPayment() {
        assertNull(subscription.getPayment());
    }

    @Test
    public void testSetPayment() {
        PaymentInfo paymentInfo = new PaymentInfo(2.0);
        subscription.setPayment(paymentInfo);
        assertEquals(paymentInfo, subscription.getPayment());
    }

    @Test
    public void testGetCopies() {
        assertEquals(2, subscription.getCopies());
    }

    @Test
    public void testSetCopies() {
        subscription.setCopies(3);
        assertEquals(3, subscription.getCopies());
    }

    @Test
    public void testGetDates() {
        assertNotNull(subscription.getDates());
    }

    @Test
    public void testGetJournal() {
        assertNotNull(subscription.getJournal());
    }

    @Test
    public void testGetSubscriber() {
        assertNotNull(subscription.getSubscriber());
    }

    @Test
    public void testCanSendWithNullDatesAndPayment() {
        assertFalse(subscription.canSend(6)); 
    }
    
    @Test
    public void testCanSend_whenDatesAndPaymentNull_expectFalse() {
        Subscription subscription = new Subscription(null, 0, null, null); 
        assertFalse(subscription.canSend(5));
    }

    @Test
    public void testCanSend_whenIssueMonthOutOfRange_expectFalse() {
        DateInfo dates = new DateInfo(1, 1, 2023);
        Subscription subscription = new Subscription(dates, 0, new Journal("1234", "Journal A", 3, 22.6), new Individual("Koço Fani", "Bagcilar, Istanbul")); 
        assertFalse(subscription.canSend(12));
    }

    @Test
    public void testIsPaymentComplete_whenReceivedPaymentEqualToExpected_expectFalse() {
    	DateInfo dates = new DateInfo(1, 1, 2023);
        Journal journal = new Journal("Some Journal", "ISSN123", 12, 10.0); // Modify journal as needed
        Subscription subscription = new Subscription(dates, 1, journal, new Individual("Koço Fani", "Bagcilar, Istanbul")); // Adjust parameters as needed
        PaymentInfo payment = new PaymentInfo(120.0); // Modify payment as needed
        subscription.setPayment(payment);
        assertFalse(subscription.isPaymentComplete());
    }

	
}
