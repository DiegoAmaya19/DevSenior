package com.devsenior.cdiaz.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.cdiaz.jpa.model.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;

@Repository
public class CustomRepository {

    private final EntityManager entityManager;

    public CustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Teacher> findTeachersByName(String name1, String name2, String lastname1, String lastname2) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        var data = criteriaQuery.from(Teacher.class);

        var conditions = new ArrayList<Predicate>();
        if (name1 != null) {
            conditions.add(criteriaBuilder.equal(data.get("firstName"), name1));
        }
        if (name2 != null) {
            conditions.add(criteriaBuilder.equal(data.get("secondName"), name2));
        }
        if (lastname1 != null) {
            conditions.add(criteriaBuilder.equal(data.get("firstLastName"), lastname1));
        }
        if (lastname2 != null) {
            conditions.add(criteriaBuilder.equal(data.get("secondLastName"), lastname2));
        }

        criteriaQuery.where(criteriaBuilder.and(conditions.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
