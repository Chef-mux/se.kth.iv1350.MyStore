package se.kth.iv1350.mystore.model;


/*
public class ItemRegistrationInfoDTO
Carries information necessary for view after registration of an item
 */
public class ItemRegistrationInfoDTO {

    private final double totalPriceToPay;
    private final double price;
    private final String itemName;
    private final int amount;
    ItemRegistrationInfoDTO(double totalPriceToPay, Receipt receipt, String itemIdentifier){

        this.totalPriceToPay = totalPriceToPay;
        price = receipt.getPrice(itemIdentifier);
        itemName = receipt.getItemDescription(itemIdentifier);
        amount = receipt.getItemQuantity(itemIdentifier);
    }

    public double getTotalPriceToPay() {
        return totalPriceToPay;
    }

    public double getPrice(){
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

}
