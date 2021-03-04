package de.leuphana.cosa.routesystem.behaviour.service.event;

import de.leuphana.cosa.routesystem.behaviour.service.RouteCommandService;
import de.leuphana.cosa.routesystem.structure.Route;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class RouteEventHandler implements EventHandler {

    private final RouteCommandService routingSystem;
    private EventAdmin eventAdmin;

    public RouteEventHandler(RouteCommandService routingSystem, BundleContext bundleContext) {
        this.routingSystem = routingSystem;
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
        System.out.println("Caught event in RouteSystem!");
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/TICKET_REQUESTED")) {
            Route route = routingSystem.selectRoute();
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("start", route.getStart());
            properties.put("destination", route.getDestination());
            properties.put("distance", route.getDistance());
            properties.put("mileage", route.getMileage());

            // Send event that route has been selected
            Event routeSelectedEvent = new Event("de/leuphana/cosa/routesystem/ROUTE_SELECTED", properties);
            eventAdmin.sendEvent(routeSelectedEvent);
        }

    }
}
