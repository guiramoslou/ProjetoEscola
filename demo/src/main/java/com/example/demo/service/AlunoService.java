package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<AlunoDTO> getAlunos() {
        return repository.findAll()
                .parallelStream()
                .map(AlunoMapper::toAlunoDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDTO> getAlunoByIndex(Long id) {
        return repository.findById(id).map(AlunoMapper::toAlunoDTO);
    }

    public Optional<Aluno> getAlunoByIndexInRepository(Long id) {
        return repository.findById(id);
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = AlunoMapper.toAluno(alunoDTO);
        repository.save(aluno);
        return AlunoMapper.toAlunoDTO(aluno);
    }

    public AlunoDTO updateAluno(AlunoDTO alunoDTO, Long id) {
        Aluno alunoExistente = AlunoMapper.toAluno(alunoDTO);
        alunoExistente.setId(id);
        repository.save(alunoExistente);
        return AlunoMapper.toAlunoDTO(alunoExistente);
    }


    @Transactional
    public void deleteAlunoById(Long id) {
        Aluno aluno = repository.findById(id).orElse(null);
        aluno.setActive(false);
    }
}