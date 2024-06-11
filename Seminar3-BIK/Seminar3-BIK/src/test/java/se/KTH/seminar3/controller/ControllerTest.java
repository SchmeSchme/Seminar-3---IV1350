package se.KTH.seminar3.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.KTH.seminar3.integration.AccountingHandler;
import se.KTH.seminar3.integration.InventoryHandler;
import se.KTH.seminar3.integration.SaleLog;
import se.KTH.seminar3.model.ItemDTO;
import se.KTH.seminar3.model.Receipt;
import se.KTH.seminar3.model.Sale;
import se.KTH.seminar3.model.SaleDTO;
import se.KTH.seminar3.model.StoreDTO;


public class ControllerTest {
    private Controller contr;
    private InventoryHandler invHandler;
    private AccountingHandler accHandler;
    private SaleLog saleLog;
    private StoreDTO storeDTO;
    private Sale finalizedSale;
    private final int BARCODE = 111;
    private final int QUANTITY = 1;
    private final double PAYMENTAMOUNT = 100;
    
    
    @BeforeEach
    public void setUp() {
        this.saleLog = new SaleLog();
        this.storeDTO = new StoreDTO("BIK AB", "BIKgatan 3");
        this.contr = new Controller();
        finalizedSale = new Sale(storeDTO, invHandler);
        contr.startSale();
    }
    

    @Test
    public void testCreateStoreDTO() {
        String storeAddress = "Test Address";
        String storeName = "Test Name";
        StoreDTO result = contr.createStoreDTO(storeAddress, storeName);
        assertTrue(result instanceof StoreDTO, "StoreDTO object is expected but has not been returned");

}

    @Test
    public void testRegisterItem() {
        ItemDTO result = contr.registerItem(BARCODE, QUANTITY);
        assertFalse(result == null, "ItemDTO object is expected but has not returned");
    }

    @Test
    public void testEndSale() {
        SaleDTO result = contr.endSale();
        assertTrue(result instanceof SaleDTO, "SaleDTO object is expected but has not been returned");
    }

    @Test
    public void testRegisterPayment() {
        Receipt result = contr.registerPayment(PAYMENTAMOUNT, accHandler);
        assertTrue(result instanceof Receipt, "A receipt is expected to print after the payment");
    }
    
}
