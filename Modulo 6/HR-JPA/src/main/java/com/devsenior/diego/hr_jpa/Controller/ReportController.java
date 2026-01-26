package com.devsenior.diego.hr_jpa.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devsenior.diego.hr_jpa.Entity.Employee;
import com.devsenior.diego.hr_jpa.Service.EmployeeService;
import com.devsenior.diego.hr_jpa.Service.ReportService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hr/reports")
public class ReportController {

    private final ReportService reportService;
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return reportService.getAllEmployees();
    }

    @GetMapping("/employees_department/{department}")
    public List<Employee> getAllForEmployees(@PathVariable String department){
        return reportService.getEmployeesForDepartment(department);
    }
    
    @GetMapping("/employees/department")
    public List<Employee> getAllForDepartment(@RequestParam String department){
        return employeeService.getAllForDepartment(department);
    }

    @GetMapping("/employees/country")
    public List<Employee> getAllForCountry(@RequestParam String country){
        return employeeService.getAllForCountry(country);
    }

    //Esto hace lo mismo pero con otro metodo del repository Usando @Query   nativeQuery=true
    @GetMapping("/employees/country_id")
    public List<Employee> getAllForCountryId(@RequestParam String country){
        return employeeService.findByCountryId(country);
    }

}