package se.kth.iv1350.mystore.integration;

/*
This class is not part of my system.
this class represents the external inventory database that returns
an ItemDTO when asked. it would at this point have the option to
return a ItemDTO that is NULL, in case the itemIdentifier
does not match any item in the database.
 */
class ExternalInventoryDatabase {

    public static ItemDTO getItemDTO(String itemIdentifier){
        String description;
        String identifier;
        double price;
        int type;
        if( itemIdentifier.equals("15fifteen"));
        {
            description = "Apple";
            identifier = "15fifteen";
            price = 8.00;
            type = 2;
        }
        if(itemIdentifier.equals("11eleven")){
            description = "Harry Potter and the order of the phoenix";
            identifier = "11eleven";
            price = 99.99375;
            type = 1;
        }
        return new ItemDTO(description, identifier, price, type);
    }
}
