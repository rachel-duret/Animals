package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.AnimalRepository;
import com.Alphanetworks.Animals.repository.UserRepository;
import com.Alphanetworks.Animals.security.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnimalService implements AnimalServiceInterface {

    private AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository,
                         UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Animal> findAllAnimal() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findOneAnimal(int id) {
        return animalRepository.findOneAnimalById(id);


    }

    @Override
    public Animal addOneAnimal(Animal animal) {

        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        animal.setUser(user);
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateOneAnimal(Animal animal) {
        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        return animalRepository.save(animal);
    }

    @Override
    public void deleteOneUser(int id) {
        animalRepository.deleteById(id);

    }
}
