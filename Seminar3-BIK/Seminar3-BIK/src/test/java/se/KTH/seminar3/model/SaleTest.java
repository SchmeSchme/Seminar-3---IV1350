package se.KTH.seminar3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.KTH.seminar3.integration.AccountingHandler;
import se.KTH.seminar3.integration.InventoryHandler;


public class SaleTest {
    private Sale finalizedSale;
    private InventoryHandler invHandler;
    private AccountingHandler accHandler;
    
    private final String STORENAME = "BIK AB";
    private final String STOREADDRESS = "BIKgatan 3";
    private final int BARCODE = 111;
    private final int ITEMQUANTITY = 1;
    private final double PAYMENTAMOUNT = 0;
        
    @BeforeEach
    public void setUp() {
        StoreDTO storeInformation = new StoreDTO(STORENAME, STOREADDRESS);
        this.invHandler = new InventoryHandler();
        this.accHandler = new AccountingHandler();
        this.finalizedSale = new Sale(storeInformation, invHandler);
    }

    @Test
    public void testAddItemToSale() {
        ItemDTO result = finalizedSale.addItemToSale(BARCODE, ITEMQUANTITY, invHandler);
        assertTrue(result instanceof ItemDTO, "ItemDTO object is expected but has not been returned");
    }

    @Test
    public void testCheckIfItemAlreadyRegistered() {
        boolean result = finalizedSale.checkIfItemAlreadyRegistered(BARCODE);
        assertFalse(result, "Item is not expected to be already registered");
    }

    @Test
    public void testIncreaseQuantity() {
       ItemDTO result = finalizedSale.increaseQuantity(BARCODE, ITEMQUANTITY);
        assertNull(result, "No item is expected to be found for increasing quantity");
    }

    @Test
    public void testUpdateProducts() {
        SaleDTO result = finalizedSale.updateProducts();
        assertTrue(result instanceof SaleDTO, "SaleDTO object is expected but has not been returned");
    }

    @Test
    public void testSaleInformation() {
        Payment payment = new Payment(PAYMENTAMOUNT, accHandler);
        SaleDTO result = finalizedSale.SaleInformation(payment, invHandler);
        assertTrue(result instanceof SaleDTO, "SaleDTO object is expected but has not been returned");
    }

    @Test
    public void testUpdateHandlers() {
            int expectedHandlersSizeBefore = finalizedSale.getListOfItems().size();
            finalizedSale.updateHandlers(PAYMENTAMOUNT, accHandler, invHandler);
            int expectedHandlersSizeAfter = finalizedSale.getListOfItems().size();
            assertEquals(expectedHandlersSizeBefore, expectedHandlersSizeAfter, "The size of the item list should remain the same after updating handlers");
        }

}
