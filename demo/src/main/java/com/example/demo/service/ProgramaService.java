package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.entity.Programa;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository programaRepository;

    @Autowired
    ProgramaMapper programaMapper;

    public ProgramaDTO createPrograma(ProgramaDTO programaDTO) {
        Programa programa = programaRepository.save(programaMapper.toPrograma(programaDTO));
        return programaMapper.toProgramaDTO(programa);
    }

    public List<ProgramaDTO> getProgramas() {
        return programaRepository.findAll()
                .parallelStream()
                .map(programaMapper::toProgramaDTO)
                .collect(Collectors.toList());
    }

    public ProgramaDTO getProgramaById(Long id) {
        return programaMapper.toProgramaDTO(programaRepository.findById(id).get());
    }

    public ProgramaDTO updatePrograma(ProgramaDTO programaDTO, Long id) {
//        Programa existingPrograma = getProgramaById(programa.getId());
//        existingPrograma.setName(programa.getName());
//        existingPrograma.setStartDate(programa.getStartDate());
//        return programaRepository.save(existingPrograma);
        Programa programa = programaMapper.toPrograma(programaDTO);
        programa.setId(id);
        programaRepository.save(programa);
        return programaMapper.toProgramaDTO(programa);
    }


    public void deleteProgramaById(Long id) {
        Programa programa = programaRepository.findById(id).get();
        programa.setActive(false);
        programaRepository.save(programa);
    }
}
