package com.devsenior.cdiaz.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.jpa.model.dto.CityUpdateRequest;
import com.devsenior.cdiaz.jpa.model.entity.City;
import com.devsenior.cdiaz.jpa.service.CityService;




@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAll();
    }

    @GetMapping("/by_department/{name}")
    public List<City> getByDepartment(@PathVariable String name) {
        return cityService.getByDepartment(name);
    }
    

    @PostMapping
    public City postMethodName(@RequestBody City city) {
        return cityService.save(city);
    }
    
    @PutMapping("/{city}")
    public City putMethodName(@PathVariable String city, @RequestBody CityUpdateRequest data) {
        return cityService.update(city, data);
    }

}
