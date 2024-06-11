package se.KTH.seminar3.integration;

import java.util.ArrayList;
import java.util.List;
import se.KTH.seminar3.model.SaleDTO;
import se.KTH.seminar3.model.ItemDTO;



public class InventoryHandler {
    private List<ItemDTO> itemCatalog = new ArrayList<>(); 
    
    /**
     * Creates an instance of items in a fake database. up to 5 items can be called. 
     * order of item information: item name, item price, VAT, bar code.
     */
    public InventoryHandler(){
        itemCatalog.add(new ItemDTO("Oboy", 20.0, 0.06, 111, 1));
        itemCatalog.add(new ItemDTO("Orange Juice", 18.0, 0.06, 222, 1));
        itemCatalog.add(new ItemDTO("Salmon", 85.0, 0.25, 333, 1));
        itemCatalog.add(new ItemDTO("Gifflar", 23.0, 0.12, 444, 1));
        itemCatalog.add(new ItemDTO("Proteinbar", 20.0, 0.06, 555, 1));

    }
    
    /**
     * 
     * Gets the item information of the item that is scanned from the fake database.
     * @param barCode The items bar code which is scanned.
     * @return <code>null</code> if there is no item
    */
  public ItemDTO getItemInformation (int barCode){
        for (int i = 0; i < itemCatalog.size(); i++) {
            if (itemCatalog.get(i).getBarCode() == barCode) {
                ItemDTO itemDTO = new ItemDTO(itemCatalog.get(i).getItemName(), itemCatalog.get(i).getItemPrice(), itemCatalog.get(i).getItemVAT(), itemCatalog.get(i).getBarCode(),
                                              itemCatalog.get(i).getItemQuantity());
                return itemDTO;
            }
        }
    return null; 
    }
  
  /**
   * 
   * The inventory is updated with the saleInformation after completed Sale.
     * @param saleInformation
   */
  public void updateInventory(SaleDTO saleInformation){
  }
  
  /**
   * Method decreases the quantity of a item bought from a sale, in the item inventory.
   * @param itemBarCode - the items bar code.
   * @param itemQuantity - the quantity decreased from the item inventory.
   */
    public void decreaseItemQuantityInInventoryHandler(int itemBarCode, int itemQuantity){
         for (int i = 0; i < itemCatalog.size(); i++) {
                 ItemDTO item = itemCatalog.get(i);
                    if (item.getBarCode() == itemBarCode) {
                        int newQuantity = item.getItemQuantity() - itemQuantity;
                        ItemDTO newItem = new ItemDTO(item.getItemName(), item.getItemPrice(), item.getItemVAT(), item.getBarCode(), newQuantity);
                        itemCatalog.set(i, newItem);
                        break;
                        }
                    }
                }

}
