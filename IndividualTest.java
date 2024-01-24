package tests;

import static org.junit.Assert.*;
import org.junit.*;
import term_project.Individual;

public class IndividualTest {
	
	private Individual individual;

    @Before
    public void setUp() {
        individual = new Individual("Koço Fani", "Bagcilar, Istanbul");
    }

    @Test
    public void testCreditCardNr() {
        individual.setCreditCardNr("0000-1234-5678-99");
        assertEquals("0000-1234-5678-99", individual.getCreditCardNr());
    }

    @Test
    public void testExpireMonthAndYear() {
        individual.setExpireMonth(12);
        individual.setExpireYear(2025);
        assertEquals(12, individual.getExpireMonth());
        assertEquals(2025, individual.getExpireYear());
    }
    
    @Test
    public void testGetBillingInformation() {
        individual.setCreditCardNr("0000-9876-5432-11");
        individual.setExpireMonth(11);
        individual.setExpireYear(2024);

        String expectedBillingInfo = "[Account type : Individual]\n" +
                "Name: Koço Fani\n" +
                "Address: Bagcilar, Istanbul\n" +
                "Subscription: " + individual.getSubscription() + "\n"; 

        assertEquals(expectedBillingInfo, individual.getBillingInformation());
    }
    @Test
    public void testIndividualInitialization() {
        Individual individual = new Individual("Jane Roe", "789 Oak St");
        assertNotNull(individual);
        assertEquals("Jane Roe", individual.getName());
        assertEquals("789 Oak St", individual.getAddress());
    }
    @Test
    public void testIndividualGettersAndSetters() {
        Individual individual = new Individual("John Doe", "123 Main St");
        individual.setCreditCardNr("1234567890123456");
        individual.setExpireMonth(12);
        individual.setExpireYear(2025);
        individual.setCCV(123);

        assertEquals("1234567890123456", individual.getCreditCardNr());
        assertEquals(12, individual.getExpireMonth());
        assertEquals(2025, individual.getExpireYear());
        assertEquals(123, individual.getCCV());
    }

    @Test
    public void testIndividualBillingInformation() {
        Individual individual = new Individual("Alice Smith", "456 Elm St");
        individual.setCreditCardNr("9876543210987654");
        individual.setExpireMonth(10);
        individual.setExpireYear(2024);
        individual.setCCV(456);

        String expectedBillingInfo = "[Account type : Individual]\n" +
                "Name: Alice Smith\n" +
                "Address: 456 Elm St\n" +
                "Subscription: null\n";

        assertEquals(expectedBillingInfo, individual.getBillingInformation());
    }
}
