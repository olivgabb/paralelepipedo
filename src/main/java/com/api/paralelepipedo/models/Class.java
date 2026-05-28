package com.api.paralelepipedo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	public int getStudent_count() {
		return student_count;
	}
	public String getName() {
		return name;
	}
	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="quantidade_alunos")
	private int student_count;
	@Column(name="nome")
	private String name;
	public Class() {}
	public Class(String name, int studentCount) {
		this.name = name;
		this.student_count = studentCount;
	}
	@OneToMany(mappedBy = "studentClass")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Aluno> students = new ArrayList<>();
}
