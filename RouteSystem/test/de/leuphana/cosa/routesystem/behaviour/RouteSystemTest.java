package de.leuphana.cosa.routesystem.behaviour;

import de.leuphana.cosa.routesystem.behaviour.service.RouteCommandService;
import de.leuphana.cosa.routesystem.structure.Route;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RouteSystemTest {

    private static RouteCommandService routingSystem;
    private static Route route;

    @BeforeAll
    static void setUpBeforeClass() {
        routingSystem = new RouteSystemImpl();
        route = new Route("Hamburg", "Lüneburg", 43.5);
        // Create some default routs
        routingSystem.createRoute("Hamburg", "Bremen", 94.9);
        routingSystem.createRoute("Hamburg", "Hannover", 132.0);
        routingSystem.createRoute("Bremen", "Lueneburg", 108.6);
        routingSystem.createRoute("Bremen", "Hannover", 100.3);
        routingSystem.createRoute("Hannover", "Lueneburg", 107.2);

    }

    @AfterAll
    static void tearDownAfterClass() {
        routingSystem = null;
        route = null;
    }

    @Test
    @Order(1)
    void canRouteBeAdded() {
        Assertions.assertTrue(routingSystem.addRoute(route));
    }

    @Test
    @Order(2)
    void canDuplicateRouteBeAdded() {
        Assertions.assertFalse(routingSystem.addRoute(route));
    }

    @Test
    @Order(3)
    void canRouteBeRemoved() {
        Assertions.assertTrue(routingSystem.removeRoute(route.getStart(), route.getDestination()));
    }

    @Test
    @Order(4)
    void canRouteBeCreated() {
        Assertions.assertTrue(routingSystem.createRoute("Hamburg", "Lueneburg", 43.5));
    }

    @Test
    @Order(5)
    void canRouteBeLoaded() {
        Assertions.assertNotNull(routingSystem.loadRoute("Bremen", "Hannover"));
    }
}