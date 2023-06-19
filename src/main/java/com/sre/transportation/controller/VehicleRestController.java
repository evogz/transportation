package com.sre.transportation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sre.transportation.entity.Vehicle;
import com.sre.transportation.service.VehicleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/getVehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable UUID id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody @Valid Vehicle req) {
        Vehicle vehicle = new Vehicle(req.getNumberPlate());
        vehicle.setId(req.getId());
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle), HttpStatus.CREATED);
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody @Valid Vehicle req, @PathVariable UUID id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        vehicle.setNumberPlate(req.getNumberPlate());
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle), HttpStatus.OK);
    }

    @DeleteMapping("/deleteVehicle/{id}")
    public void deleteVehicle(@PathVariable UUID id) throws EmptyResultDataAccessException {
        vehicleService.deleteVehicleById(id);
    }

}
