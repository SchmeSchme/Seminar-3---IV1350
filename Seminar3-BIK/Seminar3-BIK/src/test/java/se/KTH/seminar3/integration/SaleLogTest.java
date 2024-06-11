package se.KTH.seminar3.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import se.KTH.seminar3.model.SaleDTO;


public class SaleLogTest {
    
@Test
public void testUpdateSalelog() {
    SaleDTO finalizedSale = null;
    SaleLog instanceToTest = new SaleLog();
    
    int saleLogBeforeUpdate = instanceToTest.getSaleLog().size();
    instanceToTest.updateSalelog(finalizedSale);
    int saleLogAfterUpdate = instanceToTest.getSaleLog().size();
    
    assertTrue(saleLogBeforeUpdate < saleLogAfterUpdate, "salelog is updated correctly");
    }
}

