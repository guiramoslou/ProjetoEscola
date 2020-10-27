package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.entity.Materia;
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

    public MateriaDTO createMateria(MateriaDTO materiaDTO) {
        Materia materia = MateriaMapper.toMateria(materiaDTO);
        materiaRepository.save(materia);
        return MateriaMapper.toMateriaDTO(materia);
    }

    public List<MateriaDTO> getMaterias() {
        return materiaRepository.findAll()
                .parallelStream()
                .map(MateriaMapper::toMateriaDTO)
                .collect(Collectors.toList());
    }

    public Optional<MateriaDTO> getMateriaById(Long id) {
        return materiaRepository.findById(id).map(MateriaMapper::toMateriaDTO);
    }

    public MateriaDTO updateMateria(MateriaDTO materiaDTO, Long id) {
        Materia materia = MateriaMapper.toMateria(materiaDTO);
        materia.setId(id);
        materiaRepository.save(materia);
        return MateriaMapper.toMateriaDTO(materia);
    }

    @Transactional
    public void deleteMateria(Long id) {
        materiaRepository.findById(id).get().setActive(false);
    }
}
