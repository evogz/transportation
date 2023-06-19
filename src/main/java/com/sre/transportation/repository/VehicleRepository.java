package com.sre.transportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sre.transportation.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

}
