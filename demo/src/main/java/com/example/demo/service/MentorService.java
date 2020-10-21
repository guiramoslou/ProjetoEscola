package com.example.demo.service;

import com.example.demo.entity.Mentor;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {

    @Autowired
    MentorRepository repository;

    public Mentor createMentor(Mentor mentor) {
        return repository.save(mentor);
    }

    public List<Mentor> getMentores() {
        return repository.findAll();
    }

    public Mentor getMentorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Mentor updateMentor(Mentor mentor) {
        Mentor existingMentor = getMentorById(mentor.getId());
        existingMentor.setName(mentor.getName());
        existingMentor.setClasse(mentor.getClasse());
        return repository.save(existingMentor);
    }

    public String deleteMentor(Long id){
        repository.deleteById(id);
        return "Mentor deletado! " + id;
    }
}
