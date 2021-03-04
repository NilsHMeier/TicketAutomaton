package de.leuphana.cosa.pricingsystem.behaviour;

import de.leuphana.cosa.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.cosa.pricingsystem.structure.Bill;
import de.leuphana.cosa.pricingsystem.structure.PriceGroup;

public class PricingCLITest {

    static PricingCommandService pricingSystem;

    public static void main(String[] args) {
        pricingSystem = new PricingSystemImpl();

        PriceGroup priceGroup = pricingSystem.selectPriceGroup();
        Bill bill = pricingSystem.createBill(priceGroup, 100.0);

        System.out.println(bill.toString());
    }
}
