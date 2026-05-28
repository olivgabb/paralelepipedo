package com.api.paralelepipedo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByRegistration(int registration);
    Optional<Aluno> findByName(String name);
    List<Aluno> findByNameContainingIgnoreCase(String name);

}
