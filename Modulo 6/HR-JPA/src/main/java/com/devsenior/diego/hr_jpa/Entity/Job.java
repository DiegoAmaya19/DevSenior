package com.devsenior.diego.hr_jpa.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jobs")
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", length = 11)
    private Integer id;

    @Column(name = "job_title", length = 35, nullable = false)
    private String title;

    @Column
    private Double minSalary;

    @Column
    private Double maxSalary;
}
