package se.kth.iv1350.mystore.integration;

import se.kth.iv1350.mystore.exceptions.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.exceptions.NoContactWithDatabaseException;
import se.kth.iv1350.mystore.model.ReceiptDTO;
import se.kth.iv1350.mystore.util.Logger;

import java.io.IOException;

/**
public class DbHandler
handles calls to external databases
 */
public class DbHandler {
    private Logger dbHandlerLogger;

    /**
    public constructor DbHandler
     @return DbHandler

    creates instance of DbHandler
    */
    public DbHandler()
    throws IOException{
        dbHandlerLogger = new Logger();
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

        throwNoContactWithDatabaseException(itemIdentifier);

        ItemDTO itemDTO = ExternalInventoryDatabase.getItemDTO(itemIdentifier);
        if(itemDTO != null)
            return itemDTO;
        else
            throw new InvalidItemIdentifierException("null return from database. ",itemIdentifier);
    }

    /**
    public method updateDatabasesAndLogSale
    @param receiptDTO
     */
    public void updateDatabasesAndLogSale(ReceiptDTO receiptDTO)
            throws NoContactWithDatabaseException{
        try {
            throw new NoContactWithDatabaseException("No contact with ExternalAccountingSystem");
        }
        catch (NoContactWithDatabaseException e){
            dbHandlerLogger.LogException(e);
        }
    }

    /**
     *
     * @param itemIdentifier
     */
    private void throwNoContactWithDatabaseException(String itemIdentifier) {
        if (itemIdentifier.equals("exceptionTrigger")) {
            throw new NoContactWithDatabaseException("No contact with ExternalInventoryDatabase");
        }
    }
}


