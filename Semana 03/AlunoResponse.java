package com.example.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// dados que SAEM da api (com id)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private String curso;
}
