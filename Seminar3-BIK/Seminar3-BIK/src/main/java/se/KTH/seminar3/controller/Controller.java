package se.KTH.seminar3.controller;


import se.KTH.seminar3.integration.AccountingHandler;
import se.KTH.seminar3.integration.InventoryHandler;
import se.KTH.seminar3.integration.SaleLog;
import se.KTH.seminar3.model.ItemDTO;
import se.KTH.seminar3.model.Payment;
import se.KTH.seminar3.model.Sale;
import se.KTH.seminar3.model.StoreDTO;
import se.KTH.seminar3.model.Receipt;
import se.KTH.seminar3.model.SaleDTO;

/**
 * This is the applications only controller. All calls to the model pass through this class.
 *
 */
public class Controller {
    private Sale sale;
    private StoreDTO storeInformation;
    private Receipt receipt;
    private InventoryHandler invHandler;
    private SaleLog saleLog;
 
    
    /**
     *  Constructor for controller that takes a parameter for the external systems.
     */
    public Controller(){
     this.storeInformation = new StoreDTO("BIK AB", "BIKgatan 3");
     this.receipt = new Receipt(storeInformation);
     this.invHandler = new InventoryHandler();
     this.saleLog = new SaleLog();
    }
    
    /**
     * Starts a new sale. This method is the initialization of a sale.
     */
    public void startSale(){
        this.sale = new Sale(storeInformation, invHandler);

    }
    
    /**
     * Controller creates StoreDTO object with the information on the stores name and location.
     * @param storeAddress The stores address
     * @param storeName The stores name
     * @return The storeDTO object
     */
    public StoreDTO createStoreDTO(String storeAddress, String storeName)
    {
        StoreDTO storeDTO = new StoreDTO(storeAddress, storeName);
        return storeDTO;
    }
    
    /**
     * 
     * Method to register items via the items bar code, to the sale.
     * @param barCode The items barCode
     * @param itemQuantity The quantity of the item
     * @return Object of the registered item
    */
    public ItemDTO registerItem(int barCode, int itemQuantity) {
        return sale.addItemToSale(barCode, itemQuantity, invHandler);
    }
   /**
    * 
    * The Current sale ends and then returns the saleInformation
    * @return <code>null</code> The object holding the saleInformation. If there is no sale
    */
    public SaleDTO endSale(){
       if(sale != null){
           SaleDTO currentSale = sale.updateProducts();
           return currentSale;           
       }
            else
                return null;
      
   }
     /**
     * registers a payment for the sale
     * @param paymentAmount The amount paid by the customer.
     * @param accHandler Updates the stores accounting from the sale and payment.
     * @return The Receipt object saved from the sale.
     */
    public Receipt registerPayment(double paymentAmount, AccountingHandler accHandler){
        Payment payment = new Payment(paymentAmount, accHandler);
        Receipt receiptFromSale = Receipt.generateReceipt(payment, sale, saleLog, invHandler);
        return receiptFromSale;
    }
}