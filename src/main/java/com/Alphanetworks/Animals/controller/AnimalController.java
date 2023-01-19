package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.AnimalType;
import com.Alphanetworks.Animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AnimalController {

    private AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public String findAllAnimals(Model model){
        Animal animal = new Animal();
        List<Animal> animals = animalService.findAllAnimal();
        model.addAttribute("animals", animals);
        return "animals";
    }

    @GetMapping("/animals/add")
    public String renderAnimalForm(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal_create";
    }

    @PostMapping("/animals/add")
    public String addOneAnimal(@ModelAttribute("animal") Animal animal){
        animalService.addOneAnimal(animal);
        return "animals";
    }
}
