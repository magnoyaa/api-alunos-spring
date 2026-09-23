package com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
