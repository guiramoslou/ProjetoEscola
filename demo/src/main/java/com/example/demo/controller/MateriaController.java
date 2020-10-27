package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody MateriaDTO materiaDTO) {
        return ResponseEntity.ok(materiaService.createMateria(materiaDTO));
    }

    @GetMapping()
    public ResponseEntity<List<MateriaDTO>> getMaterias() {
        return new ResponseEntity<List<MateriaDTO>>(materiaService.getMaterias(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getMateria(@PathVariable("id") Long id) {
        return materiaService.getMateriaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(@PathVariable("id") Long id,
                                                    @RequestBody MateriaDTO materiaDTO) {
        return ResponseEntity.ok(materiaService.updateMateria(materiaDTO, id));
    }

    @DeleteMapping("/{id}")
    public void deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
    }
}
