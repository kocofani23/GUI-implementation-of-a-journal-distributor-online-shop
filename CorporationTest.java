package tests;

import static org.junit.Assert.*;

import org.junit.*;

import term_project.Corporation;

public class CorporationTest {
	
	@Test
    public void testGetterAndSetters() {
        Corporation corporation = new Corporation("Company", "Address");

        corporation.setBankCode(123);
        assertEquals(123, corporation.getBankCode());

        corporation.setBankName("BankName");
        assertEquals("BankName", corporation.getBankName());

        corporation.setIssueDay(15);
        assertEquals(15, corporation.getIssueDay());

        corporation.setIssueMonth(6);
        assertEquals(6, corporation.getIssueMonth());

        corporation.setIssueYear(2023);
        assertEquals(2023, corporation.getIssueYear());

        corporation.setAccountNumber(456789);
        assertEquals(456789, corporation.getAccountNumber());
    }

    @Test
    public void testGetBillingInformation() {
        Corporation corporation = new Corporation("Company", "Address");
        corporation.setBankCode(123);
        corporation.setBankName("BankName");
        corporation.setIssueDay(15);
        corporation.setIssueMonth(6);
        corporation.setIssueYear(2023);
        corporation.setAccountNumber(456789);

        String expectedBillingInfo = "[Account type : Corporation]\n";
        expectedBillingInfo += "Name: Company\n";
        expectedBillingInfo += "Address: Address\n";
        expectedBillingInfo += "Bank Code: 123\n";
        expectedBillingInfo += "Bank Name: BankName\n";
        expectedBillingInfo += "Issue Date: 15/6/2023\n";
        expectedBillingInfo += "Account Number: 456789\n";

        assertEquals(expectedBillingInfo, corporation.getBillingInformation());
    }
    
    @Test
    public void testCorporationGettersAndSetters() {
        Corporation corporation = new Corporation("ABC Corp", "123 Street");
        corporation.setBankCode(9876);
        corporation.setBankName("Test Bank");
        corporation.setIssueDay(15);
        corporation.setIssueMonth(7);
        corporation.setIssueYear(2023);
        corporation.setAccountNumber(123456789);

        assertEquals(9876, corporation.getBankCode());
        assertEquals("Test Bank", corporation.getBankName());
        assertEquals(15, corporation.getIssueDay());
        assertEquals(7, corporation.getIssueMonth());
        assertEquals(2023, corporation.getIssueYear());
        assertEquals(123456789, corporation.getAccountNumber());
    }

    @Test
    public void testCorporationBillingInformation() {
        Corporation corporation = new Corporation("XYZ Corp", "456 Avenue");
        corporation.setBankCode(4321);
        corporation.setBankName("Another Bank");
        corporation.setIssueDay(20);
        corporation.setIssueMonth(9);
        corporation.setIssueYear(2022);
        corporation.setAccountNumber(987654321);

        String expectedBillingInfo = "[Account type : Corporation]\n" +
                "Name: XYZ Corp\n" +
                "Address: 456 Avenue\n" +
                "Bank Code: 4321\n" +
                "Bank Name: Another Bank\n" +
                "Issue Date: 20/9/2022\n" +
                "Account Number: 987654321\n";

        assertEquals(expectedBillingInfo, corporation.getBillingInformation());
    }

    @Test
    public void testCorporationInitialization() {
        Corporation corporation = new Corporation("Test Corp", "789 Boulevard");
        assertNotNull(corporation);
        assertEquals("Test Corp", corporation.getName());
        assertEquals("789 Boulevard", corporation.getAddress());
    }

}
