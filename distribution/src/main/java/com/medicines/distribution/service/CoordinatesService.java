package com.medicines.distribution.service;

import com.medicines.distribution.dto.CoordinatesDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoordinatesService {

    private List<CoordinatesDTO> coordinates =  new ArrayList<>();

    public CoordinatesDTO getCoordinate(){

        if(coordinates.size() == 0){
            return null;
        }
        else{
            int lastIndex = coordinates.size() - 1;
            return coordinates.get(lastIndex);
        }

    }

    public void addCoordinates(CoordinatesDTO coords){
        coordinates.add(coords);
    }
}
