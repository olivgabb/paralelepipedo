package com.api.paralelepipedo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Table
@Entity(name="turmas")
@AllArgsConstructor
@Getter
@Setter
public class Class {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="quantidade_alunos")
	private int student_count;
	@Column(name="nome")
	private String name;
	public Class() {}
	@OneToMany(mappedBy = "studentClass")
	@JsonBackReference
    private List<Aluno> students = new ArrayList<>();
}
