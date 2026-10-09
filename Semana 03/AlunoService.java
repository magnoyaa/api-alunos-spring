package com.example.alunos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.entity.Aluno;
import com.example.alunos.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    // POST: request -> entity -> salva -> response
    public AlunoResponse create(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setCurso(request.getCurso());

        Aluno salvo = repository.save(aluno);

        return toResponse(salvo);
    }

    // GET: Optional tratado com if/else (sem lambda)
    // retorna null se nao achar, o controller transforma em 404
    public AlunoResponse getById(Long id) {
        Optional<Aluno> optional = repository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            return toResponse(aluno);
        } else {
            return null;
        }
    }

    // PUT: true se atualizou, false se o id nao existe
    public boolean update(Long id, AlunoRequest request) {
        Optional<Aluno> optional = repository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.setNome(request.getNome());
            aluno.setEmail(request.getEmail());
            aluno.setCurso(request.getCurso());
            repository.save(aluno);
            return true;
        } else {
            return false;
        }
    }

    // DELETE: confere se existe antes de apagar
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
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
