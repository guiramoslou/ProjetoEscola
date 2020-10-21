package com.example.demo.controller;

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
    public ResponseEntity<List<Mentor>> getMentores(){
        return ResponseEntity.ok(mentorService.getMentores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Long id){
        return ResponseEntity.ok(mentorService.getMentorById(id));
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        return ResponseEntity.ok(mentorService.createMentor(mentor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor, @PathVariable Long id) {
        return ResponseEntity.ok(mentorService.updateMentor(mentor));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
    }

}
