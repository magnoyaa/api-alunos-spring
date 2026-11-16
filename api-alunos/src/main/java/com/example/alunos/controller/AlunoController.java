package com.example.alunos.controller;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // GET /alunos ou GET /alunos?nome=antonio
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(alunoService.listar(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(@RequestBody AlunoRequest request) {
        AlunoResponse resposta = alunoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AlunoRequest request) {
        alunoService.atualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /alunos/autor
    @GetMapping("/autor")
    public ResponseEntity<String> autor() {
        return ResponseEntity.ok("Projeto desenvolvido por Antonio Magno - RA 101341");
    }
}
