package se.kth.iv1350.mystore.model;

/**
 * a listener interface for recieving infromation about the sale
 */
public interface SaleObserver {
        void newRegisteredItem(ItemRegistrationInfoDTO itemRegistrationInfoDTO);
}
