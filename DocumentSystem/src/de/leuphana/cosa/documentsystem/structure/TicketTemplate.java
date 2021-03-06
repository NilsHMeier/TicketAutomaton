package de.leuphana.cosa.documentsystem.structure;

public abstract class TicketTemplate {
    private final String start;
    private final String destination;
    private final Double mileage;
    private final Double price;

    protected TicketTemplate(String start, String destination, Double mileage, Double price) {
        this.start = start;
        this.destination = destination;
        this.mileage = mileage;
        this.price = price;
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
}
