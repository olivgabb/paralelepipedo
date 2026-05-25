package com.api.paralelepipedo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.api.paralelepipedo.models.Aluno;
import com.api.paralelepipedo.models.Grade;
import com.api.paralelepipedo.models.Subject;
import com.api.paralelepipedo.repositories.GradeRepository;
import com.api.paralelepipedo.repositories.SubjectRepository;
import com.api.paralelepipedo.services.AlunoService;
import com.api.paralelepipedo.services.GradeService;

import java.util.List;
import java.util.UUID;
import com.api.paralelepipedo.dtos.GradeDTO;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private GradeService gradeService;
	

	@GetMapping("/{registration}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable int registration) {
		return ResponseEntity.ok(alunoService.buscarPorMatricula(registration));
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam @Validated String nome) {
		return ResponseEntity.ok(alunoService.buscarPorNome(nome));
	}
	
	@PostMapping("/notas/registrar")
	public ResponseEntity insertGrade(@RequestBody @Validated GradeDTO dto)
	{
		gradeService.inserirNota(dto);
		
		return ResponseEntity.ok().build();
		
	}
	@GetMapping("/notas/buscar")
	public ResponseEntity<List<Grade>> buscarNotaPorMatricula(@RequestParam @Validated String nome) {
		return ResponseEntity.ok(gradeService.buscarPorNome(nome));
	}
	@GetMapping("/notas/buscar/{reg}")
	public ResponseEntity<List<Grade>> buscarNotaPorMatricula(@PathVariable int reg) {
		return ResponseEntity.ok(gradeService.buscarPorMatricula(reg));
	}
	
	
	
	
	
}

