package com.api.paralelepipedo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Table
@Entity(name="alunos")
@AllArgsConstructor
public class Aluno {

	@Id
	@Column(name="matricula")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer registration;
	@Column(name="nome")
	private String name;
	
	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	@Column(name="mensalidade")
	private double fee;
	
	
	@OneToOne
    @JoinColumn(name = "id_usuario")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_turma")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Class studentClass;
	
	@OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();
	
	public Class getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(Class studentClass) {
		this.studentClass = studentClass;
	}

	public Aluno() {
		
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public String getName() {
		return name;
	}

	public double getFee() {
		return fee;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Integer getId() {
		return registration;
	}
}
