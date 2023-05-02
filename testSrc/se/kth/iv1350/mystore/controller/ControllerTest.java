package se.kth.iv1350.mystore.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;
import se.kth.iv1350.mystore.model.Sale;
import se.kth.iv1350.mystore.model.VAT;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private DbHandler dbHandlerTest;
    private ItemDTO itemDTOTest;
    private Sale saleTest;
    private VAT vatTest;
    @BeforeEach
    void setUp() {
        dbHandlerTest = new DbHandler();
        itemDTOTest = dbHandlerTest.getItemDTO("15fifteen");
        vatTest = new VAT();
        saleTest = new Sale(vatTest);
    }

    @AfterEach
    void tearDown() {
        dbHandlerTest = null;
        itemDTOTest =null;
    }

    @Test
    void testControllerAccessToItemDTO(){
        String itemDescription = itemDTOTest.getDescription();
        String expected = "Apple";
        assertEquals(expected, itemDescription, "Controller can not order creation of ItemDTO");
    }

    @Test
    void registerItem() {
    }

    @Test
    void testRegisterItem() {
    }
}