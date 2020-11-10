package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorDTO>> getMentores() {
        try {
            return new ResponseEntity(mentorService.getMentores(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable Long id) {
        return mentorService.getMentorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@RequestBody MentorDTO mentorDTO) {
        try {
            return new ResponseEntity<MentorDTO>(mentorService.createMentor(mentorDTO), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentorDTO> updateMentor(@RequestBody MentorDTO mentorDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity<MentorDTO>(mentorService.updateMentor(mentorDTO, id), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters", exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMentor(@PathVariable Long id) {
        try {
            mentorService.deleteMentor(id);
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found", exception);
        }
    }
}
