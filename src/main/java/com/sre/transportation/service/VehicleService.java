package com.sre.transportation.service;

import java.util.List;
import java.util.UUID;

import com.sre.transportation.entity.Vehicle;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle getVehicleById(UUID id);

    void deleteVehicleById(UUID id);

    Vehicle updateVehicle(Vehicle vehicle);
}
