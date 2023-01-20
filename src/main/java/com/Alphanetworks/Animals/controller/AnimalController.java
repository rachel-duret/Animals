package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.AnimalType;
import com.Alphanetworks.Animals.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnimalController {

    private AnimalService animalService;
    private BindingResult bindingResult;

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

    @GetMapping("/animals/{id}")
    public String findOneAnimal(@PathVariable int id, Model model){
        Animal animal = animalService.findOneAnimal(id);
        model.addAttribute("animal", animal);
        return "animal";
    }

    @GetMapping("/animals/add")
    public String renderAnimalForm(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal-create";
    }

    @PostMapping("/animals/add")
    public String addOneAnimal(@Valid @ModelAttribute("animal") Animal animal,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "animal-create";
        }
        animalService.addOneAnimal(animal);
        return "redirect:/animals";
    }

    @GetMapping("/animals/{id}/update")
    public String updateAnimalForm(@PathVariable int id, Model model){
        Animal animal = animalService.findOneAnimal(id);
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal-update";
    }

    @PostMapping("/animals/{id}/update")
    public String updateOneAnimal(@PathVariable int id,
                                  @ModelAttribute("animal") Animal newAnimal){

        Animal animal=animalService.findOneAnimal(id);
        animal.setName(newAnimal.getName());
        animal.setType(newAnimal.getType());
        animalService.updateOneAnimal(animal);
        return "redirect:/animals";
    }

    @GetMapping("/animals/{id}/delete")
    public String deleteOneAnimal(@PathVariable int id){
        animalService.deleteOneUser(id);
        return "redirect:/animals";
    }


}
