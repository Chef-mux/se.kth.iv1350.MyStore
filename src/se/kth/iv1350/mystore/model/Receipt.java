package se.kth.iv1350.mystore.model;

import se.kth.iv1350.mystore.view.PaymentDTO;

import java.util.ArrayList;


class Receipt {
    private final ArrayList <Item> registeredItems;
    private PaymentDTO payment;
    private ChangeDTO change;

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
        boolean found = findItem(itemIdentifier) != null;

        return found;
    }

    void updateItemQuantity(String itemIdentifier, int quantity){
        Item item = findItem(itemIdentifier);
        item.updateQuantity(quantity);
    }

    void registerNewItem (Item item){
        registeredItems.add(item);
    }

    void setPaymentAndChangeInReceipt(PaymentDTO payment, ChangeDTO change) {
        this.payment = payment;
        this.change = change;
    }

    ReceiptDTO getReceiptInfo(double totalPrice, double totalVAT){
        return new ReceiptDTO(totalPrice, totalVAT, this );
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

    ArrayList<Item> getRegisteredItems() {
        return registeredItems;
    }
    double getPayment(){
        return payment.getPayment();
    }

    double getChange(){
        return change.getChange();
    }
}
