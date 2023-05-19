package se.kth.iv1350.mystore.exceptions;

/**
thrown if itemIdentifier does not match
any item in the Database
 */
public class InvalidItemIdentifierException extends Exception {

    private String itemIdentifier;

    public InvalidItemIdentifierException(String message, String itemIdentifier){
        super(message);
        this.itemIdentifier = itemIdentifier;
    }

    public String getItemIdentifier(){
        return itemIdentifier;
    }
}
