package com.example.alunos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    // 201
    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody AlunoRequest request) {
        AlunoResponse criado = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // 200
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // 204
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AlunoRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    // 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
