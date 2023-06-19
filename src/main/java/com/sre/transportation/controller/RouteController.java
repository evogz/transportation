package com.sre.transportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sre.transportation.entity.Route;
import com.sre.transportation.service.RouteService;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        super();
        this.routeService = routeService;
    }

    // handler method to handle list
    @GetMapping("/routes")
    public String listRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "m_route_list";
    }

    @PostMapping("/routes")
    public String saveRoute(@ModelAttribute("route") Route route) {

        routeService.saveRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/routes/new")
    public String identifyRoute(Model model) {
        Route ro = new Route();
        model.addAttribute("route", ro);
        return "m_identify_route";
    }

    @GetMapping("/routes/edit/{id}")
    public String editRoute(@PathVariable String id, Model model) {
       // model.addAttribute("station", routeService.getRouteById(id));
        return "m_edit_route";
    }

    @PostMapping("/route/{id}")
    public String updateRoute(@PathVariable String id,
            @ModelAttribute("station") Route route,
            Model model) {

        Route old = routeService.getRouteById(id);
        old.setCode(route.getCode());
        old.setTitle(route.getTitle());

        routeService.updateRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/routes/{id}")
    public String deleteRoute(@PathVariable String id) {
        //routeService.deleteRouteById(id);
        return "redirect:/routes";
    }

}
