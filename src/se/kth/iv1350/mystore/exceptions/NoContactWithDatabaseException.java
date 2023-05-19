package se.kth.iv1350.mystore.exceptions;

/**
thrown in case external databases could not be contacted
 */
public class NoContactWithDatabaseException extends RuntimeException {

    public NoContactWithDatabaseException(String message){
        super(message);
    }
}
