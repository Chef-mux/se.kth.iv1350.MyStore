package se.kth.iv1350.mystore.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.mystore.exceptions.InvalidItemIdentifierException;
import se.kth.iv1350.mystore.exceptions.NoContactWithDatabaseException;

import static org.junit.jupiter.api.Assertions.*;

class DbHandlerTest {
    ItemDTO itemDTO;
    private DbHandler dbHandlerTest;

    @BeforeEach
    void setUp() {
        try {
            dbHandlerTest = new DbHandler();
            itemDTO = ExternalInventoryDatabase.getItemDTO("15fifteen");
        }
        catch (Exception e){

        }
    }

    @AfterEach
    void tearDown() {
        dbHandlerTest = null;
        itemDTO = null;
    }

    // to check if ItemDTO was successfully created we will test a getter in the DTO
    @Test
    void getItemDTO() {
        String testDescription = "Apple";
        String itemDescription = itemDTO.getDescription();
        assertEquals(testDescription, itemDescription, "");
    }

    @Test
    void testThrownInvalidItemIdentifierException(){

        assertThrows(InvalidItemIdentifierException.class, () ->{
            dbHandlerTest.getItemDTO("invalidItemIdentifier");
        }, "Exception was not thrown, the invalid ID did not generate a null result");
    }
    @Test
    void testThrownNoContactWithDatabaseException(){
        assertThrows(NoContactWithDatabaseException.class, () ->{
            dbHandlerTest.getItemDTO("exceptionTrigger");},
                "Exception was not thrown, The ifStatment handling this exception needs redoing");
    }
    @Test
    void testThatExcpetionsContainsWhatIsExcpected(){

    }
}