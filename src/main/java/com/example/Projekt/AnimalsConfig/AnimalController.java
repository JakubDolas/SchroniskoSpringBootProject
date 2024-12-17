package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private PhotoService photoService;  // Dodajemy usługę do zapisu zdjęcia

    @GetMapping("/animals")
    public String getAnimals(Model model) {
        model.addAttribute("animals", animalService.getAllAnimals());
        return "animals";
    }

    @GetMapping("/addAnimal")
    public String addAnimalForm() {
        return "addAnimal"; // Strona formularza
    }

    @PostMapping("/addAnimal")
    public String addAnimal(@RequestParam("name") String name,
                            @RequestParam("image") MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            Photo photo = new Photo(imageBytes);
            photoService.savePhoto(photo); // Zapisujemy zdjęcie

            Animal animal = new Animal(name, photo); // Tworzymy zwierzę z powiązanym zdjęciem
            animalService.saveAnimal(animal); // Zapisujemy zwierzę
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/animals";
    }
}
