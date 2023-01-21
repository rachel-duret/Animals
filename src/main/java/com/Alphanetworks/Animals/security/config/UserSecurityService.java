package com.Alphanetworks.Animals.security.config;

import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserSecurityService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String firstname) throws UsernameNotFoundException {
        User appUser = userRepository.findByFirstname(firstname);
        if (appUser !=null){
            return new org.springframework.security.core.userdetails.User(appUser.getFirstname(), appUser.getPassword(),new ArrayList<>());
        } else {
            throw  new UsernameNotFoundException("User not find");
        }
    }
}
