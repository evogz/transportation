package com.sre.transportation.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sre.transportation.entity.Station;
import com.sre.transportation.repository.StationRepository;
import com.sre.transportation.service.StationService;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station updateStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station getStationById(UUID id) {
        return stationRepository.findById(id).get();
    }

    @Override
    public void deleteStationById(UUID id) {
        stationRepository.deleteById(id);
    }

}
