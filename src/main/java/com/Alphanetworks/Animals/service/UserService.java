package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addOneUser(User user) {
        userRepository.save(user);
        return null;
    }

    @Override
    public User findByFirstname(String firstname) {
        return  userRepository.findByFirstname(firstname);

    }
}
