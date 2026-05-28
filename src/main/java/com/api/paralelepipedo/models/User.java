package com.api.paralelepipedo.models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.paralelepipedo.config.UserRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="users")
@Getter
@AllArgsConstructor
@Setter
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	@Column(name="nome")
	private String username;
	@Column(name="senha")
	private String password;
	@Column(unique=true)
	private String email;
	@Enumerated(EnumType.STRING)
	public UserRoles role;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User(String username, String password, UserRoles role)
	{
		//this.id = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String email,String password, UserRoles role) {
	    this.email = email;
	    this.username = username;
	    this.password = password;
	    this.role = role;
	}
	public User()
	{
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRoles.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), 
					new SimpleGrantedAuthority("ROLE_PROFESSOR"));
		}
		else if(this.role == UserRoles.PROFESSOR)
		{
			return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
	}

	@Override
	public @Nullable String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}
	
	public UUID getID()
	{
		return this.id;
	}
	
	
	
}
