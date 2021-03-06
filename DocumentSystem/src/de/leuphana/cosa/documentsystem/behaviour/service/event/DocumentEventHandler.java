package de.leuphana.cosa.documentsystem.behaviour.service.event;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.documentsystem.structure.TicketTemplate;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class DocumentEventHandler implements EventHandler {

    private final DocumentCommandService documentSystem;
    private EventAdmin eventAdmin;

    public DocumentEventHandler(DocumentCommandService documentSystem, BundleContext bundleContext) {
        this.documentSystem = documentSystem;
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/TEMPLATE_REQUESTED")) {
            Templateable templateable = (Templateable) event.getProperty("templateable");
            TicketTemplate ticketTemplate = documentSystem.createTemplate(templateable);
            Manageable manageable = documentSystem.createManageable(ticketTemplate);
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("manageable", manageable);
            Event templateCreatedEvent = new Event("de/leuphana/cosa/documentsystem/TEMPLATE_CREATED", properties);
            eventAdmin.sendEvent(templateCreatedEvent);
        }

    }
}
