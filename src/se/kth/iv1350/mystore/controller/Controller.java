package se.kth.iv1350.mystore.controller;

import se.kth.iv1350.mystore.exceptions.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.exceptions.NoContactWithDatabaseException;
import se.kth.iv1350.mystore.model.*;
import se.kth.iv1350.mystore.view.PaymentDTO;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;

import java.io.IOException;


/**
 * public class Controller
 * Handles calls between layers and keeps them encapsulated from eachother.
 * Directs calls from and relays data to View.
 */
public class Controller {
    private Sale sale;
    private final DbHandler dbHandler;
    private final CashRegister cashRegister;

    /**
    public constructor Controller
    @param cashRegister
    @return Controller

    Creates a Controller instance
     */
    public Controller(CashRegister cashRegister)
    throws IOException {
        this.cashRegister = cashRegister;
        dbHandler = new DbHandler();

    }

    /**
    Initiates a new Sale class
     @return none
     */
    public void startNewSale(){
        this.sale = new Sale();
    }

    /**
    public method 'registerItem'
    @param itemIdentifier itemIdentifier
    @param quantity quantity
    @return instance of ItemRegistrationInfoDTO
     */
    public void registerItem(String itemIdentifier, int quantity)
    throws InvalidItemIdentifierException,
            NoContactWithDatabaseException{
       boolean found = sale.itemAlreadyRegistered(itemIdentifier);
       if (found){
         sale.updateItemQuantity(itemIdentifier, quantity);
       }
       else {
           ItemDTO itemDTO =  dbHandler.getItemDTO(itemIdentifier);
           sale.registerNewItem(itemDTO, quantity);
       }
    }

    /**
    public method 'registerItem'
    @param itemIdentifier itemIdentifier
    @return instance of ItemRegistrationInfoDTO

    sets default quantity to 1.
     */
    public void registerItem(String itemIdentifier)
    throws InvalidItemIdentifierException,
            NoContactWithDatabaseException{
        registerItem(itemIdentifier, 1);
    }

    /**
    public method endSale
    @return EndSaleDTO

    Signals end of sale and returns EndSaleDTO
     */
    public EndSaleDTO endSale(){

        EndSaleDTO endsSale = sale.fetchEndOfSaleInfo();
        cashRegister.setTotalPriceToPayIncludingVAT(endsSale);
        return endsSale;
    }

    /**
    public method calculateChangeAndUpdateReceipt
    @param payment
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

    /**
    public method FetchReceiptAndLogSale
    @return ReceiptDTO

    Signals time to print Receipt.
    Relays ReceiptDTO to DbHandler.
    Returns ReceiptDTO.
     */
    public ReceiptDTO FetchReceiptAndLogSale()
    throws NoContactWithDatabaseException{
        ReceiptDTO receiptDTO = sale.getReceiptInfo();
            dbHandler.updateDatabasesAndLogSale(receiptDTO);

        sale = null;
        return receiptDTO;
    }

    /**
     * adds SaleObserver
     *
     * @param observer
     */
    public void addObserver(SaleObserver observer){
        sale.addObserver(observer);
    }
    public void addObserver(TotalRevenueObserver observer){
        sale.addObserver(observer);
    }
}
