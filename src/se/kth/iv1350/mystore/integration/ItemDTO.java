package se.kth.iv1350.mystore.integration;

/*
public class ItemDTO
Carries information about an item
 */
public class ItemDTO {
    private final String description;
    private final String itemIdentifier;
    private final double price;
    private final int type;

    ItemDTO(String description, String itemIdentifier, double price, int type){
        this.description = description;
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.type = type;
    }
    public String getDescription(){
        return this.description;
    }
    public String getItemIdentifier(){
        return this.itemIdentifier;
    }
    public double getPrice(){
        return this.price;
    }
    public int getType() {
        return this.type;
    }
}
