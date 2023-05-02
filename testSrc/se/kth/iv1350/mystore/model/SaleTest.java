package se.kth.iv1350.mystore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    Receipt receiptTest;
    DbHandler dbHandlerTest;
    ItemDTO itemDTOTest;
    Sale saleTest;
    @BeforeEach
    void setUp() {
        receiptTest = new Receipt();
        dbHandlerTest = new DbHandler();
        itemDTOTest = dbHandlerTest.getItemDTO("15fifteen");
        saleTest = new Sale(new VAT());
    }

    @AfterEach
    void tearDown() {
        saleTest = null;
        itemDTOTest = null;
        dbHandlerTest = null;
        receiptTest = null;
    }

    @Test
    void itemAlreadyRegistered() {
    }

    @Test
    void updateItemQuantity() {
    }

    @Test
    void registerNewItem() {
    }

    @Test
    void createItemRegistrationInfoDTO() {
    }
}