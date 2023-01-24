package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.exceptions.BadRequestException;
import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addOneUser(User user) {
        boolean existUser = userRepository.existsByFirstname(user.getFirstname());
        if (existUser){
            throw new BadRequestException("User already exist");
        }
        User encodeUser = new User();
        encodeUser.setPassword(passwordEncoder.encode(user.getPassword()));
        encodeUser.setFirstname(user.getFirstname());
        encodeUser.setLastname(user.getLastname());
        return  userRepository.save(encodeUser);

    }

    @Override
    public User findByFirstname(String firstname) {
        return  userRepository.findByFirstname(firstname);

    }

    public boolean existsByFirstname(String firstname){
        return userRepository.existsByFirstname(firstname);
    }
}
