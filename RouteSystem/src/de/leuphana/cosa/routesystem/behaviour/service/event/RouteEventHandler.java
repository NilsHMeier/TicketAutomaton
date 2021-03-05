package de.leuphana.cosa.routesystem.behaviour.service.event;

import de.leuphana.cosa.routesystem.behaviour.service.Driveable;
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
        ServiceReference<?> reference = bundleContext.getServiceReference(EventAdmin.class.getName());
        if (reference != null) {
            eventAdmin = (EventAdmin) bundleContext.getService(reference);
        }
    }

    @Override
    public void handleEvent(Event event) {
        String topic = event.getTopic();

        if (topic.equals("de/leuphana/cosa/ticketautomaton/TICKET_REQUESTED")) {
            Route route = routingSystem.selectRoute();
            Driveable driveable = new Driveable() {
                @Override
                public String getStart() {
                    return route.getStart();
                }

                @Override
                public String getDestination() {
                    return route.getDestination();
                }

                @Override
                public Double getMileage() {
                    return route.getMileage();
                }
            };
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("driveable", driveable);

            Event routeSelectedEvent = new Event("de/leuphana/cosa/routesystem/ROUTE_SELECTED", properties);
            eventAdmin.sendEvent(routeSelectedEvent);
        }

    }
}
