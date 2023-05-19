package se.kth.iv1350.mystore.integration;

import se.kth.iv1350.mystore.exceptions.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.exceptions.NoContactWithDatabaseException;
import se.kth.iv1350.mystore.model.ReceiptDTO;

/**
public class DbHandler
handles calls to external databases
 */
public class DbHandler {

    /**
    public constructor DbHandler
     @return DbHandler

    creates instance of DbHandler
    */
    public DbHandler(){

    }

    /**
    public method getItemDTO
    @param itemIdentifier itemIdentifier
    @return ItemDTO
    calls on external systems to deliver information about an item
    with matching identifier.
     */
    public ItemDTO getItemDTO(String itemIdentifier)
    throws InvalidItemIdentifierException, NoContactWithDatabaseException {

        if (itemIdentifier.equals("exceptionTrigger")){
            throw new NoContactWithDatabaseException("No contact with ExternalInventoryDatabase");
        }

        ItemDTO itemDTO = ExternalInventoryDatabase.getItemDTO(itemIdentifier);
        if(itemDTO != null)
            return itemDTO;
        else
            throw new InvalidItemIdentifierException("null return from database. ",itemIdentifier);
    }

    /**
    public method updateDatabasesAndLogSale
    @param receiptDTO
    @return Void

    relays ReceiptDTO to external databases for logging and updates.
     */
    public void updateDatabasesAndLogSale(ReceiptDTO receiptDTO)
            throws NoContactWithDatabaseException{
        throw new NoContactWithDatabaseException("No contact with ExternalAccountingSystem");
    }

}


