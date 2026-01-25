package com.devsenior.cdiaz.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;
import com.devsenior.cdiaz.jpa.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.getAll();
    }

    @GetMapping("/by_name")
    public List<TeacherResponse> getMethodName(
            @RequestParam(required = false) String name1,
            @RequestParam(required = false) String name2,
            @RequestParam(required = false) String lastname1,
            @RequestParam(required = false) String lastname2) {
        return teacherService.getByName(name1, name2, lastname1, lastname2);
    }

    @GetMapping("/by_city/{city}")
    public List<TeacherResponse> getAllByCity(@PathVariable String city) {
        return teacherService.getByCity(city);
    }

    @GetMapping("/salary")
    public List<TeacherResponse> getAllBySalary(
            @RequestParam(defaultValue = "0") Double salary) {
        return teacherService.getBySalary(salary);
    }

    @PostMapping
    public TeacherResponse createTeacher(@RequestBody TeacherCreateRequest entity) {
        return teacherService.create(entity);
    }

}
