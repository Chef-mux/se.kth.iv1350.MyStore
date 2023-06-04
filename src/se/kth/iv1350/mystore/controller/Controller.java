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
     * public constructor Controller
     * @param cashRegister
     * @throws IOException
     */
    public Controller(CashRegister cashRegister)
    throws IOException {
        this.cashRegister = cashRegister;
        dbHandler = new DbHandler();

    }

    /**
     * Initiates a new Sale class
     */
    public void startNewSale(){
        this.sale = new Sale();
    }

    /**
     *
     * @param itemIdentifier
     * @param quantity
     *
     * InvalidItemIdentifierException is thrown if itemDTO is null which means
     * that there was no item in the database with matching identifier.
     * @throws InvalidItemIdentifierException
     *
     * NoContactWithDatabaseException is thrown if the External inventory database was not accessible
     * @throws NoContactWithDatabaseException
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
     * public method 'registerItem'
     * sets default quantity to 1.
     * @param itemIdentifier
     * InvalidItemIdentifierException is thrown if itemDTO is null which means
     * that there was no item in the database with matching identifier.
     * @throws InvalidItemIdentifierException
     *
     * NoContactWithDatabaseException is thrown if the External inventory database was not accessible
     * @throws NoContactWithDatabaseException
     */
    public void registerItem(String itemIdentifier)
    throws InvalidItemIdentifierException,
            NoContactWithDatabaseException{
        registerItem(itemIdentifier, 1);
    }

    /**
     * public method endSale
     * Signals end of sale and returns EndSaleDTO
     * @return EndSaleDTO
     */
    public EndSaleDTO endSale(){

        EndSaleDTO endsSale = sale.fetchEndOfSaleInfo();
        cashRegister.setTotalPriceToPayIncludingVAT(endsSale);
        return endsSale;
    }

    /**
     * public method calculateChangeAndUpdateReceipt
     * @param payment
     * @return change
     */
    public ChangeDTO calculateChangeAndUpdateReceipt(PaymentDTO payment){

        ChangeDTO change = cashRegister.calculateChange(payment);
        sale.setPaymentAndChangeInReceipt(payment, change);
        return change;
    }

    /**
     * public method FetchReceiptAndLogSale
     * @return receiptDTO
     *
     * NoContactWithDatabaseException is thrown if the External accounting system was not accessible
     * @throws NoContactWithDatabaseException
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
     * @param observer
     */
    public void addObserver(SaleObserver observer){
        sale.addObserver(observer);
    }

    /**
     * adds TotalRevenueObserver
     * @param observer
     */
    public void addObserver(TotalRevenueObserver observer){
        sale.addObserver(observer);
    }
}
