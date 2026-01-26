package com.devsenior.diego.hr_jpa.Repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.devsenior.diego.hr_jpa.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomRepository {
    
    private final EntityManager entityManager;

    public List<Employee> findEmployeesForDepartment(String department){

        //Contructor de consultas
        var criterialBuilder = entityManager.getCriteriaBuilder();
        
        //Consulta creada
        var criterialQuery = criterialBuilder.createQuery(Employee.class);
        
        //Raiz del entity
        var root = criterialQuery.from(Employee.class);

        //Las condiciones
        var predications = new ArrayList<Predicate>();

        if (department != null) {
            predications.add(criterialBuilder.like(root.get("department").get("name"), "%"+department+"%"));
        }

        criterialQuery.where(criterialBuilder.and(predications.toArray(new Predicate[0])));

        return entityManager.createQuery(criterialQuery).getResultList();
    }
}
