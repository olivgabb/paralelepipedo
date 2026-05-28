package com.api.paralelepipedo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.paralelepipedo.dtos.RegisterClassDTO;
import com.api.paralelepipedo.repositories.ClassRepository;
import com.api.paralelepipedo.models.Class;

@RestController
@RequestMapping("/")
public class MainController {
	@Autowired ClassRepository classRepo;

	@PostMapping("/turmas/criar")
	public ResponseEntity registerClass(@RequestBody @Validated RegisterClassDTO dto)
	{
		Class cl = new Class(dto.name(), dto.studentCount());
		classRepo.save(cl);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/turmas/buscar")
	public ResponseEntity<List<Class>> getAllClasses()
	{
		return ResponseEntity.ok(classRepo.findAll());
	}
}
