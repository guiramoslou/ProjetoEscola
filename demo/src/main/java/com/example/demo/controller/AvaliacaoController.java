package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoes() {
        return ResponseEntity.ok(avaliacaoService.getAvaliacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.getAvaliacaoById(id).get());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> avaliarAluno(@RequestParam Long alunoId,
                                                     @RequestParam Long mentorId,
                                                     @RequestParam Long materiaId,
                                                     @RequestParam Double nota) {
        return ResponseEntity.ok(avaliacaoService.avaliarAluno(alunoId, mentorId, materiaId, nota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable Long id,
                                                        @RequestParam Double nota) {
        return ResponseEntity.ok(avaliacaoService.updateNotaAvaliacao(id, nota));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteAvaliacao(id);
    }
}
