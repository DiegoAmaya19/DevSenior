package com.devsenior.cdiaz.jpa.service;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;
import com.devsenior.cdiaz.jpa.model.entity.City;
import com.devsenior.cdiaz.jpa.model.entity.Teacher;
import com.devsenior.cdiaz.jpa.repository.CityRepository;
import com.devsenior.cdiaz.jpa.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CityRepository cityRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CityRepository cityRepository) {
        this.teacherRepository = teacherRepository;
        this.cityRepository = cityRepository;

    }

    @Override
    public List<TeacherResponse> getAll() {
        var teachers = teacherRepository.findAll();

        var teacherResponse = teachers.stream()
                .map(this::toResponse)
                .toList();
        return teacherResponse;
    }

    @Override
    public List<TeacherResponse> getByCity(String city) {
        return teacherRepository.findAllByCityNameContaining(city).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<TeacherResponse> getByName(String name1, String name2, String lastname1, String lastname2) {
        // if(name1 != null && name2 != null && lastname1 != null && lastname2 != null){
        // return
        // teacherRepository.findByFirstNameAndSecondNameAndFirstLastNameAndSecondLastName(
        // name1, name2, lastname1, lastname2).stream()
        // .map(this::toResponse)
        // .toList();

        // }

        // return
        // teacherRepository.findByFirstNameOrSecondNameOrFirstLastNameOrSecondLastName(
        // name1, name2, lastname1, lastname2).stream()
        // .map(this::toResponse)
        // .toList();

        // return customRepository.findTeachersByName(name1, name2, lastname1,
        // lastname2).stream()
        // .map(this::toResponse)
        // .toList();

        // findByExample
        var query = new Teacher();
        query.setFirstName(name1 != null ? name1 : null);
        query.setSecondName(name2 != null ? name2 : null);
        query.setFirstLastName(lastname1 != null ? lastname1 : null);
        query.setSecondLastName(lastname2 != null ? lastname2 : null);
        return teacherRepository.findAll(Example.of(query)).stream()
                .map(this::toResponse)
                .toList();

    }

    @Override
    public List<TeacherResponse> getBySalary(Double salary) {
        return teacherRepository.lookingBySalary(salary).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TeacherResponse create(TeacherCreateRequest teacher) {
        var entity = teacherRepository.save(toEntity(teacher));
        return toResponse(entity);
    }

    private TeacherResponse toResponse(Teacher entity) {
        var response = new TeacherResponse();
        response.setDocument(entity.getDocument());
        response.setFirstName(entity.getFirstName()
                + (entity.getSecondName() == null ? "" : " " + entity.getSecondName()));
        response.setLastName(entity.getFirstLastName()
                + (entity.getSecondLastName() == null ? "" : " " + entity.getSecondLastName()));
        response.setEmail(entity.getEmail());
        response.setPhone((entity.getIndicative() == null ? "" : entity.getIndicative() + " ")
                + entity.getPhoneNumber());
        response.setAddress(entity.getAddress() +
                (entity.getAdditionalAddress() == null ? "" : ", " + entity.getAdditionalAddress()));
        response.setCity(entity.getCity().getName());
        response.setDepartment(entity.getCity().getDepartment());

        return response;
    }

    private Teacher toEntity(TeacherCreateRequest teacher) {
        var city = cityRepository.findById(teacher.getCity());
        return new Teacher(
                teacher.getDocument(),
                teacher.getFirstName(),
                teacher.getSecondName(),
                teacher.getFirstLastName(),
                teacher.getSecondLastName(),
                teacher.getEmail(),
                teacher.getIndicative(),
                teacher.getPhoneNumber(),
                teacher.getAddress(),
                teacher.getAdditionalAddress(),
                teacher.getSalary(),
                city.orElseGet(() -> {
                    var entity = new City(teacher.getCity());
                    entity = cityRepository.save(entity);
                    return entity;
                }));
    }

}
