package de.leuphana.cosa.routesystem.behaviour;

import de.leuphana.cosa.routesystem.behaviour.service.RouteCommandService;
import de.leuphana.cosa.routesystem.structure.Route;

/**
 * Class to test the selection of the route in RouteSystemImpl.
 */
public class RouteCLITest {

    static RouteCommandService routingSystem;

    public static void main(String[] args) {
        routingSystem = new RouteSystemImpl();
        // Create some default routs
        routingSystem.createRoute("Hamburg", "Bremen", 94.9);
        routingSystem.createRoute("Hamburg", "Hannover", 132.0);
        routingSystem.createRoute("Bremen", "Lueneburg", 108.6);
        routingSystem.createRoute("Bremen", "Hannover", 100.3);
        routingSystem.createRoute("Hannover", "Lueneburg", 107.2);

        Route route = routingSystem.selectRoute();

        System.out.println(route.toString());
    }
}
