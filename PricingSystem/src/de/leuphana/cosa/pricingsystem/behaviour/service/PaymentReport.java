package de.leuphana.cosa.pricingsystem.behaviour.service;

import de.leuphana.cosa.pricingsystem.behaviour.service.Payable;
import de.leuphana.cosa.pricingsystem.structure.Bill;

public class PaymentReport {

    private final String start;
    private final String destination;
    private final String priceGroup;
    private final Double mileage;
    private final Double price;

    public PaymentReport(Bill bill, Payable payable) {
        start = payable.getStart();
        destination = payable.getDestination();
        priceGroup = bill.getPriceGroup().toString();
        mileage = payable.getMileage();
        price = bill.getPrice();
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public Double getMileage() {
        return mileage;
    }

    public Double getPrice() {
        return price;
    }
}
