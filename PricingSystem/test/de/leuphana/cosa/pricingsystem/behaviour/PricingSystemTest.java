package de.leuphana.cosa.pricingsystem.behaviour;

import de.leuphana.cosa.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.cosa.pricingsystem.structure.Bill;
import de.leuphana.cosa.pricingsystem.structure.PriceGroup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PricingSystemTest {

    private static PricingCommandService pricingSystem;

    @BeforeAll
    static void setUpBeforeClass() {
        pricingSystem = new PricingSystemImpl();
    }

    @AfterAll
    static void tearDownAfterClass() {
        pricingSystem = null;
    }

    @Test
    void canBillBeCreated() {
        Bill bill = pricingSystem.createBill(PriceGroup.NORMAL, 100.0);
        Assertions.assertEquals(bill.getPrice(), 3.0);
    }
}