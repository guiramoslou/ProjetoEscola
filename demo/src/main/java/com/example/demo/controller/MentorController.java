package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Mentor;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorDTO>> getMentores() {
        return ResponseEntity.ok(mentorService.getMentores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable Long id) {
        return ResponseEntity.ok(mentorService.getMentorById(id));
    }

    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@RequestBody MentorDTO mentorDTO) {
        return ResponseEntity.ok(mentorService.createMentor(mentorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentorDTO> updateMentor(@RequestBody MentorDTO mentorDTO, @PathVariable Long id) {
        return ResponseEntity.ok(mentorService.updateMentor(mentorDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
    }

}
