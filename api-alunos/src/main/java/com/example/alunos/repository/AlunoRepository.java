package com.example.alunos.repository;

import com.example.alunos.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    // O Spring Data monta o SQL sozinho a partir do nome do método:
    // busca nomes que contenham o texto, sem diferenciar maiúscula de minúscula
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
