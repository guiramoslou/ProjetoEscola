package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
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

    public List<AlunoDTO> getAlunos() {
        List<AlunoDTO> listAlunoDTO = new ArrayList<AlunoDTO>();
        for (Aluno aluno : repository.findAll()) {
            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setName(aluno.getName());
            alunoDTO.setClasse(aluno.getClasse());
            listAlunoDTO.add(alunoDTO);
        }
        return listAlunoDTO;
    }

    public AlunoDTO getAlunoByIndex(Long id) {
        AlunoDTO alunoDTO = new AlunoDTO();
        Aluno aluno = repository.findById(id).orElse(null);
        alunoDTO.setId(aluno.getId());
        alunoDTO.setName(aluno.getName());
        alunoDTO.setClasse(aluno.getClasse());
        return alunoDTO;
    }

    public Aluno getAlunoByIndexInRepository(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setName(alunoDTO.getName());
        aluno.setClasse(alunoDTO.getClasse());
        Aluno savedAluno = repository.save(aluno);
        alunoDTO.setId(savedAluno.getId());
        return alunoDTO;
    }

    public AlunoDTO updateAluno(AlunoDTO alunoDTO, Long id) {
        Aluno alunoExistente = getAlunoByIndexInRepository(id);
        alunoExistente.setName(alunoDTO.getName());
        alunoExistente.setClasse(alunoDTO.getClasse());
        repository.save(alunoExistente);
        alunoDTO.setId(alunoExistente.getId());
        alunoDTO.setName(alunoExistente.getName());
        alunoDTO.setClasse(alunoExistente.getClasse());
        return alunoDTO;
    }

    public String deleteAlunoById(Long id) {
        Aluno aluno = repository.findById(id).orElse(null);
        aluno.setActive(false);
        return aluno.getName() + " deletado!";
    }
}