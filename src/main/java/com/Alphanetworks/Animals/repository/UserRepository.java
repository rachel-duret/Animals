package com.Alphanetworks.Animals.repository;

import com.Alphanetworks.Animals.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
