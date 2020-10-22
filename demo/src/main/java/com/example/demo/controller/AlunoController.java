package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
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
    public ResponseEntity<List<AlunoDTO>> getAlunos() {
        return new ResponseEntity<List<AlunoDTO>>(alunoService.getAlunos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoService.getAlunoByIndex(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO) {
        alunoService.createAluno(alunoDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable("id") Long id,
                                                @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.updateAluno(alunoDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Long id) {
        alunoService.deleteAlunoById(id);
    }
}