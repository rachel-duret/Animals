package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.AnimalType;
import com.Alphanetworks.Animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return "animal-create";
    }

    @PostMapping("/animals/add")
    public String addOneAnimal(@ModelAttribute("animal") Animal animal){
        animalService.addOneAnimal(animal);
        return "animals";
    }

    @GetMapping("/animals/{id}/update")
    public String updateAnimalForm(@PathVariable int id, Model model){
        Animal animal = animalService.findOneAnimal(id);
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal-update";
    }

    @PostMapping("/animals/{id}/update")
    public String updateOneAnimal(@PathVariable int id, @ModelAttribute("animal") Animal newAnimal){
        Animal animal=animalService.findOneAnimal(id);
        System.out.println(animal.getUser().getFirstname());
        animal.setName(newAnimal.getName());
        animal.setType(newAnimal.getType());
        animalService.updateOneAnimal(animal);
        return "redirect:/animals";
    }



}
