package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.controller.Controller;
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
    private Controller contr;

    /*
    public constructor Controller
    @param Controller
    @return View

    creates instance of View
     */
    public View(Controller contr){
        this.contr = contr;
    }
    public void runSimulation(){
        contr.startNewSale();
        ItemRegistrationInfoDTO itemInfo = contr.registerItem("15fifteen",2);
        System.out.println(itemInfo.getAmount() +" " + itemInfo.getItemName() +"\n total: "+ itemInfo.getTotalPriceToPay() +"kr");
        itemInfo = contr.registerItem("15fifteen", 13);
        System.out.println();
        System.out.println(itemInfo.getAmount() +" " + itemInfo.getItemName() +"\n total: "+ itemInfo.getTotalPriceToPay() +"kr");
        itemInfo = contr.registerItem("11eleven");
        System.out.println();
        System.out.println(itemInfo.getAmount() +" " + itemInfo.getItemName() +"\n total: "+ itemInfo.getTotalPriceToPay() +"kr");

        EndSaleDTO endSale = contr.endSale();
        System.out.println();
        System.out.println("Total: " + endSale.getTotalPriceToPayIncludingVAT() +"kr");
        PaymentDTO paymentDTO = new PaymentDTO(500.0);
        ChangeDTO changeDTO = contr.calculateChangeAndUpdateReceipt(paymentDTO);
        System.out.println();
        System.out.println("Total change: " + changeDTO.getChange() +"kr");

        ReceiptDTO receiptDTO = contr.FetchReceiptAndLogSale();
        System.out.println();
        System.out.println(receiptDTO.getReceipt());
    }

}
