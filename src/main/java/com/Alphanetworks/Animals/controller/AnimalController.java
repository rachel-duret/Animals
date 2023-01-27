package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.AnimalType;
import com.Alphanetworks.Animals.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private BindingResult bindingResult;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

//    Get animals list of user
    @GetMapping("/animals")
    public String findAllAnimals(Model model){
        List<Animal> animals = animalService.findAllAnimal();
        model.addAttribute("animals", animals);
        return "animals";
    }

//    Get details of one animal
    @GetMapping("/animals/{id}")
    public String findOneAnimal(@PathVariable int id, Model model){
        Animal animal = animalService.findOneAnimal(id);
        model.addAttribute("animal", animal);
        return "animal";
    }

//    Show add one new animal form
    @GetMapping("/animals/add")
    public String renderAnimalForm(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal-create";
    }
//  Add one new animal
    @PostMapping("/animals/add")
    public String addOneAnimal(@Valid @ModelAttribute("animal") Animal animal,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "animal-create";
        }
        animalService.addOneAnimal(animal);
        return "redirect:/animals";
    }

//    Show update one animal form
    @GetMapping("/animals/{id}/update")
    public String updateAnimalForm(@PathVariable int id, Model model){
        Animal animal = animalService.findOneAnimal(id);
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", AnimalType.values());
        return "animal-update";
    }

//    Update one animal
    @PostMapping("/animals/{id}/update")
    public String updateOneAnimal(@PathVariable int id,
                                  @ModelAttribute("animal") Animal newAnimal){
        animalService.updateOneAnimal(newAnimal, id);
        return "redirect:/animals";
    }

//    Delete one animal
    @GetMapping("/animals/{id}/delete")
    public String deleteOneAnimal(@PathVariable int id){
        animalService.deleteOneAnimal(id);
        return "redirect:/animals";
    }


}
