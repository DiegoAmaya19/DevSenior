package com.devsenior.cdiaz.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.jpa.model.dto.CityUpdateRequest;
import com.devsenior.cdiaz.jpa.model.entity.City;
import com.devsenior.cdiaz.jpa.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        var cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City update(String city, CityUpdateRequest data) {
        return cityRepository.save(new City(city, data.getDepartment()));
    }
    
    @Override
    public List<City> getByDepartment(String name) {
        return cityRepository.findByDepartmentContainingIgnoringCase(name);
    }
}
