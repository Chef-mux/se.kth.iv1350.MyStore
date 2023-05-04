package se.kth.iv1350.mystore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    private DbHandler dbHandlerTest;
    private ItemDTO itemDTOTest;
    private Item itemTest;
    private Receipt receiptTest;

    @BeforeEach
    void setUp() {
        dbHandlerTest = new DbHandler();
        itemDTOTest = dbHandlerTest.getItemDTO("15fifteen");
        itemTest = new Item(itemDTOTest, 3);
        receiptTest = new Receipt();
    }

    @AfterEach
    void tearDown() {
        receiptTest = null;
        itemTest = null;
        itemDTOTest = null;
        dbHandlerTest = null;
    }

    @Test
    void registerNewItem() {
        receiptTest.registerNewItem(itemTest);
        String foundItemDescription = receiptTest.getItemDescription("15fifteen");
        String expected = "Apple";
        int foundItemQuantity = receiptTest.getItemQuantity("15fifteen");
        int expectedQuantity = 3;
        assertEquals(expectedQuantity,foundItemQuantity, " Quantity did not register properly");
        assertEquals(expected, foundItemDescription, "The item did not Register properly");
    }


    @Test
    void registerMultipleItems(){
        ItemDTO itemDTOTest2 = dbHandlerTest.getItemDTO("11eleven");
        Item itemTest2 = new Item(itemDTOTest2, 1);
        receiptTest.registerNewItem(itemTest2);
        receiptTest.registerNewItem(itemTest);
        String description1 = receiptTest.getItemDescription("15fifteen");
        String description2 = receiptTest.getItemDescription("11eleven");
        String expected1 = "Apple";

        assertEquals(expected1, description1, "wrong item was found");
        assertNotEquals(expected1, description2, "Wrong item was found");
    }

    @Test
    void itemAlreadyRegistered() {
        receiptTest.registerNewItem(itemTest);
        boolean expected = true;
        boolean foundItem = receiptTest.itemAlreadyRegistered("15fifteen");
        assertEquals(expected, foundItem, "Registered item could not be located");
    }

    @Test
    void updateItemQuantity() {
        receiptTest.registerNewItem(itemTest);
        receiptTest.updateItemQuantity("15fifteen", 4);
        int expected = 7;
        int foundQuantity = receiptTest.getItemQuantity("15fifteen");
        assertEquals(expected, foundQuantity, "Quantity is calculated falsly");
    }
}
