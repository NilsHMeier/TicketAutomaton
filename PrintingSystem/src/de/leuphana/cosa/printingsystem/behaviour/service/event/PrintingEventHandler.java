package de.leuphana.cosa.printingsystem.behaviour.service.event;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class PrintingEventHandler implements EventHandler {

    private final PrintingCommandService printingSystem;
    private EventAdmin eventAdmin;

    public PrintingEventHandler(PrintingCommandService printingSystem, BundleContext bundleContext) {
        this.printingSystem = printingSystem;
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/PRINTREPORT_REQUESTED")) {
            Printable printable = (Printable) event.getProperty("printable");
            PrintConfiguration printConfiguration = (PrintConfiguration) event.getProperty("printConfiguration");
            PrintReport printReport = printingSystem.printTicket(printable, printConfiguration);
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("printReport", printReport);
            Event ticketPrintedEvent = new Event("de/leuphana/cosa/printingsystem/TICKET_PRINTED", properties);
            eventAdmin.sendEvent(ticketPrintedEvent);
        }
    }
}
