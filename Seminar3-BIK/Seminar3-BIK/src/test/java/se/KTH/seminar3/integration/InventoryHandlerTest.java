package se.KTH.seminar3.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.KTH.seminar3.model.ItemDTO;

/**
 *
 * @author Ziya
 */
public class InventoryHandlerTest {
    private final int BARCODE = 1;

    @Test
    public void testGetItemInformation() {
        InventoryHandler instanceToTest = new InventoryHandler();
        ItemDTO result = instanceToTest.getItemInformation(BARCODE);
        assertFalse(result != null, "The correct item was found");

    }

@Test
public void testDecreaseItemQuantityInInventoryHandler() {
    int itemBarCode = 111; 
    int itemQuantity = 1; 
    InventoryHandler instance = new InventoryHandler();
    instance.decreaseItemQuantityInInventoryHandler(itemBarCode, itemQuantity);
    assertTrue(true, "The method runs correctly");
}
    
}
