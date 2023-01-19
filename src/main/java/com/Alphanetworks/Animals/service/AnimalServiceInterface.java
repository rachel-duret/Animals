package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalServiceInterface {

    public List<Animal> findAllAnimal();
    public Optional<Animal> findOneAnimal(int id);
    public Animal addOneAnimal(Animal animal);
    public Animal updateOneAnimal(Animal animal);
    public  void deleteOneUser(int id);
}
