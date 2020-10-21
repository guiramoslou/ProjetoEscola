package com.example.demo.controller;

import com.example.demo.entity.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping()
    public ResponseEntity<List<Aluno>> getAlunos() {
        return new ResponseEntity<List<Aluno>>(alunoService.getAlunos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoService.getAlunoByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        alunoService.createAluno(aluno);
        //return ResponseEntity.created(URI.create("/aluno/{id}")).build();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno aluno, @PathVariable Long id) {
        return ResponseEntity.ok(alunoService.updateAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Long id) {
        alunoService.deleteAlunoById(id);
        //return ResponseEntity.ok("Aluno deletado" + id);
    }
}