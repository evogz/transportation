package com.sre.transportation.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sre.transportation.entity.Vehicle;
import com.sre.transportation.repository.VehicleRepository;
import com.sre.transportation.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(UUID id){ return vehicleRepository.findById(id).get();
    }

    @Override
    public void deleteVehicleById(UUID id) {
        vehicleRepository.deleteById(id);
    }

}
