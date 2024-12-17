package com.example.Projekt.AnimalsConfig;

import jakarta.persistence.*;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob  // Aby przechowywać dane binarne obrazu
    private byte[] imageData;

    public Photo() {}

    public Photo(byte[] imageData) {
        this.imageData = imageData;
    }

    // Gettery i Settery
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