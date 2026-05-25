package com.api.paralelepipedo.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Table(name="professores")
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
	@Id
	private UUID id;
	
	private String cpf;
	private String telefone;
	private Date nascimento;
	private String rg;
	private String formacao;
	@Column(name="num_vinculo")
	private int numVinculo;
	@Column(name="tipo_vinculo")
	private String tipoVinculo;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id")
	private User user;
	
	
	
	public Date getNascimento() {
		return nascimento;
	}
	public String getRg() {
		return rg;
	}
	public String getFormacao() {
		return formacao;
	}
	public int getNum_vinculo() {
		return numVinculo;
	}
	public String getTipo_vinculo() {
		return tipoVinculo;
	}
	public User getUser() {
		return user;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public void setNum_vinculo(int num_vinculo) {
		this.numVinculo = num_vinculo;
	}
	public void setTipo_vinculo(String tipo_vinculo) {
		this.tipoVinculo = tipo_vinculo;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UUID getId() {
		return id;
	}
	public String getNome() {
		return this.user.getUsername();
	}
	public String getCpf() {
		return cpf;
	}
	public String getTelefone() {
		return telefone;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	
}
