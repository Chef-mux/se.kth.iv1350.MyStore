package se.kth.iv1350.mystore.integration;

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
    public ItemDTO getItemDTO(String itemIdentifier){
        return ExternalInventoryDatabase.getItemDTO(itemIdentifier);
    }
}
