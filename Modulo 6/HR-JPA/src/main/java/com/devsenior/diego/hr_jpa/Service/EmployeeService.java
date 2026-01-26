package com.devsenior.diego.hr_jpa.Service;

import java.util.List;
import com.devsenior.diego.hr_jpa.Entity.Employee;

public interface EmployeeService {

    List<Employee> getAllForDepartment(String Department);

    List<Employee> getAllForCountry(String county);

    List<Employee> findByCountryId(String country);
}