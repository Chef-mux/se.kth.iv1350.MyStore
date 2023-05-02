package se.kth.iv1350.mystore.model;

import java.util.ArrayList;


class Receipt {
    private ArrayList <Item> registeredItems;

    /*
    constructor for Receipt
    when a Receipt is constructed, an ArrayList is
    initiated to be filled later.
     */
    Receipt(){
        registeredItems = new ArrayList<Item>();
    }

    private Item findItem (String itemIdentifier){
        for(Item item : registeredItems){
            if (item.getItemIdentifier(item).equals(itemIdentifier)){
                return item;
            }
        }
        return null; // todo exception here
    }
    boolean itemAlreadyRegistered(String itemIdentifier){
        boolean found = false;

        if (findItem(itemIdentifier) != null){
            found = true;
        }
        return found;
    }

    void updateItemQuantity(String itemIdentifier, int quantity){
        Item item = findItem(itemIdentifier);
        item.updateQuantity(quantity);
    }

    void registerNewItem (Item item){
        registeredItems.add(item);
    }

    double getPrice(String itemIdentifier){
        Item item = findItem(itemIdentifier);
        return item.getPrice();
    }

    int getType(String itemIdentifier){
        Item item = findItem(itemIdentifier);
        return item.getType();
    }

    String getItemDescription(String itemIdentifier){
        Item item = findItem(itemIdentifier);
        return item.getItemDescription();
    }

    int getItemQuantity(String itemIdentifier){
        Item item = findItem(itemIdentifier);
        return item.getItemQuantity();
    }
}
