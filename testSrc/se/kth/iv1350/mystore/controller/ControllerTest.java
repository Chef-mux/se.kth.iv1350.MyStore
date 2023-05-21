package se.kth.iv1350.mystore.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;
import se.kth.iv1350.mystore.model.CashRegister;
import se.kth.iv1350.mystore.model.ItemRegistrationInfoDTO;
import se.kth.iv1350.mystore.model.VAT;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private DbHandler dbHandlerTest;
    private ItemDTO itemDTOTest;
    private Controller contrTest;
    private VAT vatTest;

    @BeforeEach
    void setUp() throws Exception{
        dbHandlerTest = new DbHandler();
        itemDTOTest = dbHandlerTest.getItemDTO("15fifteen");
        vatTest = new VAT();
        contrTest = new Controller(new CashRegister());
    }

    @AfterEach
    void tearDown() {
        dbHandlerTest = null;
        itemDTOTest = null;
        vatTest = null;
        contrTest = null;


    }
    /*
    @Test
    void testRegisterItem() throws Exception {
        contrTest.startNewSale();
        ItemRegistrationInfoDTO infoDTO = contrTest.registerItem("11eleven");
        String nameTest = infoDTO.getItemName();
        String expected = "Harry Potter and the order of the phoenix";
        assertEquals(expected, nameTest, "any number of things ");
    }

    @Test
    void testRegisterItemWithHigherQuantity() throws Exception{
        contrTest.startNewSale();
        ItemRegistrationInfoDTO infoDTO = contrTest.registerItem("11eleven", 14);
        String nameTest = infoDTO.getItemName();
        String expected = "Harry Potter and the order of the phoenix";
        assertEquals(expected, nameTest, "any number of things ");
    }

     */
}

