package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entity.Materia;
import com.example.demo.mapper.MateriaMapper;
import com.example.demo.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {
    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    MateriaMapper materiaMapper;

    public MateriaDTO createMateria(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        materiaRepository.save(materia);
        return materiaMapper.toMateriaDTO(materia);
    }

    public List<MateriaDTO> getMaterias() {
        return materiaRepository.findAll()
                .parallelStream()
                .map(materiaMapper::toMateriaDTO)
                .collect(Collectors.toList());
    }

    public Optional<MateriaDTO> getMateriaById(Long id) {
        return materiaRepository.findById(id).map(materiaMapper::toMateriaDTO);
    }

    public MateriaDTO updateMateria(MateriaDTO materiaDTO, Long id) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        materia.setId(id);
        materiaRepository.save(materia);
        return materiaMapper.toMateriaDTO(materia);
    }

    @Transactional
    public void deleteMateria(Long id) {
        materiaRepository.findById(id).get().setActive(false);
    }
}
