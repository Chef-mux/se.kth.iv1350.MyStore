package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.model.TotalRevenueObserver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class TotalRevenueView implements TotalRevenueObserver {
    private double totalRevenue;

    @Override
    public void logRevenue(double revenue) {
        totalRevenue += revenue;
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(getTime());
        logBuilder.append(", New sale was completed, income: ");
        logBuilder.append(revenue);
        logBuilder.append("\n");
        logBuilder.append("Total revenue: ");
        logBuilder.append(totalRevenue);
        System.out.println(logBuilder);
    }
    private String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return time.format(formatter);
    }
}
