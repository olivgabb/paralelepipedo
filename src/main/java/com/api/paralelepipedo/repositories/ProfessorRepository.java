package com.api.paralelepipedo.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	Optional<Professor> findByNumVinculo(int numVinculo);
}
