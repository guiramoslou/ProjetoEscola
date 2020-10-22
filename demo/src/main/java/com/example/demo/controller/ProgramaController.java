package com.example.demo.controller;

import com.example.demo.entity.Programa;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<Programa>> getProgramas(){
        return ResponseEntity.ok(programaService.getProgramas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programa> getProgramaById(@PathVariable Long id){
        return ResponseEntity.ok(programaService.getProgramaById(id));
    }

    @PostMapping
    public ResponseEntity<Programa> createPrograma(@RequestBody Programa programa) {
        return ResponseEntity.ok(programaService.createPrograma(programa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Programa> updatePrograma(@RequestBody Programa programa, @PathVariable Long id) {
        return ResponseEntity.ok(programaService.updatePrograma(programa));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMentor(@PathVariable Long id) {
        programaService.deleteProgramaById(id);
    }
}
