package com.api.paralelepipedo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name="disciplinas")
@AllArgsConstructor
public class Subject {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="nome")
	private String name;
	
	@OneToMany(mappedBy = "subject")
    private List<Grade> notas = new ArrayList<>();
}
