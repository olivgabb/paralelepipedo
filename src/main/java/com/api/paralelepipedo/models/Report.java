package com.api.paralelepipedo.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="relatorios")
@Getter
@Setter
@AllArgsConstructor
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="conteudo", columnDefinition="TEXT")
	private String content;
	@Column(name="data_criacao")
	private LocalDate creationDate;
	
	@ManyToOne
	@JoinColumn(name="id_professor")
	private Professor prof;
	
	@ManyToOne
	@JoinColumn(name="id_disciplina")
	private Subject subject;
	
	public Report()
	{
		
	}
}
