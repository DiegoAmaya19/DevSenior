package com.devsenior.diego.hr_jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.diego.hr_jpa.Entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    
}
