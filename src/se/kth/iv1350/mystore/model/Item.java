package se.kth.iv1350.mystore.model;
import se.kth.iv1350.mystore.integration.ItemDTO;
class Item {

    private final ItemDTO itemDTO;
    private int quantity;
    Item(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }

    String getItemIdentifier(Item item){
       return itemDTO.getItemIdentifier();
    }

    double getPrice(){
        return itemDTO.getPrice();
    }

    int getType(){
        return itemDTO.getType();
    }

    String getItemDescription(){
        return itemDTO.getDescription();
    }

    int getItemQuantity(){
        return quantity;
    }

    void updateQuantity(int quantity){
        this.quantity += quantity;
    }
}
