package tests;

import static org.junit.Assert.*;

import org.junit.*;
import term_project.DateInfo;
import term_project.Individual;
import term_project.Journal;
import term_project.Subscription;

public class SubscriberTest {

	private Individual subscriber;
	private DateInfo dates;
	private Journal journal;

    @SuppressWarnings("serial")
	@Before
    public void setUp() {
    	dates = new DateInfo(01, 11, 2023);
    	journal = new Journal("Car Magazine", "1234-5678", 12, 15.99);
        subscriber = new Individual("Koço Fani", "Bagcilar, Istanbul") {
            @Override
            public String getBillingInformation() {
                return null;
            }
        };
    }

    @Test
    public void testGetName() {
        assertEquals("Koço Fani", subscriber.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("Bagcilar, Istanbul", subscriber.getAddress());
    }

    @Test
    public void testSetName() {
         subscriber.setName("Mondi");
        assertEquals("Mondi", subscriber.getName());
    }

    @Test
    public void testSetAddress() {
        subscriber.setAddress("Berat, Arnavutluk");
        assertEquals("Berat, Arnavutluk", subscriber.getAddress());
    }

    @Test
    public void testGetSubscription() {
        Subscription subscription = new Subscription(dates, 2, journal, subscriber);
        subscriber.setSubscription(subscription);
        assertEquals(subscription, subscriber.getSubscription());
    }
}
