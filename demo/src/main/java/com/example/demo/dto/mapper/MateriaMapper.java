package com.example.demo.dto.mapper;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entity.Materia;
import com.example.demo.entity.Mentoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaMapper {
    private Long id;
    private String name;
    private Map<String, Double> notaDoMes;
    private Boolean active;
    private Mentoria mentoria;

    public static Materia toMateria(MateriaDTO materiaDTO) {
        Materia materia = new Materia();
        materia.setId(materiaDTO.getId());
        materia.setName(materiaDTO.getName());
        materia.setActive(materiaDTO.getActive());
        return materia;
    }

    public static MateriaDTO toMateriaDTO(Materia materia) {
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(materia.getId());
        materiaDTO.setName(materia.getName());
        materiaDTO.setActive(materia.getActive());
        return materiaDTO;
    }
}
