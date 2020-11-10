package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody MateriaDTO materiaDTO) {
        try {
            return new ResponseEntity<MateriaDTO>(materiaService.createMateria(materiaDTO), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parameters", exception);
        }
    }

    @GetMapping()
    public ResponseEntity<List<MateriaDTO>> getMaterias() {
        try {
            return new ResponseEntity<List<MateriaDTO>>(materiaService.getMaterias(), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
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
        try {
            return new ResponseEntity(materiaService.updateMateria(materiaDTO, id), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMateria(@PathVariable Long id) {
        try {
            materiaService.deleteMateria(id);
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", exception);
        }
    }
}