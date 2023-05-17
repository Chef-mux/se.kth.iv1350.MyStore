package se.kth.iv1350.mystore.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceiptDTO {
    private final String receipt;
    private final LocalTime time;
    private final LocalDate date;
    private final ArrayList<Item> registeredItems;
    private final double totalPrice;
    private final double totalVAT;
    private final double sum;
    private final double payment;
    private final double change;

    ReceiptDTO(double totalPrice, double totalVAT, Receipt receipt){


        registeredItems = receipt.getRegisteredItems();
        time = LocalTime.now();
        date = LocalDate.now();
        this.totalPrice = totalPrice;
        this.totalVAT =  totalVAT;
        sum = this.totalPrice + this.totalVAT;
        payment = receipt.getPayment();
        change = receipt.getChange();


        String toBeReceipt = "          MyStore" + "\n" + "       Somestreet 33" + "\n" + "       " +
                            time + "\n" + "       " +  date + "\n" +"\n" +
                            "---------------------------------------------------------" + "\n";
        for (Item item: registeredItems) {
            toBeReceipt += item.getItemQuantity() +" "+ item.getItemDescription() +
                    "     " + item.getPrice() +"kr" +"\n";
        }
        toBeReceipt += "---------------------------------------------------------" + "\n" +"Total price: " + totalPrice
                +"kr"+ "\n" +"Total VAT: " + totalVAT +"kr" + "\n" + "Sum: " + sum +"\n"+
                "Payment received: " + payment +"kr"+ "\n" +
                "Total change: " + change +"kr";

        this.receipt = toBeReceipt;
    }

    public String getReceipt() {
        return receipt;

    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getSum() {
        return sum;
    }

    public double getPayment() {
        return payment;
    }

    public double getChange() {
        return change;
    }
}
