package com.curso.controller;

import org.springframework.web.bind.annotation.*;

import com.curso.model.Aluno;
import com.curso.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> listar(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return alunoService.buscarPorNome(nome);
        }
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping("/ra")
    public String buscarRa() {
        return "O RA do aluno Antonio Magno é 101341";
    }
}
