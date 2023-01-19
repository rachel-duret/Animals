package com.Alphanetworks.Animals.repository;

import com.Alphanetworks.Animals.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Animal findOneAnimalById(int id);
}
