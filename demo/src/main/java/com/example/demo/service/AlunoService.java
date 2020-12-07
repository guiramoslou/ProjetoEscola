package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoMapper alunoMapper;

    Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

    Pageable secondPageWithFiveElements = PageRequest.of(0, 5);

    public Page<Aluno> getPageOfAlunos(int page, int size) {
        Page<Aluno> allAlunos = repository.findAll(PageRequest.of(page, size));
        return allAlunos;
    }

    public List<Aluno> getMaria() {
        List<Aluno> listOfMaria =
                repository.findByName("Maria Blue", secondPageWithFiveElements);
        return listOfMaria;
    }

    public List<Aluno> getAlunosByName() {
        List<Aluno> allAlunosSortedByName = repository.findAll(Sort.by("name"));
        return allAlunosSortedByName;
    }

    public List<AlunoDTO> getAlunos() {
        return repository.findAll()
                .parallelStream()
                .map(alunoMapper::toAlunoDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDTO> getAlunoByIndex(Long id) {
        return repository.findById(id).map(alunoMapper::toAlunoDTO);
    }

    public Optional<Aluno> getAlunoByIndexInRepository(Long id) {
        return repository.findById(id);
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toAluno(alunoDTO);
        repository.save(aluno);
        return alunoMapper.toAlunoDTO(aluno);
    }

    public AlunoDTO updateAluno(AlunoDTO alunoDTO, Long id) {
        Aluno alunoExistente = alunoMapper.toAluno(alunoDTO);
        alunoExistente.setId(id);
        repository.save(alunoExistente);
        return alunoMapper.toAlunoDTO(alunoExistente);
    }


    @Transactional
    public void deleteAlunoById(Long id) {
        repository.findById(id).get().setActive(false);
    }
}