package se.kth.iv1350.mystore.model;

import se.kth.iv1350.mystore.integration.ItemDTO;

/*
public class Sale
Carries information about price and VAT
calculates running total

 */
public class Sale {

    private Receipt receipt;
    private VAT vat;
    private double totalPrice;
    private double totalVAT;
    private double runningTotalIncludingVAT = totalPrice+totalVAT;
    public Sale(VAT vat){
        this.vat = vat;
        this.receipt = new Receipt();
    }

    /*
    public method itemAlreadyRegistered
    @param String itemIdentifier
    @return Boolean
    works as a question. Is there an Item with this identity already registered?
     */
    public boolean itemAlreadyRegistered (String itemIdentifier){
       return receipt.itemAlreadyRegistered(itemIdentifier);
    }

    /*
    public method updateItemQuantity
    @param String itemIdentifier
    @return void
    calls for update on already registered item with a certain quantity
    method also calls to update totalPrice and totalVAT
     */
    public void updateItemQuantity(String itemIdentifier, int quantity){
        receipt.updateItemQuantity(itemIdentifier, quantity);
        updateTotalPriceAndTotalVAT(itemIdentifier, quantity);
    }

    /*
    public method registerNewItem
    @param ItemDTO
    @param int quantity
    @return void
    Initiates a new item to be registered based on parameters.
    also calls for update of totalPrice and totalVAT
     */
    public  void registerNewItem(ItemDTO itemDTO, int quantity){
        Item item = createItem(itemDTO, quantity);
        receipt.registerNewItem(item);
        String itemIdentifier = itemDTO.getItemIdentifier();
        updateTotalPriceAndTotalVAT(itemIdentifier,quantity);

    }

    /*public method createItemRegistrationInfoDTO
    @param String itemIdentifier
    @return ItemRegistrationInfoDTO
    relays information about Item registration.
     */
    public ItemRegistrationInfoDTO createItemRegistrationInfoDTO(String itemIdentifier){
        return new ItemRegistrationInfoDTO(runningTotalIncludingVAT, receipt, itemIdentifier);
    }

    private Item createItem(ItemDTO itemDTO, int quantity){
        return new Item (itemDTO, quantity);
    }

    private void updateTotalPriceAndTotalVAT(String itemIdentifier, int quantity){

        int typeOfItem = receipt.getType(itemIdentifier);
        double price = receipt.getPrice(itemIdentifier);
        totalPrice = (totalPrice + ((double)quantity * price));

        double vatMultiplier = this.vat.getVat(typeOfItem);
        double latestItemVAT = price * vatMultiplier;
        totalVAT = (totalVAT + (latestItemVAT * quantity));

    }
}
