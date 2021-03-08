package de.leuphana.cosa.documentsystem.behaviour.service;

import java.time.LocalDate;

public interface Templateable {
    String getStart();
    String getDestination();
    String getPriceGroup();
    Double getMileage();
    Double getPrice();
    LocalDate getDate();
}
