package se.KTH.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.KTH.seminar3.integration.AccountingHandler;


public class PaymentTest {
    private AccountingHandler accHandler;
    private Payment payment;
    
    private final double PAYMENTAMOUNT = 100;
    private final double TOTALPAYMENT = 101;

    @BeforeEach
    public void setUp() {
        this.accHandler = new AccountingHandler();
        this.payment = new Payment(PAYMENTAMOUNT, accHandler);
    }
    
    @AfterEach
    public void tearDown() {
        this.accHandler = null;
        this.payment = null;
    }

    @Test
    public void testGetChange() {
        double insufficientPayment = -1;
        double result = payment.getChange(TOTALPAYMENT);
        assertTrue(result > insufficientPayment, "Not enough has been paid for the purchase to be completed");
    }
    
}
