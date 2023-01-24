package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.Animal;

import java.util.List;

public interface AnimalServiceInterface {

    public List<Animal> findAllAnimal();
    public Animal findOneAnimal(int id);
    public void addOneAnimal(Animal animal);
    public void updateOneAnimal(Animal animal, int id);
    public  void deleteOneAnimal(int id);
}
