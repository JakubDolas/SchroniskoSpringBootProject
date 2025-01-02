package com.example.Projekt.AnimalsConfig;

import jakarta.persistence.*;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)  // Relacja jeden-do-jednego z Photo
    @JoinColumn(name = "photo_id") // Kolumna, która będzie przechowywać odniesienie do zdjęcia
    private Photo photo;

    public Animal() {}

    public Animal(String name, String description, Photo photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    // Gettery i Settery
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}