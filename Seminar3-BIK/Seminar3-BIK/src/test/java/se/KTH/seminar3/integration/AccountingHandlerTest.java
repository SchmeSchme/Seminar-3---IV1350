package se.KTH.seminar3.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class AccountingHandlerTest {
    private final float TOTALPAYMENT = 100;

    @Test
    public void testUpdateAccountHandler() {
        AccountingHandler instanceToTest = new AccountingHandler();
        double initialBalance = instanceToTest.getBalance();
        instanceToTest.updateAccountHandler(TOTALPAYMENT);
        double finalBalance = instanceToTest.getBalance();
        assertEquals(initialBalance + TOTALPAYMENT, finalBalance, "The balance has been correctly updated");
    }
}
