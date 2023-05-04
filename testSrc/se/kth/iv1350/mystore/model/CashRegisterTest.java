package se.kth.iv1350.mystore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.mystore.integration.DbHandler;
import se.kth.iv1350.mystore.integration.ItemDTO;
import se.kth.iv1350.mystore.view.PaymentDTO;

class CashRegisterTest {

    private DbHandler testDbHandler;
    private Sale testSale;
    private ItemDTO apple;
    private EndSaleDTO testEndSaleDTO;
    private CashRegister testCashRegister;

    @BeforeEach
    void setUp() {
        testDbHandler = new DbHandler();
        testSale = new Sale();
        apple = testDbHandler.getItemDTO("15fifteen");
        testSale.registerNewItem(apple, 2);
        testEndSaleDTO = testSale.fetchEndOfSaleInfo();
        testCashRegister = new CashRegister();
    }

    @AfterEach
    void tearDown() {
        apple = null;
        testSale = null;
        testDbHandler = null;
        testEndSaleDTO = null;
        testCashRegister = null;
    }

    @Test
    void calculateChange() {
        testCashRegister.setTotalPriceToPayIncludingVAT(testEndSaleDTO);
        PaymentDTO paymentDTO = new PaymentDTO(100);
        ChangeDTO changeDTO = testCashRegister.calculateChange(paymentDTO);
        double change = changeDTO.getChange();
        double price = (apple.getPrice()*2);
        price += (price * 0.12);
        double expected = 100 - price;
        assertEquals(expected, change, "Calculation of change was handled incorrectly");
    }
}