package com.sre.transportation.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sre.transportation.entity.Vehicle;
import com.sre.transportation.service.VehicleService;

import org.springframework.ui.Model;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        super();
        this.vehicleService = vehicleService;
    }

    // handler method to handle list
    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "m_vehicle_list";
    }

    @GetMapping("/vehicles/new")
    public String identifyVehicle(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "m_identify_vehicle";
    }

    @PostMapping("/vehicles")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle v) {
        //v.setId(UUID.randomUUID().toString());

        vehicleService.saveVehicle(v);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String editVehicle(@PathVariable String id, Model model) {
        //model.addAttribute("vehicle", vehicleService.getVehicleById(id));
        return "m_edit_vehicle";
    }

    @PostMapping("/vehicle/{id}")
    public String updateVehicle(@PathVariable String id,
            @ModelAttribute("vehicle") Vehicle vehicle,
            Model model) {

        /*Vehicle old = vehicleService.getVehicleById(id);
        //old.setId(id);
        old.setNumberPlate(vehicle.getNumberPlate());

        vehicleService.updateVehicle(old);*/
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/{id}")
    public String deleteVehicle(@PathVariable String id) {
        //vehicleService.deleteVehicleById(id);
        return "redirect:/vehicles";
    }

}
