package com.example.Projekt.AnimalsConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping("/animals")
    public String getAnimals(Model model) {
        model.addAttribute("animals", animalService.getAllAnimals());
        return "animals";
    }
}
