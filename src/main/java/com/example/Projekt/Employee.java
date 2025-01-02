package com.example.Projekt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String telephone;
    private String email;
    private String descriprion;

    public Employee() {}

    public Employee(String name, String surname, String telephone, String email, String descriprion) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.descriprion = descriprion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescriprion() {
        return descriprion;
    }
    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }
}
