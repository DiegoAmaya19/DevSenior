package com.devsenior.diego.hr_jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.diego.hr_jpa.Entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    
}
