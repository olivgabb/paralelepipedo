package com.api.paralelepipedo.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paralelepipedo.models.Report;

public interface ReportRepository extends JpaRepository<Report,Integer> {
	Optional<Report> findById(int id);
	Optional<List<Report>> findByCreationDate(LocalDate creationDate);
}
