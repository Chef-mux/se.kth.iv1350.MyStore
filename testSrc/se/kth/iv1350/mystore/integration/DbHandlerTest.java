package se.kth.iv1350.mystore.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbHandlerTest {
    ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        try {
            itemDTO = ExternalInventoryDatabase.getItemDTO("15fifteen");
        }
        catch (Exception e){

        }
    }

    @AfterEach
    void tearDown() {
        itemDTO = null;
    }

    // to check if ItemDTO was successfully created we will test a getter in the DTO
    @Test
    void getItemDTO() {
        String testDescription = "Apple";
        String itemDescription = itemDTO.getDescription();
        assertEquals(testDescription, itemDescription, "");
    }
}