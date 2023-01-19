package com.Alphanetworks.Animals.service;

import com.Alphanetworks.Animals.models.User;

public interface UserServiceInterface {
    public User addOneUser(User user);
    public User findByFirstname(String firstname);
}
