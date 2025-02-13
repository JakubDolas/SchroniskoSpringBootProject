package com.example.Projekt.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.employee.firstName.notBlank}")
    @Size(min = 2, max = 50, message = "{validation.employee.firstName.size}")
    private String firstName;

    @NotBlank(message = "{validation.employee.lastName.notBlank}")
    @Size(min = 2, max = 50, message = "{validation.employee.lastName.size}")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_photo_id")
    private EmployeePhoto employeePhoto;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public Employee() {}

    public Employee(String firstName, String lastName, EmployeePhoto employeePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeePhoto = employeePhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeePhoto getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(EmployeePhoto employeePhoto) {
        this.employeePhoto = employeePhoto;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
