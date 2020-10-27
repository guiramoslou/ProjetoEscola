package com.example.demo.controller;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> getProgramas() {
        return ResponseEntity.ok(programaService.getProgramas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> getProgramaById(@PathVariable Long id) {
        return ResponseEntity.ok(programaService.getProgramaById(id));
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> createPrograma(@RequestBody ProgramaDTO programaDTO) {
        return ResponseEntity.ok(programaService.createPrograma(programaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDTO> updatePrograma(@RequestBody ProgramaDTO programaDTO, @PathVariable Long id) {
        return ResponseEntity.ok(programaService.updatePrograma(programaDTO, id));
    }

    @DeleteMapping("/{id}")
    public void deletePrograma(@PathVariable Long id) {
        programaService.deleteProgramaById(id);
    }
}
