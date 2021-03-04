package de.leuphana.cosa.routesystem.behaviour;

import de.leuphana.cosa.routesystem.behaviour.service.RouteCommandService;
import de.leuphana.cosa.routesystem.behaviour.service.event.RouteEventHandler;
import de.leuphana.cosa.routesystem.structure.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;

import java.util.*;

public class RouteSystemImpl implements RouteCommandService, BundleActivator {

    private final Map<String, Map<String, Route>> routeMap;
    private final Scanner scanner;
    private final Logger logger;

    public RouteSystemImpl() {
        routeMap = new HashMap<>();
        logger = LogManager.getLogger(this.getClass());
        scanner = new Scanner(System.in);
    }

    public Route selectRoute() {
        String start = selectOptionFromSet("Please choose your start location!", routeMap.keySet());
        String destination = selectOptionFromSet("Please choose your destination!", routeMap.get(start).keySet());
        return routeMap.get(start).get(destination);
    }

    private String selectOptionFromSet(String message, Set<String> options) {
        System.out.println(message);
        System.out.println("Options: " + String.join(", ", options));
        System.out.print("Your choice: ");
        String choice = scanner.nextLine();
        while (!options.contains(choice)) {
            System.out.println("Invalid choice!");
            System.out.print("Your choice: ");
            choice = scanner.nextLine();
        }
        return choice;
    }

    @Override
    public Route loadRoute(String start, String destination) {
        return routeMap.get(start).get(destination);
    }

    @Override
    public Boolean createRoute(String start, String destination, Double distance) {
        return addRoute(new Route(start, destination, distance));
    }

    @Override
    public Boolean addRoute(Route route) {
        if (!routeMap.containsKey(route.getStart())) {
            routeMap.put(route.getStart(), new HashMap<>());
        }
        if (!routeMap.get(route.getStart()).containsKey(route.getDestination())) {
            routeMap.get(route.getStart()).put(route.getDestination(), route);
        }
        else return false;
        logger.info("Added route from" + route.getStart() + " to " + route.getDestination() + " with distance " + route.getDistance());
        return true;
    }

    @Override
    public Boolean removeRoute(String start, String destination) {
        if (routeMap.containsKey(start)) {
            routeMap.get(start).remove(destination);
            return true;
        }
        return false;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting RouteSystem...");
        //Register RoutingEventHandler
        String[] topics = new String[] {
                "de/leuphana/cosa/ticketautomaton/TICKET_REQUESTED"
        };
        Dictionary properties = new Hashtable();
        properties.put(EventConstants.EVENT_TOPIC, topics);
        RouteEventHandler eventHandler = new RouteEventHandler(this, bundleContext);
        bundleContext.registerService(RouteEventHandler.class.getName(), eventHandler, properties);

        createRoute("Hamburg", "Bremen", 94.9);
        createRoute("Hamburg", "Hannover", 132.0);
        createRoute("Bremen", "Lueneburg", 108.6);
        createRoute("Bremen", "Hannover", 100.3);
        createRoute("Hannover", "Lueneburg", 107.2);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("...stopping RouteSystem!");
    }
}
