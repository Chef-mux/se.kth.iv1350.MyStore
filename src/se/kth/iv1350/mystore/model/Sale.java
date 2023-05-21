package se.kth.iv1350.mystore.model;

import se.kth.iv1350.mystore.integration.ItemDTO;
import se.kth.iv1350.mystore.view.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

/**
public class Sale
Carries information about price and VAT
calculates running total

 */
public class Sale {

    private final Receipt receipt;
    private final VAT vat;
    private double totalPrice;
    private double totalVAT;
    private List <SaleObserver> listOfSaleObservers;
    private List <TotalRevenueObserver> listOfRevenueObservers;
    public Sale(){
        this.vat = new VAT();
        this.receipt = new Receipt();
        listOfSaleObservers = new ArrayList<>();
        listOfRevenueObservers = new ArrayList<>();
    }

    /**
    public method itemAlreadyRegistered
    @param itemIdentifier itemIdentifier
    @return Boolean
    works as a question. Is there an Item with this identity already registered?
     */
    public boolean itemAlreadyRegistered (String itemIdentifier){
       return receipt.itemAlreadyRegistered(itemIdentifier);
    }

    /**
    public method updateItemQuantity
     @param itemIdentifier
     @param quantity
     @return void
    calls for update on already registered item with a certain quantity
    method also calls to update totalPrice and totalVAT
     */
    public void updateItemQuantity(String itemIdentifier, int quantity){
        receipt.updateItemQuantity(itemIdentifier, quantity);
        updateTotalPriceAndTotalVAT(itemIdentifier, quantity);
        createItemRegistrationInfoDTO(itemIdentifier);
    }

    /**
    public method registerNewItem
    @param itemDTO
    @param quantity quantity
    @return void
    Initiates a new item to be registered based on parameters.
    also calls for update of totalPrice and totalVAT
     */
    public  void registerNewItem(ItemDTO itemDTO, int quantity){
        Item item = createItem(itemDTO, quantity);
        receipt.registerNewItem(item);
        String itemIdentifier = itemDTO.getItemIdentifier();
        updateTotalPriceAndTotalVAT(itemIdentifier,quantity);
        createItemRegistrationInfoDTO(itemIdentifier);

    }

    /**
    public method createItemRegistrationInfoDTO
    @param itemIdentifier itemIdentifier
    @return ItemRegistrationInfoDTO
    relays information about Item registration.
     */
    private void createItemRegistrationInfoDTO(String itemIdentifier){
        double runningTotalIncludingVAT = (totalPrice + totalVAT);
        ItemRegistrationInfoDTO irid = new ItemRegistrationInfoDTO(runningTotalIncludingVAT, receipt, itemIdentifier);
        notifyObserver(irid);
    }

    /**
    public method fetchEndOfSaleInfo
     @return Creates and returns EndSaleDTO
     */
    public EndSaleDTO fetchEndOfSaleInfo (){
        return new EndSaleDTO(totalPrice, totalVAT);
    }

    /**
     * Sets payment and change variables int the receipt
     *
     * @param payment
     * @param change
     */
    public void setPaymentAndChangeInReceipt(PaymentDTO payment, ChangeDTO change){
        receipt.setPaymentAndChangeInReceipt(payment, change);
    }

    /**
     * returns receiptDTO for caller
     *
     * @return ReceiptDTO
     */
    public ReceiptDTO getReceiptInfo(){
        for(TotalRevenueObserver observer: listOfRevenueObservers){
            observer.logRevenue(totalPrice);
        }
        return receipt.getReceiptInfo(totalPrice, totalVAT);
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

    /**
     * Add an observer.
     * @param observer
     */
    public void addObserver(SaleObserver observer){
        listOfSaleObservers.add(observer);
    }
    public void addObserver(TotalRevenueObserver observer){
        listOfRevenueObservers.add(observer);
    }
    private void notifyObserver(ItemRegistrationInfoDTO itemRegistrationInfoDTO){
        for (SaleObserver observer: listOfSaleObservers){
            observer.newRegisteredItem(itemRegistrationInfoDTO);
        }
    }
}
