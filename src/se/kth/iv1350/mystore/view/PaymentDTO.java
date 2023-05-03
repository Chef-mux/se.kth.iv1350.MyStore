package se.kth.iv1350.mystore.view;

public class PaymentDTO {
    private final double payment;

    PaymentDTO(double payment){
        this.payment = payment;
    }

    public double getPayment() {
        return payment;
    }
}
