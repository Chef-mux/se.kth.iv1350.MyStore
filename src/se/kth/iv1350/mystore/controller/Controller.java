package se.kth.iv1350.mystore.controller;

import se.kth.iv1350.mystore.model.CashRegister;
import se.kth.iv1350.mystore.model.ItemRegistrationInfoDTO;
import se.kth.iv1350.mystore.model.Sale;
import se.kth.iv1350.mystore.model.VAT;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;

/*
public class Controller
Handles calls between layers and keeps them encapsulated from eachother.
Directs calls and relays data.
 */
public class Controller {
    private final VAT vat;
    private Sale sale;
    private DbHandler dbHandler;

    /*
    public constructor Controller
    @param CashRegister
    @return Controller

    Creates a Controller instance
     */
    public Controller(CashRegister cashRegister){
       dbHandler = new DbHandler();
       vat = new VAT();
    }

    /*
    Initiates a new Sale class
    @param none
    @return none
     */
    public void startNewSale(){
        this.sale = new Sale(vat);
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
}
