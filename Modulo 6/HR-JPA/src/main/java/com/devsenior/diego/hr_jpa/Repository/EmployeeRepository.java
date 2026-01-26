package com.devsenior.diego.hr_jpa.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsenior.diego.hr_jpa.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    
    List<Employee> findAllByDepartmentId(Integer department);

    List<Employee> findAllByDepartmentLocationCountryIdId(String country);

    // @Query(value = """
    //         SELECT Em.* 
    //         FROM employees Em
    //         JOIN departments D USING (department_id)
    //         JOIN locations L USING (location_id)
    //         JOIN countries C USING (country_id)
    //         WHERE C.country_id = :country
    //         """, nativeQuery = true)

    @Query(""" 
        SELECT e 
        FROM Employee e 
        JOIN e.department d 
        JOIN d.location l
        JOIN l.countryId c
        WHERE c.id = :country
        """) //JPQL es mejor para no depender de un leguaje de SQL
    List<Employee> findAllByCountryId(String country);
}
