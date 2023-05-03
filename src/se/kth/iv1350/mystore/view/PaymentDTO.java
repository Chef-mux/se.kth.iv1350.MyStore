package se.kth.iv1350.mystore.view;

public class PaymentDTO {
    private final double payment;

    public PaymentDTO(double payment){
        this.payment = payment;
    }

    public double getPayment() {
        return payment;
    }
}
