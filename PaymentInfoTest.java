package tests;

import static org.junit.Assert.*;

import org.junit.*;
import term_project.PaymentInfo;

public class PaymentInfoTest {
	
	private PaymentInfo paymentInfo;

    @Before
    public void setUp() {
        paymentInfo = new PaymentInfo(2.0);
    }

    @Test
    public void testGetRecievedPayment() {
        paymentInfo.setRecievedPayment(50.0);
        assertEquals(50.0, paymentInfo.getRecievedPayment(), 0.01); 
    }

    @Test
    public void testGetDiscountRatio() {
        assertEquals(0.1, PaymentInfo.getDiscountRatio(), 0.01); 
    }

    @Test
    public void testIncreasePayment() {
        paymentInfo.setRecievedPayment(30.0);
        paymentInfo.increasePayment(20.0);
        assertEquals(50.0, paymentInfo.getRecievedPayment(), 0.01); 
    }
    
    @Test
    public void testIncreasePaymentWithNegativeAmount() {
        paymentInfo.setRecievedPayment(50.0);
        paymentInfo.increasePayment(-20.0);
        assertEquals(50.0, paymentInfo.getRecievedPayment(), 0.01); 
    }
    @Test
    public void testGetSetReceivedPayment() {
        PaymentInfo paymentInfo = new PaymentInfo(50.0);
        assertEquals(50.0, paymentInfo.getRecievedPayment(), 0.01);

        paymentInfo.setRecievedPayment(70.0);
        assertEquals(70.0, paymentInfo.getRecievedPayment(), 0.01);
    }

    @Test
    public void testIncreasePaymentValidAmount() {
        PaymentInfo paymentInfo = new PaymentInfo(30.0);
        paymentInfo.increasePayment(20.0);
        assertEquals(50.0, paymentInfo.getRecievedPayment(), 0.01);
    }

    @Test
    public void testIncreasePaymentNegativeAmount() {
        PaymentInfo paymentInfo = new PaymentInfo(30.0);
        paymentInfo.increasePayment(-20.0);
        assertEquals(30.0, paymentInfo.getRecievedPayment(), 0.01);
    }

    

}
