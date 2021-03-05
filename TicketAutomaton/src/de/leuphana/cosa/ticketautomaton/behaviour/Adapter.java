package de.leuphana.cosa.ticketautomaton.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.pricingsystem.behaviour.service.Payable;
import de.leuphana.cosa.pricingsystem.behaviour.service.PaymentReport;
import de.leuphana.cosa.routesystem.behaviour.service.Driveable;

public abstract class Adapter {

    public static Payable driveableToPayable(Driveable driveable) {
        return new Payable() {
            @Override
            public String getStart() {
                return driveable.getStart();
            }

            @Override
            public String getDestination() {
                return driveable.getDestination();
            }

            @Override
            public Double getMileage() {
                return driveable.getMileage();
            }
        };
    }

    public static Templateable paymentReportToTemplateable(PaymentReport paymentReport) {
        return new Templateable() {
            @Override
            public String getStart() {
                return paymentReport.getStart();
            }

            @Override
            public String getDestination() {
                return paymentReport.getDestination();
            }

            @Override
            public String getPriceGroup() {
                return paymentReport.getPriceGroup();
            }

            @Override
            public Double getMileage() {
                return paymentReport.getMileage();
            }

            @Override
            public Double getPrice() {
                return paymentReport.getPrice();
            }
        };
    }
}
