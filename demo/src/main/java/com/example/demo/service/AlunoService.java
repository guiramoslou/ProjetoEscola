package com.example.demo.service;

import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    private List<String> alunos = new ArrayList<String>(List.of("Ciclano", "Fulano", "Beltrano"));

    public List<String> getAlunos() {
        return alunos;
    }

    public String getAlunoByIndex(Long id) {
        return alunos.get(id.intValue());
    }

    public void createAluno(Aluno aluno) {
        alunos.add(aluno.getName());
    }
}
