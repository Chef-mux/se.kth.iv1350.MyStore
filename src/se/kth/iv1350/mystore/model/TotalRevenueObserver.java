package se.kth.iv1350.mystore.model;

/**
 * a listener interface for recieving infromation about revenue
 */
public interface TotalRevenueObserver {
    void logRevenue(double revenue);
}
