package de.leuphana.cosa.routesystem.behaviour.service;

import de.leuphana.cosa.routesystem.structure.Route;

public interface RouteCommandService {
    Route selectRoute();
    Route loadRoute(String start, String destination);
    Boolean createRoute(String start, String destination, Double distance);
    Boolean addRoute(Route route);
    Boolean removeRoute(String start, String destination);
}
