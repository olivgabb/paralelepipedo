package com.api.paralelepipedo.repositories;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.User;



public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByEmail(String username);
}
