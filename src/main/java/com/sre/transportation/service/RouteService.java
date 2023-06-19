package com.sre.transportation.service;

import java.util.List;
import java.util.UUID;

import com.sre.transportation.entity.Route;

public interface RouteService {
    List<Route> getAllRoutes();

    Route saveRoute(Route route);

    Route getRouteById(String id);

    void deleteRouteById(UUID id);

    Route updateRoute(Route route);

    Route findRouteByCode(String code);
}
