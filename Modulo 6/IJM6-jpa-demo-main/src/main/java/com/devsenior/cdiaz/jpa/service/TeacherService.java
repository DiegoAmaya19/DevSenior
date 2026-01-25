package com.devsenior.cdiaz.jpa.service;

import java.util.List;

import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;

public interface TeacherService {
    
    List<TeacherResponse> getAll();

    TeacherResponse create(TeacherCreateRequest teacher);

    List<TeacherResponse> getByCity(String city);

    List<TeacherResponse> getByName(String name1, String name2, String lastname1, String lastname2);

    List<TeacherResponse> getBySalary(Double salary);
}
