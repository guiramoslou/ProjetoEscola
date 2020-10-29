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
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        return ResponseEntity.ok(avaliacaoService.createAvaliacao(avaliacaoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO, @PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.updateAvaliacao(avaliacaoDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteAvaliacao(id);
    }
}
