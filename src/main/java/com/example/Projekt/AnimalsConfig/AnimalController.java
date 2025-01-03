package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


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
                             @RequestParam(defaultValue = "6") int size) { // Liczba elementów na stronie
        Pageable pageable = PageRequest.of(page, size);
        Page<Animal> animalsPage = animalService.getPaginatedAnimals(pageable);

        model.addAttribute("animalsPage", animalsPage);
        return "animals";
    }
//    @GetMapping("/animals")
//    public String getAnimals(Model model) {
//        model.addAttribute("animals", animalService.getAllAnimals());
//        return "animals";
//    }

    @GetMapping("/addAnimal")
    public String addAnimalForm() {
        return "addAnimal";
    }

    @PostMapping("/addAnimal")
    public String addAnimal(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("image") MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            Photo photo = new Photo(imageBytes);
            photoService.savePhoto(photo);

            Animal animal = new Animal(name, description, photo);
            animalService.saveAnimal(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/animals";
    }
    @GetMapping("/deleteAnimal")
    public String showDeleteAnimalPage(Model model) {
        // Pobieranie wszystkich zwierząt do wyświetlenia na stronie
        model.addAttribute("animalsPage", animalService.getPaginatedAnimals(PageRequest.of(0, 6)));
        return "deleteAnimal";
    }

    @PostMapping("/deleteAnimal")
    public String deleteAnimal(@RequestParam("id") Long id) {
        // Usuwanie zwierzęcia na podstawie id
        animalService.deleteAnimalById(id);
        return "redirect:/deleteAnimal"; // Po usunięciu przekierowanie na stronę
    }


}
