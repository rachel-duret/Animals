package com.Alphanetworks.Animals.security.config;

import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;

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
        System.out.println(firstname);

        if (appUser !=null){
            return new org.springframework.security.core.userdetails.User(appUser.getFirstname(), appUser.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER")));
        } else {
            throw  new UsernameNotFoundException("Invalid name or password");
        }
    }


}
