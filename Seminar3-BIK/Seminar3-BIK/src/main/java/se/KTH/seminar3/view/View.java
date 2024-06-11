package se.KTH.seminar3.view;

import java.util.Locale;
import se.KTH.seminar3.controller.Controller;
import se.KTH.seminar3.integration.AccountingHandler;
import se.KTH.seminar3.model.ItemDTO;
import se.KTH.seminar3.model.Receipt;
import se.KTH.seminar3.model.SaleDTO;

/**
 * This is a placeholder for the real view.
 * It contains a hard-coded execution with calls to all system operations in the controller.
 */
public class View {
    private Controller contr;
    private final int AMOUNT = 100;
    
    /**
    * Creates a new instance, that uses the controller for all calls to other layers.
    * @param contr The controller to use for all calls to other layers.
    */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /*
    * Performs a fake sale, by calling all system operations in the controller.
    */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("\nA new sale has been started");
        
        int[] barcodes = {555};
        int[] quantities = {3};
        double runningTotal = 0;
        double totalVAT = 0;
        

        for (int i = 0; i < barcodes.length; i++) {
            for (int j = 0; j < quantities[i]; j++) {
                System.out.println("\nItem's barcode scanning ");
                ItemDTO itemDTO = contr.registerItem(barcodes[i], 1);
                printScannedItem(itemDTO);
                
                double vatAmount = itemDTO.getItemPrice() * itemDTO.getItemVAT();
                runningTotal += itemDTO.getItemPrice();
                totalVAT += vatAmount;
                System.out.println("\nRunning Total (incl. VAT): " + runningTotal + " SEK");
                System.out.println("Total VAT: " + totalVAT + " SEK");

            }
        }
        
        

        System.out.println("\nSale ends, Total cost displayed");
        SaleDTO finalizedSale = contr.endSale();
        printFinalizedSale(finalizedSale);
        System.out.println("Customer gives " + AMOUNT + " SEK:");


        System.out.println("Sent sale info to external accounting system.");
        for (int i = 0; i < barcodes.length; i++) {
            System.out.println("Told external inventory system to decrease inventory quantity of item " + barcodes[i] + " by " + quantities[i] + " units.");
            }
        

            System.out.println("\n------------------------Receipt------------------------");
            double paymentAmount = AMOUNT;
            AccountingHandler accHandler = new AccountingHandler();
            Receipt receiptFromSale = contr.registerPayment(paymentAmount, accHandler);
            printReceipt(receiptFromSale, finalizedSale);
    }
    

private void printScannedItem(ItemDTO itemDTO){
    System.out.println("Item(s): " + itemDTO.getItemName() + "\n" +
                       "Price: " + itemDTO.getItemPrice() + " SEK" + "\n" +
                       "Tax[%]: " + (itemDTO.getItemVAT() * 100) + "%" + "\n" +
                       "Quantity: " + itemDTO.getItemQuantity());
}


 private void printFinalizedSale(SaleDTO finalizedSale){
    System.out.println("Items cost (incl.VAT): " + finalizedSale.getTotalPrice() + " SEK" + "\n");
}
 

        private void printReceipt(Receipt receiptFromSale, SaleDTO finalizedSale){
        printSaleDate(receiptFromSale);
        printFinalizedSale(finalizedSale);
        for (ItemDTO item : finalizedSale.getItemList()) {
        System.out.printf(Locale.US, "%-20s %d x %.2f          %.2f SEK%n",
                          item.getItemName(),
                          item.getItemQuantity(),
                          item.getItemPrice(),
                          item.getItemQuantity() * item.getItemPrice());
        }
        
            System.out.printf(Locale.US, "\n%-38s %.2f SEK%n", "Payment: ", (double) AMOUNT);
            System.out.printf(Locale.US, "%-38s %.2f SEK%n", "Change: ", (double) AMOUNT - finalizedSale.getTotalPrice());

            printStoreInformation(receiptFromSale);
            
    }
        

        private void printSaleDate(Receipt receipt){
        System.out.println("\nTime of Sale: " + receipt.getSaleDate());
    }
        

        private void printStoreInformation(Receipt receiptFromSale){
        System.out.println("\nGoodbye! \n" + receiptFromSale.getStoreName() + ", " + receiptFromSale.getStoreAddress());

    }
}
