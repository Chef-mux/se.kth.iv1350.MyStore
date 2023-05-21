package se.kth.iv1350.mystore.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class Logger {
    private static final String LOG_FILE_NAME = "myStore-log.txt";
    private PrintWriter printWriter;

    public Logger()
    throws IOException {
        printWriter = new PrintWriter(new FileWriter(LOG_FILE_NAME),true);

    }

    /**
     * writes log entry that describes the thrown exception.
     *
     * @param exception The exception that was thrown.
     */
    public void LogException (Exception exception){
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(getTime());
        messageBuilder.append(", Exception was thrown: ");
        messageBuilder.append(exception.getMessage());
        printWriter.println(messageBuilder);
        exception.printStackTrace(printWriter);
    }

    private String getTime(){
       LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return time.format(formatter);
    }

}
