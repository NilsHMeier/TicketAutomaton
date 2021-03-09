package de.leuphana.cosa.routesystem.structure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Route {
    private final String start;
    private final String destination;
    private final Double distance;
    private final Double mileage;

    public Route(String start, String destination, Double distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        mileage = new BigDecimal(distance * 1.45).setScale(2, RoundingMode.HALF_UP).doubleValue();
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
