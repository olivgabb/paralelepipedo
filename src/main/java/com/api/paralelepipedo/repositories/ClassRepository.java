package com.api.paralelepipedo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.paralelepipedo.models.Class;

public interface ClassRepository extends JpaRepository<Class,Integer> {
}
