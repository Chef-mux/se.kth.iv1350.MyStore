package se.kth.iv1350.mystore.util;

import se.kth.iv1350.mystore.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class taht observes revenue and prints to txt file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    PrintWriter printWriter;
    private static final String LOG_FILE_NAME = "revenueOfMyStore-log.txt";
    private double totalRevenue;

    /**
     *
     * @throws IOException
     */
    public TotalRevenueFileOutput()
    throws IOException {
        printWriter = new PrintWriter(new FileWriter(LOG_FILE_NAME),true);
    }

    /**
     *
     * @param revenue
     */
    @Override
    public void logRevenue (double revenue) {
        totalRevenue += revenue;
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(getTime());
        logBuilder.append(", New sale was completed, income: ");
        logBuilder.append(revenue);
        logBuilder.append("\n");
        logBuilder.append("Total revenue: ");
        logBuilder.append(totalRevenue);
        printWriter.println(logBuilder);
    }

    private String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return time.format(formatter);
    }
}
