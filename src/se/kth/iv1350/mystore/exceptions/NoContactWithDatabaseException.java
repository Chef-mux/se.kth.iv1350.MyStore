package se.kth.iv1350.mystore.exceptions;

/**
thrown in case external databases could not be contacted
 */
public class NoContactWithDatabaseException extends RuntimeException {

    /**
     *
     * @param message
     */
    public NoContactWithDatabaseException(String message){
        super(message);
    }
}
