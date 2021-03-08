package de.leuphana.cosa.documentsystem.structure;

import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;

import java.time.LocalDate;

public abstract class TicketTemplate {
    private final String start;
    private final String destination;
    private final Double mileage;
    private final Double price;
    private final LocalDate date;

    protected TicketTemplate(Templateable templateable) {
        this.start = templateable.getStart();
        this.destination = templateable.getDestination();
        this.mileage = templateable.getMileage();
        this.price = templateable.getPrice();
        date = templateable.getDate();
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
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
