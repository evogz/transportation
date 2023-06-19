package com.sre.transportation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sre.transportation.entity.Station;
import com.sre.transportation.entity.Vehicle;
import com.sre.transportation.service.StationService;
import com.sre.transportation.service.VehicleService;

@Controller
public class StationController {
    private StationService stationService;
    @Autowired
    private VehicleService vehicleService;

    public StationController(StationService stationService) {
        super();
        this.stationService = stationService;
    }

    @GetMapping("/stations")
    public String listRoutes(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "m_station_list";
    }

    @GetMapping("/stations/new")
    public String identifyStation(Model model) {
        Station st = new Station();
        List<Vehicle> allVehicles = new ArrayList<>();
        allVehicles = vehicleService.getAllVehicles();

        model.addAttribute("station", st);
        model.addAttribute("vehicles", allVehicles);
        return "m_identify_station";
    }

    @PostMapping("/stations")
    public String saveStation(@ModelAttribute("station") Station s, @ModelAttribute("vehicles") Vehicle v) {
        /*
         * List<Vehicle> list = new ArrayList<>();
         * list.add(v);
         * s.setVehicles(list);
         */
        stationService.saveStation(s);
        return "redirect:/stations";
    }

    @GetMapping("/stations/edit/{id}")
    public String editStation(@PathVariable Long id, Model model) {
        //model.addAttribute("station", stationService.getStationById(id));
        return "m_edit_station";
    }

    @PostMapping("/station/{id}")
    public String updateStation(@PathVariable Long id,
            @ModelAttribute("station") Station station,
            Model model) {

        /*Station old = stationService.getStationById(id);
        old.setId(id);
        old.setTitle(station.getTitle());

        stationService.updateStation(station);*/
        return "redirect:/stations";
    }

    @GetMapping("/stations/{id}")
    public String deleteStation(@PathVariable Long id) {
        //stationService.deleteStationById(id);
        return "redirect:/stations";
    }
}
