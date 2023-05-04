package se.kth.iv1350.mystore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.mystore.integration.DbHandler;
class ItemTest {
    Item testitem;
    DbHandler testdbHandler;

    @BeforeEach
    void setUp(){
    testdbHandler = new DbHandler();
    testitem = new Item(testdbHandler.getItemDTO("11eleven"),1);
    }

    @AfterEach
    void tearDown() {
        testitem = null;
        testdbHandler = null;
    }

    @Test
    void testUpdateQuantity(){
        testitem.updateQuantity(4);
        int expectedQuantity = 5;
        int foundQuantity = testitem.getItemQuantity();

        assertEquals(expectedQuantity, foundQuantity, "Quantity is not calculated correctly");
    }
}