package com.example.demo.controller;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> getProgramas() {
        try {
            return new ResponseEntity(programaService.getProgramas(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> getProgramaById(@PathVariable Long id) {
        try {
            return new ResponseEntity(programaService.getProgramaById(id), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", exception);
        }
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> createPrograma(@RequestBody ProgramaDTO programaDTO) {
        try {
            return new ResponseEntity(programaService.createPrograma(programaDTO), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDTO> updatePrograma(@RequestBody ProgramaDTO programaDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity(programaService.updatePrograma(programaDTO, id), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePrograma(@PathVariable Long id) {
        try {
            programaService.deleteProgramaById(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", exception);
        }
    }
}