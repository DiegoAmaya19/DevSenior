package com.devsenior.cdiaz.jpa.service;

import java.util.List;

import com.devsenior.cdiaz.jpa.model.dto.CityUpdateRequest;
import com.devsenior.cdiaz.jpa.model.entity.City;

public interface CityService {
    
    List<City> getAll();

    City save(City city);

    City update(String city, CityUpdateRequest data);

    List<City> getByDepartment(String name); 
}
