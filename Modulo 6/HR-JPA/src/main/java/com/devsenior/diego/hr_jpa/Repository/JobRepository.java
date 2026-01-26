package com.devsenior.diego.hr_jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.diego.hr_jpa.Entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{
    
}
