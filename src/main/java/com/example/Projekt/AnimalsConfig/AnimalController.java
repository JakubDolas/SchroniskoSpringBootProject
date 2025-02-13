package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/animals/{id}")
    public String getAnimalDetails(@PathVariable Long id, Model model) {
        Animal animal = animalService.getAnimalById(id);
        if (animal == null) {
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal);
        return "animalDetails";
    }

    @GetMapping("/animals")
    public String getAnimals(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "8") int size,
                             @RequestParam(defaultValue = "name") String sortBy,
                             @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Animal> animalsPage = animalService.getPaginatedAnimals(pageable);

        model.addAttribute("animalsPage", animalsPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        return "animals";
    }

    @GetMapping("/home")
    public String getHomePage(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "8") int size,
                              @RequestParam(defaultValue = "name") String sortBy,
                              @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Animal> animalsPage = animalService.getPaginatedAnimals(pageable);

        model.addAttribute("animalsPage", animalsPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        return "home";
    }


    @GetMapping("/addAnimal")
    public String addAnimalForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "addAnimal";
    }

    @PostMapping("/addAnimal")
    public String addAnimal(@ModelAttribute("animal") @Valid Animal animal,
                            BindingResult bindingResult,
                            @RequestParam("image") MultipartFile imageFile,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "addAnimal";
        }

        try {
            byte[] imageBytes = imageFile.getBytes();
            Photo photo = new Photo(imageBytes);
            photoService.savePhoto(photo);

            animal.setPhoto(photo);
            animalService.saveAnimal(animal);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while saving the animal.");
            return "addAnimal";
        }
        return "redirect:/animals";
    }

    @GetMapping("/deleteAnimal")
    public String deleteAnimal(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               @RequestParam(defaultValue = "name") String sortBy,
                               @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Animal> animalsPage = animalService.getPaginatedAnimals(pageable);

        model.addAttribute("animalsPage", animalsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", animalsPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        return "deleteAnimal";
    }

    @PostMapping("/deleteAnimal")
    public String deleteAnimal(@RequestParam("id") Long id) {
        animalService.deleteAnimalById(id);
        return "redirect:/deleteAnimal";
    }
}
