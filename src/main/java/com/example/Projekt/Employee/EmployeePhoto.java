package com.example.Projekt.Employee;

import jakarta.persistence.*;

@Entity
public class EmployeePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob  // Aby przechowywać dane binarne obrazu
    private byte[] imageData;

    public EmployeePhoto() {}

    public EmployeePhoto(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}

