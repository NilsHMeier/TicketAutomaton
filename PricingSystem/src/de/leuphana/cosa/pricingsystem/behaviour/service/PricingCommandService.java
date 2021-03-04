package de.leuphana.cosa.pricingsystem.behaviour.service;

import de.leuphana.cosa.pricingsystem.structure.Bill;
import de.leuphana.cosa.pricingsystem.structure.PriceGroup;

public interface PricingCommandService {
    PriceGroup selectPriceGroup();
    Bill createBill(PriceGroup priceGroup, Double mileage);
}
