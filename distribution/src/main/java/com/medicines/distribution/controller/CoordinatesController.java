package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CoordinatesDTO;
import com.medicines.distribution.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController {

    @Autowired
    CoordinatesService coordinatesService;

    @GetMapping("/current")
    public ResponseEntity<CoordinatesDTO> getCordinates(){
        CoordinatesDTO coordinates = coordinatesService.getCoordinate();
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }
}
