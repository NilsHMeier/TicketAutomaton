package de.leuphana.cosa.ticketautomaton.behaviour.event;

import de.leuphana.cosa.ticketautomaton.behaviour.TicketAutomaton;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Collections;

public class TicketEventHandler implements EventHandler {

    private final TicketAutomaton ticketAutomaton;
    private EventAdmin eventAdmin;

    public TicketEventHandler(TicketAutomaton ticketAutomaton, BundleContext bundleContext) {
        this.ticketAutomaton = ticketAutomaton;
        System.out.println("BundleContext = "+bundleContext);
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        System.out.println(reference);
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
        System.out.println("EventAdmin = " + eventAdmin.toString());
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();
    }

    public Boolean requestTicket() {
        Event ticketRequestedEvent = new Event("de/leuphana/cosa/ticketautomaton/TICKET_REQUESTED", Collections.emptyMap());
        System.out.println("Sending event "+ticketRequestedEvent+ " from TicketAutomaton!");
        eventAdmin.sendEvent(ticketRequestedEvent);
        return true;
    }
}
