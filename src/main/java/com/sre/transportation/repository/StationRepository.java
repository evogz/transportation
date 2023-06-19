package com.sre.transportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sre.transportation.entity.Station;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {

}
