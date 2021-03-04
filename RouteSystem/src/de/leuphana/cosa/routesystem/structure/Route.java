package de.leuphana.cosa.routesystem.structure;

public class Route {
    private final String start;
    private final String destination;
    private final Double distance;
    private final Double mileage;

    public Route(String start, String destination, Double distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        mileage = distance * 1.45;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Start " + start + " - Destination " + destination + " - Distance " + distance + " - Mileage " + mileage;
    }
}
