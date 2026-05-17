package com.api.paralelepipedo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.paralelepipedo.dtos.RegisterProfessorDTO;

@RestController
@RequestMapping("/")
public class MainController {
	@GetMapping("/gay")
	public String hello()
	{
		
		return "gay";
	}
	
}
