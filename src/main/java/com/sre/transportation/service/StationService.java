package com.sre.transportation.service;

import java.util.List;
import java.util.UUID;

import com.sre.transportation.entity.Station;

public interface StationService {
    List<Station> getAllStations();

    Station saveStation(Station station);

    Station getStationById(UUID id);

    void deleteStationById(UUID id);

    Station updateStation(Station station);
}
