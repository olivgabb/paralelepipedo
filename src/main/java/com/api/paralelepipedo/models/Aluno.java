package com.api.paralelepipedo.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Table
@Entity(name="alunos")
@AllArgsConstructor
public class Aluno {
	@Id
	private UUID id;
	@Column(name="nome")
	private String name;
	@Column(name="matricula")
	private int registration;
	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	@Column(name="mensalidade")
	private double fee;
	
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id")
	private User user;
	
	public Aluno() {
		
	}
	
	public void setUser(User user)
	{
		this.user = user;
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


	
}
