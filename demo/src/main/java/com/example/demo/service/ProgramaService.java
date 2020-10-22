package com.example.demo.service;

import com.example.demo.entity.Programa;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository programaRepository;

    public Programa createPrograma(Programa programa) {
        return programaRepository.save(programa);
    }

    public List<Programa> getProgramas() {
        return programaRepository.findAll();
    }

    public Programa getProgramaById(Long id) {
        return programaRepository.findById(id).orElse(null);
    }

    public Programa updatePrograma(Programa programa) {
        Programa existingPrograma = getProgramaById(programa.getId());
        existingPrograma.setName(programa.getName());
        existingPrograma.setStartDate(programa.getStartDate());
        return programaRepository.save(existingPrograma);
    }

    public String deleteProgramaById(Long id) {
        programaRepository.deleteById(id);
        return "Programa deletado! " + id;
    }
}
