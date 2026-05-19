package com.api.paralelepipedo.controllers;

import com.api.paralelepipedo.models.Aluno;
import com.api.paralelepipedo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class MainController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable UUID id) {
		return ResponseEntity.ok(alunoService.buscarPorId(id));
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam String nome) {
		return ResponseEntity.ok(alunoService.buscarPorNome(nome));
	}
}

