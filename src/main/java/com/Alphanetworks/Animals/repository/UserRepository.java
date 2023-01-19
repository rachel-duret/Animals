package com.Alphanetworks.Animals.repository;

import com.Alphanetworks.Animals.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByFirstname(String firstname);
}
