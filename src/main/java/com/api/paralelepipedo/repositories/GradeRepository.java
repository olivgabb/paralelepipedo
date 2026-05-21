package com.api.paralelepipedo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
	Optional<List<Grade>> findByRegistration(int registration);
	Optional<List<Grade>> findByName(String name);
}
