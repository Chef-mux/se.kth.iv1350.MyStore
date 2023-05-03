package se.kth.iv1350.mystore.model;


/*
public class ItemRegistrationInfoDTO
Carries information necessary for view after registration of an item
 */
public class ItemRegistrationInfoDTO {

    private final double totalPriceToPay;
    private final String itemName;
    private final int amount;
    ItemRegistrationInfoDTO(double totalPriceToPay, Receipt receipt, String itemIdentifier){

        this.totalPriceToPay = totalPriceToPay;
        this.itemName = receipt.getItemDescription(itemIdentifier);
        this.amount = receipt.getItemQuantity(itemIdentifier);
    }

    public double getTotalPriceToPay() {
        return totalPriceToPay;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

}
