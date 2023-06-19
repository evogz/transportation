package com.sre.transportation.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sre.transportation.entity.Route;
import com.sre.transportation.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;

@RestController
@RequestMapping("/auth")
public class RouteRestController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/getAllRoutes")
    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('READ_ALL_ROUTES')")
    public ResponseEntity<List<Route>> getAllRoutes() throws AccessDeniedException {

        return new ResponseEntity<>(routeService.getAllRoutes(), HttpStatus.OK);
    }

    @PostMapping("/addRoute")
    public ResponseEntity<Route> addRoute(@RequestBody @Valid Route req) {

        if(routeService.findRouteByCode(req.getCode()) == null){
            Route route = new Route(req.getCode(), req.getTitle());
            route.setStations(req.getStations());
            return new ResponseEntity<>(routeService.saveRoute(route), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateRoute/{code}")
    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('UPDATE_ROUTE')")
    public ResponseEntity<Route> updateRoute(@RequestBody @Valid Route req, @PathVariable String code) {
        Route oldRoute = routeService.findRouteByCode(code);
        if(oldRoute == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            oldRoute.setTitle(req.getTitle());
            oldRoute.setStations(req.getStations());
            return new ResponseEntity<>(routeService.saveRoute(oldRoute), HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteRoute/{code}")
    public ResponseEntity<Route> deleteRoute(@PathVariable String code) throws EmptyResultDataAccessException {
        Route route = routeService.findRouteByCode(code);
        if(route == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            routeService.deleteRouteById(route.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
