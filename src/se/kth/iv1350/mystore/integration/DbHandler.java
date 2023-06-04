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
     * public constructor DbHandler
     * @throws IOException
     */
    public DbHandler()
    throws IOException{
        dbHandlerLogger = new Logger();
    }

    /**
     * public method getItemDTO
     * calls on external systems to deliver information about an item
     * with matching identifier.
     * @param itemIdentifier
     * @return  ItemDTO
     *
     * InvalidItemIdentifierException is thrown when the database returns null which means
     * that there was no item in the database with matching identifier.
     * @throws InvalidItemIdentifierException
     *
     * NoContactWithDatabaseException is thrown if external inventory database was not accessible
     * @throws NoContactWithDatabaseException
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
     * public method updateDatabasesAndLogSale
     * @param receiptDTO
     *
     * NoContactWithDatabaseException is thrown if external accounting system was not accessible
     * @throws NoContactWithDatabaseException
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
     *
     * NoContactWithDatabaseException is thrown if external inventory database was not accessible
     * @throws NoContactWithDatabaseException
     */
    private void throwNoContactWithDatabaseException(String itemIdentifier)
    throws NoContactWithDatabaseException {
        if (itemIdentifier.equals("exceptionTrigger")) {
            throw new NoContactWithDatabaseException("No contact with ExternalInventoryDatabase");
        }
    }
}


