package com.devsenior.diego.hr_jpa.Service;

import java.util.List;
import com.devsenior.diego.hr_jpa.Entity.Employee;

public interface ReportService {
    
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesForDepartment(String department);
    
}
