package com.curso.service;

import org.springframework.stereotype.Service;

import com.curso.model.Aluno;
import com.curso.repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Aluno> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }
}
