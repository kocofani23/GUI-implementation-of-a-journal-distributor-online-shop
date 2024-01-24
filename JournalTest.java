package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import term_project.DateInfo;
import term_project.Individual;
import term_project.Journal;
import term_project.Subscriber;
import term_project.Subscription;

public class JournalTest {
	
	private Journal journal;
	private DateInfo dates;
	private Subscriber subscriber;

    @Before
    public void setUp() {
        journal = new Journal("Car Magazine", "1234-5678", 12, 15.99);
        dates = new DateInfo(01, 11, 2023);
        subscriber = new Individual("Koço Fani", "Bagcilar, Istanbul");
    }

    @Test
    public void testGetName() {
        assertEquals("Car Magazine", journal.getName());
    }

    @Test
    public void testGetIssn() {
        assertEquals("1234-5678", journal.getIssn());
    }

    @Test
    public void testGetFrequency() {
        assertEquals(12, journal.getFrequency());
    }

    @Test
    public void testGetIssuePrice() {
        assertEquals(15.99, journal.getIssuePrice(), 0.01); // Using delta for double comparison
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription(dates, 2, journal, subscriber);
        journal.addSubscription(subscription);

        ArrayList<Subscription> subscriptions = journal.getSubscriptions();
        assertTrue(subscriptions.contains(subscription));
    }
    
	@Test
    public void testJournalGettersAndSetters() {
        Journal journal = new Journal("Science Journal", "ISSN123", 12, 15.99);
        journal.setName("Nature Journal");
        journal.setIssn("ISSN456");
        journal.setFrequency(6);
        journal.setIssuePrice(10.5);

        assertEquals("Nature Journal", journal.getName());
        assertEquals("ISSN456", journal.getIssn());
        assertEquals(6, journal.getFrequency());
        assertEquals(10.5, journal.getIssuePrice(), 0.01);
    }

    @Test
    public void testJournalAddSubscription() {
        Journal journal = new Journal("Science Journal", "ISSN123", 12, 15.99);
        Subscription subscription1 = new Subscription(new DateInfo(1, 2023, 12), 5, journal, new Individual("John Doe", "123 Main St"));
        Subscription subscription2 = new Subscription(new DateInfo(3, 2023, 12), 8, journal, new Individual("Alice Smith", "456 Elm St"));

        journal.addSubscription(subscription1);
        journal.addSubscription(subscription2);

        assertEquals(2, journal.getSubscriptions().size());
        assertTrue(journal.getSubscriptions().contains(subscription1));
        assertTrue(journal.getSubscriptions().contains(subscription2));
    }

    @Test
    public void testJournalInitialization() {
        Journal journal = new Journal("Science Journal", "ISSN123", 12, 15.99);
        assertNotNull(journal);
        assertEquals("Science Journal", journal.getName());
        assertEquals("ISSN123", journal.getIssn());
        assertEquals(12, journal.getFrequency());
        assertEquals(15.99, journal.getIssuePrice(), 0.01);
        assertEquals(0, journal.getSubscriptions().size());
    }
    
    @Test
    public void testJournalToString() {
        Journal journal = new Journal("Science Journal", "ISSN123", 12, 15.99);
        String expected = "Journal [name=Science Journal, issn=ISSN123, frequency=12, issuePrice=15.99]";
        assertEquals(expected, journal.toString());
    }
}
