package com.example.Projekt.AnimalsConfig;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal sampleAnimal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAnimal = new Animal();
        sampleAnimal.setId(1L);
        sampleAnimal.setName("Doggo");
    }

    @Test
    void testGetAllAnimals() {
        List<Animal> animals = Arrays.asList(sampleAnimal);
        when(animalRepository.findAll()).thenReturn(animals);

        List<Animal> result = animalService.getAllAnimals();
        assertEquals(1, result.size());
        assertEquals("Doggo", result.get(0).getName());
    }

    @Test
    void testGetPaginatedAnimals() {
        Page<Animal> page = new PageImpl<>(Arrays.asList(sampleAnimal));
        when(animalRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Animal> result = animalService.getPaginatedAnimals(PageRequest.of(0, 10));
        assertEquals(1, result.getContent().size());
        assertEquals("Doggo", result.getContent().get(0).getName());
    }

    @Test
    void testSaveAnimal() {
        animalService.saveAnimal(sampleAnimal);
        verify(animalRepository, times(1)).save(sampleAnimal);
    }

    @Test
    void testGetAnimalById() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(sampleAnimal));

        Animal result = animalService.getAnimalById(1L);
        assertNotNull(result);
        assertEquals("Doggo", result.getName());
    }

    @Test
    void testDeleteAnimalById() {
        animalService.deleteAnimalById(1L);
        verify(animalRepository, times(1)).deleteById(1L);
    }
}
