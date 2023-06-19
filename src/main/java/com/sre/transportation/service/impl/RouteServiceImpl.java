package com.sre.transportation.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sre.transportation.entity.Route;
import com.sre.transportation.repository.RouteRepository;
import com.sre.transportation.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route findRouteByCode(String code) {
        try {
            return this.routeRepository.findByCode(code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Route getRouteById(String id) {
        //return routeRepository.findById(id).get();
        return null;
    }

    @Override
    public void deleteRouteById(UUID id) {
        routeRepository.deleteById(id);

    }
}
