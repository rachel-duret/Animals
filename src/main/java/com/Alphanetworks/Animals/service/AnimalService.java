package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.exceptions.AnimalNotFoundException;
import com.Alphanetworks.Animals.exceptions.BadRequestException;
import com.Alphanetworks.Animals.exceptions.ForbiddenException;
import com.Alphanetworks.Animals.models.Animal;
import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.AnimalRepository;
import com.Alphanetworks.Animals.repository.UserRepository;
import com.Alphanetworks.Animals.security.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnimalService implements AnimalServiceInterface {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository,
                         UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Animal> findAllAnimal() {
        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        return animalRepository.findAllAnimalsByUserId(user.getId());
    }

    @Override
    public Animal findOneAnimal(int id) {
        Animal animal = animalRepository.findOneAnimalById(id);
        if (animal == null){
            throw new AnimalNotFoundException("Animal do not exist");
        }
        return animalRepository.findOneAnimalById(id);
    }

    @Override
    public void addOneAnimal(Animal animal) {
        Boolean existAnimal = animalRepository.existsByName(animal.getName());
        if (existAnimal){
            throw new BadRequestException("Animal already exist.");
        }
        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        animal.setUser(user);
        animalRepository.save(animal);
    }

    @Override
    public void updateOneAnimal(Animal newAnimal, int id) {
        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        Animal animal = animalRepository.findOneAnimalById(id);
        if (animal == null){
            throw new AnimalNotFoundException("Animal do not exist");
        }
        if (animal.getUser() !=user ){
            throw new ForbiddenException("You do not have the right to update the post.");
        }
        animal.setType(newAnimal.getType());
        animal.setName(newAnimal.getName());
        animalRepository.save(animal);
    }

    @Override
    public void deleteOneAnimal(int id) {
        User user = userRepository.findByFirstname(SecurityUtil.getloggedUsername());
        Animal animal = animalRepository.findOneAnimalById(id);
        if (animal == null){
            throw new AnimalNotFoundException("Animal do not exist");
        }
        if (animal.getUser() !=user ){
            throw new ForbiddenException("You do not have the right to delete the post.");
        }
        animalRepository.deleteById(id);

    }
}
