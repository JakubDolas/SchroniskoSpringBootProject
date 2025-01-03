package com.example.Projekt.Employee;


import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)  // Relacja jeden-do-jednego z Photo
    @JoinColumn(name = "employee_photo_id") // Kolumna, która będzie przechowywać odniesienie do zdjęcia
    private EmployeePhoto employeePhoto;

    public Employee() {}

    public Employee(String firstName, String lastName, EmployeePhoto employeePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeePhoto = employeePhoto;
    }

    // Gettery i Settery
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
}
