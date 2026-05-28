package com.api.paralelepipedo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.paralelepipedo.config.UserRoles;
import com.api.paralelepipedo.dtos.AuthDTO;
import com.api.paralelepipedo.dtos.RegisterAlunoDTO;
import com.api.paralelepipedo.dtos.RegisterProfessorDTO;
import com.api.paralelepipedo.models.Aluno;
import com.api.paralelepipedo.models.Professor;
import com.api.paralelepipedo.models.User;
import com.api.paralelepipedo.models.Class;
import com.api.paralelepipedo.repositories.AlunoRepository;
import com.api.paralelepipedo.repositories.ClassRepository;
import com.api.paralelepipedo.repositories.ProfessorRepository;
import com.api.paralelepipedo.repositories.UserRepository;
import com.api.paralelepipedo.services.JWTService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5670")
public class AuthController {
	
	@Autowired private AuthenticationManager authManager;
	@Autowired UserRepository userRepo;
	@Autowired ProfessorRepository profRepo;
	@Autowired AlunoRepository alunoRepo;
	@Autowired PasswordEncoder encoder;
	@Autowired JWTService tokenService;
	@Autowired ClassRepository classRepo;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestBody @Validated AuthDTO dto)
	{
		var userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
		var auth = this.authManager.authenticate(userPassword);
		
		var token = tokenService.generateToken((User)auth.getPrincipal());
		Map<String,String> body = Map.of("token", token);
		
		return new ResponseEntity<>(body,HttpStatus.OK);
	}
	
	@PostMapping("/validate-token")
	public ResponseEntity checkToken(@RequestBody @Validated Map<String,String> req)
	{
		if(tokenService.validateToken(req.get("token")) != "") return ResponseEntity.ok().build();
		return ResponseEntity.badRequest().build();
	}

	
	@PostMapping("/register/professor")
	public ResponseEntity<Map<String,String>> registerProfessor(@RequestBody @Validated RegisterProfessorDTO dto)
	{
		if(this.userRepo.findByEmail(dto.email()).isPresent()) 
		{
			System.out.println("User already exists");
			return new ResponseEntity<>(Map.of("erro", "usuario ja existe"), HttpStatus.BAD_REQUEST);
		}
		var userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
		
		String encryptedPassword = encoder.encode(dto.senha());
		
		User user = new User(dto.nome(), dto.email(),encryptedPassword, UserRoles.PROFESSOR);
		this.userRepo.save(user);
		
		
		
		Professor prof = new Professor();
		prof.setUser(user);
		prof.setCpf(dto.cpf());
		prof.setFormacao(dto.formacao());
		prof.setTelefone(dto.telefone());
		prof.setRg(dto.rg());
		
		profRepo.save(prof);
		var auth = this.authManager.authenticate(userPassword);
		var token = tokenService.generateToken((User)auth.getPrincipal());
		
		var body = Map.of("token", token);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
		
	}
	
	@PostMapping("/register/aluno")
	public ResponseEntity<Map<String,String>> registerAluno(@RequestBody @Validated RegisterAlunoDTO dto)
	{
		if(this.userRepo.findByEmail(dto.email()).isPresent()) 
		{
			System.out.println("User already exists");
			return new ResponseEntity<>(Map.of("erro", "usuario ja existe"), HttpStatus.BAD_REQUEST);
		}
		var userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
		
		String encryptedPassword = encoder.encode(dto.password());
		
		User user = new User(dto.name(), dto.email(),encryptedPassword, UserRoles.ALUNO);
		this.userRepo.save(user);
		
		Class studentClass = classRepo.findById(dto.id_class()).orElseThrow(
				()->new RuntimeException("Turma não existe"));
		
		Aluno aluno = new Aluno();
		aluno.setUser(user);
		aluno.setName(dto.name());
		aluno.setFee(dto.fee());
		aluno.setStudentClass(studentClass);
		
		
		alunoRepo.save(aluno);
		var auth = this.authManager.authenticate(userPassword);
		var token = tokenService.generateToken((User)auth.getPrincipal());
		
		var body = Map.of("token", token);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
		
	}
	
	@PostMapping("/register/admin")
	public ResponseEntity<Map<String,String>> registerAdmin(@RequestBody @Validated AuthDTO dto)
	{
		if(this.userRepo.findByEmail(dto.email()).isPresent()) 
		{
			System.out.println("User already exists");
			return new ResponseEntity<>(Map.of("erro", "usuario ja existe"), HttpStatus.BAD_REQUEST);
		}
		var userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
		
		String encryptedPassword = encoder.encode(dto.password());
		
		User user = new User(dto.email(),encryptedPassword, UserRoles.ADMIN);
		this.userRepo.save(user);
		
		
		var auth = this.authManager.authenticate(userPassword);
		var token = tokenService.generateToken((User)auth.getPrincipal());
		
		var body = Map.of("token", token);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/professor/{num_vinculo}")
	public ResponseEntity deleteProfessor(@PathVariable int num_vinculo)
	{
		//System.out.println(profRepo.findByNumVinculo(6));
		Professor prof = profRepo.findByNumVinculo(num_vinculo).orElseThrow(
				()-> new RuntimeException("Professor não existe"));
		User user = userRepo.findByEmail(prof.getUser().getEmail()).orElseThrow(
				()-> new RuntimeException("Professor não existe"));
		
		
		profRepo.delete(prof);
		userRepo.delete(user);
		
		return ResponseEntity.ok().build();
		
	}
	
	
	@DeleteMapping("/delete/aluno/{registration}")
	public ResponseEntity deleteAluno(@PathVariable int registration)
	{
		Aluno aluno = alunoRepo.findByRegistration(registration).orElseThrow(
				()-> new RuntimeException("Aluno não existe"));
		User user = userRepo.findByEmail(aluno.getUser().getEmail()).orElseThrow(
				()-> new RuntimeException("Aluno não existe"));
		
		alunoRepo.delete(aluno);
		userRepo.delete(user);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "test";
	}
	
	
}
