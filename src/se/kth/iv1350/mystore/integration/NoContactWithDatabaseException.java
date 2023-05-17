package se.kth.iv1350.mystore.integration;

/*
thrown in case external databases could not be contacted
 */
public class NoContactWithDatabaseException extends Exception {

    private String nameOfDatabase;

    public NoContactWithDatabaseException(String message, String nameOfDatabase){
        super(message);
        this.nameOfDatabase = nameOfDatabase;
    }

    public String getDatabase() {
        return nameOfDatabase;
    }
}
