package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Page<Animal> getPaginatedAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

}
