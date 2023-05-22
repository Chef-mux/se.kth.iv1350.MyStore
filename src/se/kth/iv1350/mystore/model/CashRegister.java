package se.kth.iv1350.mystore.model;

import se.kth.iv1350.mystore.view.PaymentDTO;


/**
public class CashRegister
Stores balance
Calculates change
 */
public class CashRegister {

    private double balance;
    private double totalPriceToPayIncludingVAT;

    /**
     * public constructor CashRegister
     * @return CashRegister
     *
     * creates instance of CashRegister
    */
    public CashRegister(){
    }

    /**
     *
     * @param endSale
     */
    public void setTotalPriceToPayIncludingVAT(EndSaleDTO endSale) {
        totalPriceToPayIncludingVAT = endSale.getTotalPriceToPayIncludingVAT();
    }

    /**
    public ChangeDTO calculateChange
    @param payment
    @ChangeDTO

    calculates change based on totalPriceToPayIncludingVAT and payment.
    calls to update internal Balance
     */
    public ChangeDTO calculateChange(PaymentDTO payment) {
        double change = (payment.getPayment() - totalPriceToPayIncludingVAT);
        ChangeDTO changeDTO = new ChangeDTO(change);
        updateBalance(payment, changeDTO);
        return changeDTO;
    }

    private void updateBalance (PaymentDTO paymentDTO, ChangeDTO changeDTO){
        double payment = paymentDTO.getPayment();
        double change = changeDTO.getChange();
        balance += (payment-change);
    }
}
