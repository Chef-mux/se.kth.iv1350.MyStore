// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
package se.kth.iv1350.mystore.startup;

import se.kth.iv1350.mystore.controller.Controller;
import se.kth.iv1350.mystore.model.CashRegister;
import se.kth.iv1350.mystore.model.ItemRegistrationInfoDTO;
import se.kth.iv1350.mystore.util.TotalRevenueFileOutput;
import se.kth.iv1350.mystore.view.View;

import java.io.IOException;

/**
public class Main
Starts up the program
 */
public class Main {

    /**
    public static method main
    @param args
    @return void
    calls constructor for;
    CashRegister
    Controller
    View
    TotalRevenueFileOutput
     */
    public static void main(String[] args)
    throws IOException {
       CashRegister cashRegister = new CashRegister();
       Controller contr = new Controller(cashRegister);
       View view = new View(contr);
       view.runSimulation();
  }
}