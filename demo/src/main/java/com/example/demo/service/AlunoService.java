package com.example.demo.service;

import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> getAlunos() {
        return repository.findAll();
    }

    public Aluno getAlunoByIndex(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Aluno createAluno(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno updateAluno(Aluno aluno) {
        Aluno alunoExistente = getAlunoByIndex(aluno.getId());
        alunoExistente.setName(aluno.getName());
        alunoExistente.setClasse(aluno.getClasse());
        return repository.save(alunoExistente);
    }

    public String deleteAlunoById(Long id) {
        repository.deleteById(id);
        return "Aluno deletado! " + id;
    }
}
