package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(params = {"page", "size"})
    public List<Aluno> findPaginated(@RequestParam("page") int page,
                                     @RequestParam("size") int size) {
        Page<Aluno> resultPage = alunoService.getPageOfAlunos(page, size);
        return resultPage.getContent();
    }

    @GetMapping("/name")
    public ResponseEntity<List<Aluno>> getMaria() {
        try {
            return new ResponseEntity<List<Aluno>>(alunoService.getMaria(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping("/sortByName")
    public ResponseEntity<List<Aluno>> getAlunosSortByName() {
        try {
            return new ResponseEntity<List<Aluno>>(alunoService.getAlunosByName(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping()
    public ResponseEntity<List<AlunoDTO>> getAlunos() {
        try {
            return new ResponseEntity<List<AlunoDTO>>(alunoService.getAlunos(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
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

    @CrossOrigin
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

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteAlunoById(id);
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found", exception
            );
        }
    }
}