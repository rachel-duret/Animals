package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnimalService implements AnimalServiceInterface {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> findAllAnimal() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> findOneAnimal(int id) {
        return animalRepository.findById(id);
    }

    @Override
    public Animal addOneAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateOneAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void deleteOneUser(int id) {
        animalRepository.deleteById(id);

    }
}