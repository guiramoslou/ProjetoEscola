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
//        List<AlunoDTO> listAlunoDTO = new ArrayList<AlunoDTO>();
//        for (Aluno aluno : repository.findAll()) {
//            AlunoDTO alunoDTO = new AlunoDTO();
//            alunoDTO.setId(aluno.getId());
//            alunoDTO.setName(aluno.getName());
//            alunoDTO.setClasse(aluno.getClasse());
//            listAlunoDTO.add(alunoDTO);
//        }
//        return listAlunoDTO;
        return repository.findAll()
                .parallelStream()
                .map(AlunoMapper::toAlunoDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDTO> getAlunoByIndex(Long id) {
//        AlunoDTO alunoDTO = new AlunoDTO();
//        Aluno aluno = repository.findById(id).orElse(null);
//        alunoDTO.setId(aluno.getId());
//        alunoDTO.setName(aluno.getName());
//        alunoDTO.setClasse(aluno.getClasse());
//        return alunoDTO;
        return repository.findById(id).map(AlunoMapper::toAlunoDTO);
    }

    public Optional<Aluno> getAlunoByIndexInRepository(Long id) {
        return repository.findById(id);
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
//        Aluno aluno = new Aluno();
//        aluno.setName(alunoDTO.getName());
//        aluno.setClasse(alunoDTO.getClasse());
//        Aluno savedAluno = repository.save(aluno);
//        alunoDTO.setId(savedAluno.getId());
        Aluno aluno = AlunoMapper.toAluno(alunoDTO);
        repository.save(aluno);
        return AlunoMapper.toAlunoDTO(aluno);
    }

    public AlunoDTO updateAluno(AlunoDTO alunoDTO, Long id) {
        Aluno alunoExistente = getAlunoByIndexInRepository(id).get();
        alunoExistente.setPrograma(alunoDTO.getPrograma());
        alunoExistente.setActive(alunoDTO.getActive());
        alunoExistente.setClasse(alunoDTO.getClasse());
        alunoExistente.setName(alunoDTO.getName());
        repository.save(alunoExistente);
        return AlunoMapper.toAlunoDTO(alunoExistente);
    }


    @Transactional
    public void deleteAlunoById(Long id) {
        Aluno aluno = repository.findById(id).orElse(null);
        aluno.setActive(false);
    }
}