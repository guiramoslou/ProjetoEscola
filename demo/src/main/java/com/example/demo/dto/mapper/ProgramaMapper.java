package com.example.demo.dto.mapper;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Programa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaMapper {
    private Long id;
    private String name;
    private LocalDate startDate;
    private List<Aluno> listaDeAlunos;
    private Boolean active;

    public static ProgramaDTO toProgramaDTO(Programa programa) {
        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(programa.getId());
        programaDTO.setName(programa.getName());
        programaDTO.setStartDate(programa.getStartDate());
        programaDTO.setListaDeAlunos(programa.getListaDeAlunos());
        programaDTO.setActive(programa.getActive());
        return programaDTO;
    }

    public static Programa toPrograma(ProgramaDTO programaDTO) {
        Programa programa = new Programa();
        programa.setId(programaDTO.getId());
        programa.setName(programaDTO.getName());
        programa.setStartDate(programaDTO.getStartDate());
        programa.setListaDeAlunos(programaDTO.getListaDeAlunos());
        programa.setActive(programaDTO.getActive());
        return programa;
    }
}
