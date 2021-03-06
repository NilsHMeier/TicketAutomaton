package de.leuphana.cosa.ticketautomaton.behaviour.event;

import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.pricingsystem.behaviour.service.Payable;
import de.leuphana.cosa.pricingsystem.behaviour.service.PaymentReport;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.routesystem.behaviour.service.Driveable;
import de.leuphana.cosa.ticketautomaton.behaviour.Adapter;
import de.leuphana.cosa.ticketautomaton.behaviour.TicketAutomaton;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

public class TicketEventHandler implements EventHandler {

    private final TicketAutomaton ticketAutomaton;
    private EventAdmin eventAdmin;

    public TicketEventHandler(TicketAutomaton ticketAutomaton, BundleContext bundleContext) {
        this.ticketAutomaton = ticketAutomaton;
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getTopic()) {
            case "de/leuphana/cosa/routesystem/ROUTE_SELECTED": {
                Driveable driveable = (Driveable) event.getProperty("driveable");
                Payable payable = Adapter.driveableToPayable(driveable);
                Dictionary<String, Object> properties = new Hashtable<>();
                properties.put("payable", payable);
                Event priceGroupRequestedEvent = new Event("de/leuphana/cosa/ticketautomaton/PRICEGROUP_REQUESTED", properties);
                eventAdmin.sendEvent(priceGroupRequestedEvent);
            }
            case "de/leuphana/cosa/pricingsystem/BILL_CREATED": {
                PaymentReport paymentReport = (PaymentReport) event.getProperty("paymentReport");
                Templateable templateable = Adapter.paymentReportToTemplateable(paymentReport);
                Dictionary<String, Object> properties = new Hashtable<>();
                properties.put("templateable", templateable);
                Event templateRequestedEvent = new Event("de/leuphana/cosa/ticketautomaton/TEMPLATE_REQUESTED", properties);
                eventAdmin.sendEvent(templateRequestedEvent);
            }
            case "de/leuphana/cosa/documentsystem/TEMPLATE_CREATED": {
                Manageable manageable = (Manageable) event.getProperty("manageable");
                Printable printable = Adapter.manageableToPrintable(manageable);
                PrintConfiguration printConfiguration = new PrintConfiguration() {
                    String printFormat;
                    @Override
                    public void setPrintFormat(String printFormat) {
                        this.printFormat = printFormat;
                    }

                    @Override
                    public String getPrintFormat() {
                        return printFormat;
                    }
                };
                printConfiguration.setPrintFormat("A6");
                Dictionary<String, Object> properties = new Hashtable<>();
                properties.put("printable", printable);
                properties.put("printConfiguration", printConfiguration);
                Event printReportRequestedEvent = new Event("de/leuphana/cosa/ticketautomaton/PRINTREPORT_REQUESTED", properties);
                eventAdmin.sendEvent(printReportRequestedEvent);
            }
            case "de/leuphana/cosa/printingsystem/TICKET_PRINTED": {

            }
            case "de/leuphana/cosa/messagingsystem/MESSAGE_SENT": {

            }
        }
    }

    public Boolean requestTicket() {
        Event ticketRequestedEvent = new Event("de/leuphana/cosa/ticketautomaton/TICKET_REQUESTED", Collections.emptyMap());
        eventAdmin.sendEvent(ticketRequestedEvent);
        return true;
    }
}
