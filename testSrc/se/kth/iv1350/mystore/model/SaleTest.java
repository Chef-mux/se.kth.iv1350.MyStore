package se.kth.iv1350.mystore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    DbHandler dbHandlerTest;
    ItemDTO itemDTOTest;
    Sale saleTest;
    @BeforeEach
    void setUp() throws Exception {
        dbHandlerTest = new DbHandler();
        itemDTOTest = dbHandlerTest.getItemDTO("15fifteen");
        saleTest = new Sale();
    }

    @AfterEach
    void tearDown() {
        saleTest = null;
        itemDTOTest = null;
        dbHandlerTest = null;
    }

    @Test
    void createAndRegisterNewItem() throws Exception {
        ItemDTO itemDTOTest2 = dbHandlerTest.getItemDTO("11eleven");

        saleTest.registerNewItem(itemDTOTest, 2);
        saleTest.registerNewItem(itemDTOTest2, 5);

        boolean found = saleTest.itemAlreadyRegistered("15fifteen");
        boolean excpected = true;

        assertEquals(excpected, found, "Item was not created correctly");
    }

    @Test
    void createItemRegistrationInfoDTO(){
        saleTest.registerNewItem(itemDTOTest, 23);
        ItemRegistrationInfoDTO DTOTest = saleTest.createItemRegistrationInfoDTO("15fifteen");
        String name = DTOTest.getItemName();
        String excpectedName = "Apple";
        double runningTotal = DTOTest.getTotalPriceToPay();
        runningTotal = Math.round(runningTotal*100);
        double excpectedTotal = 206.08;
        excpectedTotal = Math.round(excpectedTotal*100);

        assertEquals(excpectedName, name, " DTO not created with correct information");
        assertEquals(excpectedTotal, runningTotal, "Value calulated incorrectly");
    }
}