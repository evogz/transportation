package com.sre.transportation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sre.transportation.entity.Station;
import com.sre.transportation.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;

@RestController
@RequestMapping("/auth")
public class StationRestController {

    @Autowired
    StationService stationService;

    @GetMapping("/getAllStations")
    public ResponseEntity<List<Station>> getAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(), HttpStatus.OK);
    }

    @PostMapping("/addStation")
    public ResponseEntity<Station> addStation(@RequestBody @Valid Station req) {
        Station st = new Station(req.getCode(),req.getTitle(), req.getRoute());
        return new ResponseEntity<>(stationService.saveStation(st), HttpStatus.CREATED);
    }

    @PutMapping("/updateStation/{id}")
    public ResponseEntity<Station> updateStation(@RequestBody @Valid Station req, @PathVariable UUID id) {
        Station st = stationService.getStationById(id);
        st.setTitle(req.getTitle());
        return new ResponseEntity<>(stationService.saveStation(st), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStation/{id}")
    public void deleteStation(@PathVariable UUID id) throws EmptyResultDataAccessException {
        stationService.deleteStationById(id);
    }
}
