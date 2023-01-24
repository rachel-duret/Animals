package com.Alphanetworks.Animals.repository;

import com.Alphanetworks.Animals.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Animal findOneAnimalById(int id);
    Boolean existsByName(String firstname);

    @Query(value = "SELECT * FROM animal WHERE user_id = ?1 ", nativeQuery = true)
    List<Animal> findAllAnimalsByUserId(int userId);
}
