package com.api.paralelepipedo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    Optional<Aluno> findByMatricula(String matricula);
    Optional<Aluno> findByCpf(String cpf);
    Optional<Aluno> findByNome(String nome);
    List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
