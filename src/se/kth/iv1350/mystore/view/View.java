package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.controller.Controller;
import se.kth.iv1350.mystore.exceptions.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.exceptions.NoContactWithDatabaseException;
import se.kth.iv1350.mystore.model.*;
import se.kth.iv1350.mystore.util.Logger;
import se.kth.iv1350.mystore.util.TotalRevenueFileOutput;

import java.io.IOException;

/**
 * Public class view
 * Representation of the outside interface. this is where calls to
 * the program originates
 */
public class View {
    private ItemRegistrationDisplay itemRegistrationDisplay;
    private TotalRevenueView totalRevenueView;
    private TotalRevenueFileOutput revenueFileOutput;
    private Logger viewLogger;
    private final Controller contr;

    /**
     * public constructor Controller
     * creates instance of View
     * @param contr
     * @throws IOException
     */
    public View(Controller contr)
    throws IOException {
        viewLogger = new Logger();
        itemRegistrationDisplay = new ItemRegistrationDisplay();
        totalRevenueView = new TotalRevenueView();
        revenueFileOutput = new TotalRevenueFileOutput();
        this.contr = contr;
    }

    public void runSimulation() {
        contr.startNewSale();
        contr.addObserver(itemRegistrationDisplay);
        contr.addObserver(totalRevenueView);
        contr.addObserver(revenueFileOutput);
        try {
            System.out.println("Register 2 Apples");
            contr.registerItem("15fifteen", 2);

           // System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
           // System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException e) {
            System.out.println("The item identifier " + e.getItemIdentifier() +
                        " did not match any items in the database");
               }
        catch (NoContactWithDatabaseException e){
            viewLogger.LogException(e);
            System.out.println("The item could not be registered due to database connectivity issues" );
        }

        try {
            System.out.println("\nregister 13 more Apples");
            contr.registerItem("15fifteen", 13);

            System.out.println();
            // System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
            // System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());


        }
        catch (InvalidItemIdentifierException  e){
            System.out.println("The item identifier "+ e.getItemIdentifier() +
                    " did not match any items in the database");
        }
        catch (NoContactWithDatabaseException e) {
            viewLogger.LogException(e);
            System.out.println("The item could not be registered due to database connectivity issues");
        }

        try {
            System.out.println("Trigger an exception");
            contr.registerItem("invalidIdentifier");

            System.out.println();
            // System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
            // System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException e){
            System.out.println("The item identifier "+ e.getItemIdentifier() +
                    " did not match any items in the database");
        }
        catch (NoContactWithDatabaseException e) {
            viewLogger.LogException(e);
            System.out.println("The item could not be registered due to database connectivity issues");
        }

        try {
            System.out.println("\nRegister a book");
            contr.registerItem("11eleven");

            System.out.println();
           // System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
           // System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException e){
            System.out.println("The item identifier "+ e.getItemIdentifier() +
                    " did not match any items in the database");
        }
        catch (NoContactWithDatabaseException e) {
            viewLogger.LogException(e);
            System.out.println("The item could not be registered due to database connectivity issues");
        }

        EndSaleDTO endSale = contr.endSale();
        System.out.println();
        System.out.println("Ends sale");
        System.out.println("Total: " + endSale.getTotalPriceToPayIncludingVAT() +"kr");
        PaymentDTO paymentDTO = new PaymentDTO(500.0);
        System.out.println(" Pays 500kr");
        ChangeDTO changeDTO = contr.calculateChangeAndUpdateReceipt(paymentDTO);
        System.out.println();
        System.out.println("Total change: " + changeDTO.getChange() +"kr");

        ReceiptDTO receiptDTO = contr.FetchReceiptAndLogSale();
        System.out.println("Prints Receipt:\n");
        System.out.println();
        System.out.println(receiptDTO.getReceipt());
    }

}
