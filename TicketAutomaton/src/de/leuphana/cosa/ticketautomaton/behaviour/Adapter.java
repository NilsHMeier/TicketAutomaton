package de.leuphana.cosa.ticketautomaton.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;
import de.leuphana.cosa.pricingsystem.behaviour.service.Payable;
import de.leuphana.cosa.pricingsystem.behaviour.service.PaymentReport;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.routesystem.behaviour.service.Driveable;

import java.time.LocalDate;

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

            @Override
            public LocalDate getDate() {
                return paymentReport.getDate();
            }
        };
    }

    public static Printable manageableToPrintable(Manageable manageable) {
        return new Printable() {
            @Override
            public String getTitle() {
                return manageable.getTitle();
            }

            @Override
            public String getContent() {
                return manageable.getContent();
            }
        };
    }

    public static Sendable printReportToSendable(PrintReport printReport) {
        return new Sendable() {
            @Override
            public String getContent() {
                return printReport.getConfirmationText();
            }

            @Override
            public String getMessageType() {
                return "EMAIL";
            }

            @Override
            public String getSender() {
                return "TicketAutomaton1@train.de";
            }

            @Override
            public String getReceiver() {
                return "TrainCentral@train.de";
            }
        };
    }

}
