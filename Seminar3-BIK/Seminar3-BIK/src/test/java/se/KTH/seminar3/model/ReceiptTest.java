package se.KTH.seminar3.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.KTH.seminar3.integration.AccountingHandler;
import se.KTH.seminar3.integration.InventoryHandler;
import se.KTH.seminar3.integration.SaleLog;


public class ReceiptTest {
       private Receipt receipt;
       private Payment payment;
       private Sale finalizedSale;
       private SaleLog saleLog;
       private InventoryHandler invHandler;
       
       private final String STORENAME = "BIK AB";
       private final String STOREADDRESS = "BIKgatan 3";
       private final double TOTALPAYMENT = 0;
       
       
    @BeforeEach
    public void setUp() {
        StoreDTO store = new StoreDTO(STORENAME, STOREADDRESS);
        this.receipt = new Receipt(store);
        AccountingHandler accHandler = new AccountingHandler();
        this.payment = new Payment(TOTALPAYMENT, accHandler);
        this.finalizedSale = new Sale(store, invHandler);
        this.saleLog = new SaleLog();
        this.invHandler = new InventoryHandler();
    }
    

    @Test
    public void testGenerateReceipt() {
        Receipt result = Receipt.generateReceipt(payment, finalizedSale, saleLog, invHandler);
        assertTrue(result instanceof Receipt, "Receipt was not printed");

    }







}
