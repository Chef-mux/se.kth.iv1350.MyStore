package se.kth.iv1350.mystore.controller;

import se.kth.iv1350.mystore.model.*;
import se.kth.iv1350.mystore.view.PaymentDTO;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;


/*
public class Controller
Handles calls between layers and keeps them encapsulated from eachother.
Directs calls from and relays data to View.
 */
public class Controller {
    private Sale sale;
    private final DbHandler dbHandler;
    private final CashRegister cashRegister;

    /*
    public constructor Controller
    @param CashRegister
    @return Controller

    Creates a Controller instance
     */
    public Controller(CashRegister cashRegister){
        this.cashRegister = cashRegister;
        dbHandler = new DbHandler();
    }

    /*
    Initiates a new Sale class
    @param none
    @return none
     */
    public void startNewSale(){
        this.sale = new Sale();
    }

    /*
    public method 'registerItem'
    @param String itemIdentifier
    @param int quantity
    @return instance of ItemRegistrationInfoDTO
     */
    public ItemRegistrationInfoDTO registerItem(String itemIdentifier, int quantity){
       boolean found = sale.itemAlreadyRegistered(itemIdentifier);
       if (found){
         sale.updateItemQuantity(itemIdentifier, quantity);
       }
       else {
           ItemDTO itemDTO =  dbHandler.getItemDTO(itemIdentifier);

           if(itemDTO != null) {//todo this should not be an if statement but an exception
               sale.registerNewItem(itemDTO, quantity);
           }
       }
        return sale.createItemRegistrationInfoDTO(itemIdentifier);
    }

    /*
    public method 'registerItem'
    @param String itemIdentifier
    @return instance of ItemRegistrationInfoDTO

    sets default quantity to 1.
     */
    public ItemRegistrationInfoDTO registerItem(String itemIdentifier){
        return registerItem(itemIdentifier, 1);
    }

    /*
    public method endSale
    @param void
    @return EndSaleDTO

    Signals end of sale and returns EndSaleDTO
     */
    public EndSaleDTO endSale(){

        EndSaleDTO endsSale = sale.fetchEndOfSaleInfo();
        cashRegister.setTotalPriceToPayIncludingVAT(endsSale);
        return endsSale;
    }

    /*
    public method calculateChangeAndUpdateReceipt
    @param PaymentDTO
    @return ChangeDTO

    Relays PaymentDTO to CashRegister for calculating change.
    Relays ChangeDTO to Sale
    Returns ChangeDTO
     */
    public ChangeDTO calculateChangeAndUpdateReceipt(PaymentDTO payment){

        ChangeDTO change = cashRegister.calculateChange(payment);
        sale.setPaymentAndChangeInReceipt(payment, change);
        return change;
    }

    /*
    public method FetchReceiptAndLogSale
    @param viod
    @return ReceiptDTO

    Signals time to print Receipt.
    Relays ReceiptDTO to DbHandler.
    Returns ReceiptDTO.
     */
    public ReceiptDTO FetchReceiptAndLogSale(){
        ReceiptDTO receiptDTO = sale.getReceiptInfo();
        dbHandler.updateDatabasesAndLogSale(receiptDTO);
        return receiptDTO;
    }

}
