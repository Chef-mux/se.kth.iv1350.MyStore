package se.kth.iv1350.mystore.model;
public class EndSaleDTO {

    private final double TotalPriceToPayIncludingVAT;

    EndSaleDTO(double price, double VAT){
        double total = price + VAT;
        total = total * 100;
        total = Math.round(total);
        TotalPriceToPayIncludingVAT = total / 100;
    }

    public double getTotalPriceToPayIncludingVAT() {
        return TotalPriceToPayIncludingVAT;
    }

}
