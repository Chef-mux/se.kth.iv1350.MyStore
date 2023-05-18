package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.controller.Controller;
import se.kth.iv1350.mystore.integration.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.integration.NoContactWithDatabaseException;
import se.kth.iv1350.mystore.model.ChangeDTO;
import se.kth.iv1350.mystore.model.EndSaleDTO;
import se.kth.iv1350.mystore.model.ItemRegistrationInfoDTO;
import se.kth.iv1350.mystore.model.ReceiptDTO;

/*
Public class view
Representation of the outside interface. this is where calls to
the program originates
 */
public class View {
    private final Controller contr;

    /*
    public constructor Controller
    @param Controller
    @return View

    creates instance of View
     */
    public View(Controller contr){
        this.contr = contr;
    }
    public void runSimulation() {
        contr.startNewSale();
        ItemRegistrationInfoDTO itemInfo;
        try {
            itemInfo = contr.registerItem("15fifteen", 2);
            System.out.println("Register 2 Apples");
            System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
            System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException e) {
            System.out.println("The item identifier " + e.getItemIdentifier() +
                        " did not match any items in the database");
               }

        try {
            itemInfo = contr.registerItem("15fifteen", 13);
            System.out.println("register 13 more Apples");
            System.out.println();
            System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
            System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException  e){
            System.out.println("The item identifier "+ e.getItemIdentifier() +
                    " did not match any items in the database");
        }

        try {
            itemInfo = contr.registerItem("11eleven");
            System.out.println("Register a book");
            System.out.println();
            System.out.println(itemInfo.getAmount() + " " + itemInfo.getItemName() + "  á ");
            System.out.printf("%.2f kr" + "\nRunning total: %.2f\n", itemInfo.getPrice(), itemInfo.getTotalPriceToPay());
        }
        catch (InvalidItemIdentifierException e){
            System.out.println("The item identifier "+ e.getItemIdentifier() +
                    " did not match any items in the database");
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
