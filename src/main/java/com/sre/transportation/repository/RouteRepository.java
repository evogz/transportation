package com.sre.transportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sre.transportation.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {

    Route findByCode(String code) throws Exception;

}
