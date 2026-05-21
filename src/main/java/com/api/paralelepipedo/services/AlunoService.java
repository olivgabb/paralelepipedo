package com.api.paralelepipedo.services;

import com.api.paralelepipedo.models.Aluno;
import com.api.paralelepipedo.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno buscarPorMatricula(int registration) {
        return alunoRepository.findByRegistration(registration)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> buscarPorNome(String name) {
        return alunoRepository.findByNameContainingIgnoreCase(name);
    }
}