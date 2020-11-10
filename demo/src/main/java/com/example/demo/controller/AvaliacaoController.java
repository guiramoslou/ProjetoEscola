package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoes() {
        try {
            return new ResponseEntity(avaliacaoService.getAvaliacoes(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable Long id) {
        return avaliacaoService.getAvaliacaoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> avaliarAluno(@RequestParam Long alunoId,
                                                     @RequestParam Long mentorId,
                                                     @RequestParam Long materiaId,
                                                     @RequestParam Double nota) {
        try {
            return new ResponseEntity(avaliacaoService.avaliarAluno(alunoId, mentorId, materiaId, nota), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable Long id,
                                                        @RequestParam Double nota) {
        try {
            return new ResponseEntity(avaliacaoService.updateNotaAvaliacao(id, nota), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAvaliacao(@PathVariable Long id) {
        try {
            avaliacaoService.deleteAvaliacao(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", exception);
        }
    }
}
