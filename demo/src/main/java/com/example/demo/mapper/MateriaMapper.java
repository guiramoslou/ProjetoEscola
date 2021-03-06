package com.example.demo.mapper;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entity.Materia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriaMapper {
    Materia toMateria(MateriaDTO materiaDTO);

    MateriaDTO toMateriaDTO(Materia materia);
}
