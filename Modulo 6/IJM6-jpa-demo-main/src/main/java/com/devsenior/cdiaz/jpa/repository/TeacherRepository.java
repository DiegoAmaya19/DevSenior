package com.devsenior.cdiaz.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsenior.cdiaz.jpa.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findAllByCityNameContaining(String city);

    List<Teacher> findAllByFirstNameAndSecondNameAndFirstLastNameAndSecondLastName(
            String firstName, String secondName, String firstLastName, String sercondLastName);

    List<Teacher> findAllByFirstNameOrSecondNameOrFirstLastNameOrSecondLastName(
            String firstName, String secondName, String firstLastName, String sercondLastName);

    // @Query("SELECT t FROM Teacher t WHERE t.salary >= :salary ORDER BY
    // t.firstLastName, t.firstName") //JPQL
    @Query(value = "SELECT * FROM profesor where sueldo >= :salary ORDER BY primer_apellido, primer_nombre", nativeQuery = true) // SQL
    List<Teacher> lookingBySalary(Double salary);

}
