package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.model.ItemRegistrationInfoDTO;
import se.kth.iv1350.mystore.model.SaleObserver;

/**
 * shows information about the newly registered item and relevant info about the sale.
 */
class ItemRegistrationDisplay implements SaleObserver {

    private ItemRegistrationInfoDTO itemRegistrationInfoDTO;

    /**
     * creates new instance of the class.
     */
    ItemRegistrationDisplay(){

    }

    /**
     * updates current state of this class
     * implementation of SaleObserver interface
     *
     * @param itemRegistrationInfoDTO
     */
    @Override
    public void newRegisteredItem(ItemRegistrationInfoDTO itemRegistrationInfoDTO) {
        this.itemRegistrationInfoDTO = itemRegistrationInfoDTO;
        printLatestDTO();
    }

    ItemRegistrationInfoDTO getItemRegistrationInfoDTO(){
        return itemRegistrationInfoDTO;
    }

    private void printLatestDTO(){

        System.out.println(itemRegistrationInfoDTO.getAmount() + " " + itemRegistrationInfoDTO.getItemName() + "  á ");
        System.out.printf("%.2f kr" + "\nRunning total: %.2f\n",
                itemRegistrationInfoDTO.getPrice(), itemRegistrationInfoDTO.getTotalPriceToPay());
    }
}
