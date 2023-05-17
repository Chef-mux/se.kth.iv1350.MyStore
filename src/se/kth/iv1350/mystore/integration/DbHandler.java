package se.kth.iv1350.mystore.integration;

import se.kth.iv1350.mystore.model.ReceiptDTO;

/*
public class DbHandler
handles calls to external databases
 */
public class DbHandler {

    /*
    public constructor DbHandler
    @param null
    @return DbHandler

    creates instance of DbHandler
    */
    public DbHandler(){

    }

    /*
    public method getItemDTO
    @param String itemIdentifier
    @return ItemDTO
    calls on external systems to deliver information about an item
    with matching identifier.
     */
    public ItemDTO getItemDTO(String itemIdentifier) {
       ItemDTO itemDTO = ExternalInventoryDatabase.getItemDTO(itemIdentifier);
      // if(itemDTO != null)
           return itemDTO;

    }

    /*
    public method updateDatabasesAndLogSale
    @param ReceiptDTO
    @return Void

    relays ReceiptDTO to external databases for logging and updates.
     */
    public void updateDatabasesAndLogSale(ReceiptDTO receiptDTO){
    }
}
