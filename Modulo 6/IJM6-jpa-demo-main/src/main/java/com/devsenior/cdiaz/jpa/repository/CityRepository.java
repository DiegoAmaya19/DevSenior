package com.devsenior.cdiaz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.cdiaz.jpa.model.entity.City;
import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {

    // 'A%' -> Empieza con A.
    // '%A' -> Termina con A.
    // '%A%' -> Contiene una A en su contenido.
    // '% A %' -> espacio + 'A' + espacio
    // '_A%' -> 
    // SELECT * FROM ciudad WHERE LOWER(departamento) LIKE LOWER('%A%')
    List<City> findByDepartmentContainingIgnoringCase(String department);
    
}
