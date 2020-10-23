package com.example.demo.controller;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentoria")
public class MentoriaController {
    @Autowired
    MentoriaService mentoriaService;

    @PostMapping(value = "/{mentorId}/{alunoId}")
    public ResponseEntity<MentoriaDTO> createMentoria(@PathVariable("mentorId") Long mentorId,
                                                      @PathVariable("alunoId") Long alunoId) {
        return ResponseEntity.ok(mentoriaService.createMentoria(mentorId, alunoId));
    }

    @GetMapping
    public ResponseEntity<List<MentoriaDTO>> getMentorias() {
        return ResponseEntity.ok(mentoriaService.getMentorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> getMentoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(mentoriaService.getMentoriaById(id));
    }

    @PutMapping(value = "/{mentoriaId}/{mentorId}/{alunoId}")
    public ResponseEntity<MentoriaDTO> updateMentoria(@PathVariable("mentoriaId") Long mentoriaId,
                                                      @PathVariable("mentorId") Long mentorId,
                                                      @PathVariable("alunoId") Long alunoId) {
        return ResponseEntity.ok(mentoriaService.updateMentoria(mentoriaId, mentorId, alunoId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMentoria(@PathVariable Long id) {
        mentoriaService.deleteMentoria(id);
    }
}
