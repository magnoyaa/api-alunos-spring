package com.example.alunos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.entity.Aluno;
import com.example.alunos.exception.AlunoNaoEncontradoException;
import com.example.alunos.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    // POST
    public AlunoResponse create(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setCurso(request.getCurso());

        Aluno salvo = repository.save(aluno);

        return toResponse(salvo);
    }

    // GET (Optional com if/else, sem lambda)
    public AlunoResponse getById(Long id) {
        Optional<Aluno> optional = repository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            return toResponse(aluno);
        } else {
            throw new AlunoNaoEncontradoException(id);
        }
    }

    // PUT: findById + orElseThrow joga excecao se o id nao existir
    public void update(Long id, AlunoRequest request) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setCurso(request.getCurso());

        repository.save(aluno);
    }

    // DELETE: existsById + if (!exists), sem lambda
    public void deleteById(Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new AlunoNaoEncontradoException(id);
        }

        repository.deleteById(id);
    }

    // entity -> response
    private AlunoResponse toResponse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCurso());
    }
}
