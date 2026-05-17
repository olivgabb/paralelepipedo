package com.api.paralelepipedo.models;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.paralelepipedo.config.UserRoles;

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
	private int num_vinculo;
	private String tipo_vinculo;
	
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
		return num_vinculo;
	}
	public String getTipo_vinculo() {
		return tipo_vinculo;
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
		this.num_vinculo = num_vinculo;
	}
	public void setTipo_vinculo(String tipo_vinculo) {
		this.tipo_vinculo = tipo_vinculo;
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
