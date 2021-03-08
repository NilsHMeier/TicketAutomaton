package de.leuphana.cosa.pricingsystem.behaviour.service;

import de.leuphana.cosa.pricingsystem.structure.Bill;

import java.time.LocalDate;

public class PaymentReport {

    private final String start;
    private final String destination;
    private final String priceGroup;
    private final Double mileage;
    private final Double price;
    private final LocalDate date;

    public PaymentReport(Bill bill, Payable payable) {
        start = payable.getStart();
        destination = payable.getDestination();
        priceGroup = bill.getPriceGroup().toString();
        mileage = payable.getMileage();
        price = bill.getPrice();
        date = bill.getDate();
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

    public LocalDate getDate() {
        return date;
    }
}
