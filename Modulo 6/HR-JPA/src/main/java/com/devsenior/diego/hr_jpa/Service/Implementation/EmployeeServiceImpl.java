package com.devsenior.diego.hr_jpa.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import com.devsenior.diego.hr_jpa.Entity.Employee;
import com.devsenior.diego.hr_jpa.Repository.EmployeeRepository;
import com.devsenior.diego.hr_jpa.Service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository; 

    @Override
    public List<Employee> getAllForDepartment(String department) {
        return employeeRepository.findAllByDepartmentId(Integer.valueOf(department));
    }

    @Override
    public List<Employee> getAllForCountry(String county) {
        return employeeRepository.findAllByDepartmentLocationCountryIdId(county);
    }

    @Override
    public List<Employee> findByCountryId(String country) {
        return employeeRepository.findAllByCountryId(country);
    }
    
}