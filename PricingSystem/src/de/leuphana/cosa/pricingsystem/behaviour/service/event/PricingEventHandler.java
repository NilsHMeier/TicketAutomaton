package de.leuphana.cosa.pricingsystem.behaviour.service.event;

import de.leuphana.cosa.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.cosa.pricingsystem.structure.Bill;
import de.leuphana.cosa.pricingsystem.structure.PriceGroup;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class PricingEventHandler implements EventHandler {

    private final PricingCommandService pricingSystem;
    private EventAdmin eventAdmin;

    public PricingEventHandler(PricingCommandService pricingSystem, BundleContext bundleContext) {
        this.pricingSystem = pricingSystem;
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/PRICEGROUP_REQUESTED")) {
            PriceGroup priceGroup = pricingSystem.selectPriceGroup();
            Double mileage = (Double) event.getProperty("mileage");
            Bill bill = pricingSystem.createBill(priceGroup, mileage);

            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("pricegroup", bill.getPriceGroup().toString());
            properties.put("price", bill.getPrice());

            Event billCreatedEvent = new Event("de/leuphana/cosa/pricingsystem/BILL_CREATED", properties);
            eventAdmin.sendEvent(billCreatedEvent);
        }
    }
}
