package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping()
    public ResponseEntity<List<AlunoDTO>> getAlunos() {
        return new ResponseEntity<List<AlunoDTO>>(alunoService.getAlunos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable("id") Long id) {
        return alunoService.getAlunoByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO) {
        try {
            return new ResponseEntity<AlunoDTO>(alunoService.createAluno(alunoDTO), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable("id") Long id,
                                                @RequestBody AlunoDTO alunoDTO) {
        try {
            return new ResponseEntity<AlunoDTO>(alunoService.updateAluno(alunoDTO, id), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid parameters", exception
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteAlunoById(id);
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Id not found", exception
            );
        }
    }
}