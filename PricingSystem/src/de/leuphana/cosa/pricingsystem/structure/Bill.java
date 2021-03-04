package de.leuphana.cosa.pricingsystem.structure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bill {
    private final PriceGroup priceGroup;
    private Double price;

    public Bill(PriceGroup priceGroup, Double mileage) {
        this.priceGroup = priceGroup;
        calculatePrice(mileage);
    }

    private void calculatePrice(Double mileage) {
        price = mileage * 0.03;
        switch (priceGroup) {
            case CHEAPER -> {
                price = price * 0.75;
            }
            case BARGAIN -> {
                price = price * 0.5;
            }
        }
        // Make sure price has two decimal places
        price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Double getPrice() {
        return price;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    @Override
    public String toString() {
        return "PriceGroup " + priceGroup.name() + " with total price " + price;
    }
}
