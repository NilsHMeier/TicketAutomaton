package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class MessagingEventHandler implements EventHandler {

    private final MessagingCommandService messagingSystem;
    private EventAdmin eventAdmin;

    public MessagingEventHandler(MessagingCommandService messagingSystem, BundleContext bundleContext) {
        this.messagingSystem = messagingSystem;
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/DELIVERYREPORT_REQUESTED")) {
            Sendable sendable = (Sendable) event.getProperty("sendable");
            DeliveryReport deliveryReport = messagingSystem.sendMessage(sendable);
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("deliveryReport", deliveryReport);
            Event messageSentEvent = new Event("de/leuphana/cosa/messagingsystem/MESSAGE_SENT", properties);
            eventAdmin.sendEvent(messageSentEvent);
        }
    }
}
