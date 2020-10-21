package com.example.demo.controller;

import com.example.demo.entity.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping()
    public ResponseEntity<List<String>> getAlunos() {
        return new ResponseEntity<List<String>>(alunoService.getAlunos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAluno(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoService.getAlunoByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> createAluno(@RequestBody Aluno aluno) {
        alunoService.createAluno(aluno);
        return ResponseEntity.created(URI.create("/aluno/223344")).build();
        //return new ResponseEntity(HttpStatus.CREATED);

    }
}
