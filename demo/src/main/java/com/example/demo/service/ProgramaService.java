package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.entity.Programa;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository programaRepository;

    public ProgramaDTO createPrograma(ProgramaDTO programaDTO) {
        Programa programa = programaRepository.save(ProgramaMapper.toPrograma(programaDTO));
        return ProgramaMapper.toProgramaDTO(programa);
    }

    public List<ProgramaDTO> getProgramas() {
        return programaRepository.findAll()
                .parallelStream()
                .map(ProgramaMapper::toProgramaDTO)
                .collect(Collectors.toList());
    }

    public ProgramaDTO getProgramaById(Long id) {
        return ProgramaMapper.toProgramaDTO(programaRepository.findById(id).get());
    }

    public ProgramaDTO updatePrograma(ProgramaDTO programaDTO, Long id) {
//        Programa existingPrograma = getProgramaById(programa.getId());
//        existingPrograma.setName(programa.getName());
//        existingPrograma.setStartDate(programa.getStartDate());
//        return programaRepository.save(existingPrograma);
        Programa programa = ProgramaMapper.toPrograma(programaDTO);
        programa.setId(id);
        programaRepository.save(programa);
        return ProgramaMapper.toProgramaDTO(programa);
    }


    public void deleteProgramaById(Long id) {
        Programa programa = programaRepository.findById(id).get();
        programa.setActive(false);
        programaRepository.save(programa);
    }
}
