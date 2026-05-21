package com.api.paralelepipedo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.paralelepipedo.dtos.GradeDTO;
import com.api.paralelepipedo.models.Aluno;
import com.api.paralelepipedo.models.Grade;
import com.api.paralelepipedo.models.Subject;
import com.api.paralelepipedo.repositories.GradeRepository;
import com.api.paralelepipedo.repositories.SubjectRepository;

@Service
public class GradeService {
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private SubjectRepository subRepo;
	@Autowired
	private GradeRepository gradeRepo;
	
	public void inserirNota(GradeDTO dto)
	{
		Aluno student = alunoService.buscarPorMatricula(dto.reg());
		Subject sub = subRepo.findById(dto.id_sub()).orElseThrow(
				() -> new RuntimeException("Essa disciplina não existe"));
		
		Grade grade = new Grade();
		grade.setGrade(dto.grade());
		grade.setPeriod(dto.period());
		grade.setStudent(student);
		grade.setSubject(sub);
		grade.setName(student.getName());
		grade.setRegistration(dto.reg());
		gradeRepo.save(grade);
	}
	
	public List<Grade> buscarPorMatricula(int mat)
	{
		return gradeRepo.findByRegistration(mat).orElseThrow(
				()->new RuntimeException("Nota não encontrada"));
	}
	public List<Grade> buscarPorNome(String nome)
	{
		return gradeRepo.findByName(nome).orElseThrow(
				()->new RuntimeException("Nota não encontrada"));
	}
}
