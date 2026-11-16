package com.example.alunos.service;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.entity.Aluno;
import com.example.alunos.exception.AlunoNaoEncontradoException;
import com.example.alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponse> listar(String nome) {
        List<Aluno> alunos;

        if (nome == null || nome.isBlank()) {
            alunos = alunoRepository.findAll();
        } else {
            alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        }

        List<AlunoResponse> respostas = new ArrayList<>();
        for (Aluno aluno : alunos) {
            respostas.add(converterParaResponse(aluno));
        }
        return respostas;
    }

    public AlunoResponse buscarPorId(Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return converterParaResponse(alunoOptional.get());
        } else {
            throw new AlunoNaoEncontradoException(id);
        }
    }

    public AlunoResponse cadastrar(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());

        Aluno alunoSalvo = alunoRepository.save(aluno);
        return converterParaResponse(alunoSalvo);
    }

    public void atualizar(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());

        alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new AlunoNaoEncontradoException(id);
        }
        alunoRepository.deleteById(id);
    }

    private AlunoResponse converterParaResponse(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade());
    }
}
