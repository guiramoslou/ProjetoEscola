package com.example.demo.controller;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/mentoria")
public class MentoriaController {
    @Autowired
    MentoriaService mentoriaService;

    @PostMapping(value = "/{mentorId}/{alunoId}")
    public ResponseEntity<MentoriaDTO> createMentoria(@PathVariable("mentorId") Long mentorId,
                                                      @PathVariable("alunoId") Long alunoId) {
        try {
            return new ResponseEntity(mentoriaService.createMentoria(mentorId, alunoId), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @GetMapping
    public ResponseEntity<List<MentoriaDTO>> getMentorias() {
        try {
            return new ResponseEntity(mentoriaService.getMentorias(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> getMentoriaById(@PathVariable Long id) {
        try {
            return new ResponseEntity<MentoriaDTO>(mentoriaService.getMentoriaById(id), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", exception);
        }
    }

    @PutMapping(value = "/{mentoriaId}/{mentorId}/{alunoId}")
    public ResponseEntity<MentoriaDTO> updateMentoria(@PathVariable("mentoriaId") Long mentoriaId,
                                                      @PathVariable("mentorId") Long mentorId,
                                                      @PathVariable("alunoId") Long alunoId) {
        try {
            return new ResponseEntity(mentoriaService.updateMentoria(mentoriaId, mentorId, alunoId), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMentoria(@PathVariable Long id) {
        try {
            mentoriaService.deleteMentoria(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found", exception);
        }
    }
}
