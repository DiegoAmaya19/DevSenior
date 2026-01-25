package com.devsenior.cdiaz.jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Teacher {

    @Id
    @Column(name = "documento")
    private Integer document;

    @Column(name = "primer_nombre", length = 50, nullable = false)
    private String firstName;

    @Column(name = "segundo_nombre", length = 50)
    private String secondName;

    @Column(name = "primer_apellido", length = 50, nullable = false)
    private String firstLastName;

    @Column(name = "segundo_apellido", length = 50)
    private String secondLastName;

    @Column(name = "correo", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "prefijo", length = 7)
    private String indicative;

    @Column(name = "telefono", length = 10, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "direccion", length = 200, nullable = false)
    private String address;

    @Column(name = "direccion_adicional", length = 500)
    private String additionalAddress;

    @Column(name = "sueldo", nullable = false)
    private Integer salary;

    // @Column(name = "ciudad", length = 25, nullable = false)
    @ManyToOne
    @JoinColumn(name = "ciudad", referencedColumnName = "nombre", nullable = false)
    private City city;

    public Teacher() {
    }

    public Teacher(Integer document, String firstName, String secondName, String firstLastName, String secondLastName,
            String email, String indicative, String phoneNumber, String address, String additionalAddress,
            Integer salary, City city) {
        this.document = document;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.indicative = indicative;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.additionalAddress = additionalAddress;
        this.salary = salary;
        this.city = city;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndicative() {
        return indicative;
    }

    public void setIndicative(String indicative) {
        this.indicative = indicative;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
