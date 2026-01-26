package com.devsenior.diego.hr_jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.diego.hr_jpa.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    
}
