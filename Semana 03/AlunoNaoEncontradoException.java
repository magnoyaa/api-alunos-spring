package com.example.alunos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// RuntimeException com @ResponseStatus pra devolver 404 em vez de 500
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(Long id) {
        super("Aluno nao encontrado com id " + id);
    }
}
