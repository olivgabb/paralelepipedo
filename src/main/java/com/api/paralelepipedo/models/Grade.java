package com.api.paralelepipedo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;



@Entity
@Table(name="notas")
@AllArgsConstructor
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="bimestre")
	private int period;
	@Column(name="nota")
	private float grade;
	@Column(name="matricula")
	private int registration;
	@Column(name="nome_aluno")
	private String name;
	
	
	public int getRegistration() {
		return registration;
	}
	public String getName() {
		return name;
	}
	public void setRegistration(int registration) {
		this.registration = registration;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPeriod() {
		return period;
	}
	public float getGrade() {
		return grade;
	}
	public Aluno getStudent() {
		return student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public void setStudent(Aluno student) {
		this.student = student;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
	@ManyToOne
    @JoinColumn(name = "id_aluno")
	private Aluno student;
	@ManyToOne
    @JoinColumn(name = "id_disciplina")
	private Subject subject;
	public Grade()
	{
		
	}
}
