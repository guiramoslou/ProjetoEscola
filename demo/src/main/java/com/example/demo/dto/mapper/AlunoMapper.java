package com.example.demo.dto.mapper;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Programa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoMapper {
    private Long id;
    private String name;
    private String classe;
    private Programa programa;
    private Boolean active;

    public static Aluno toAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setId(alunoDTO.getId());
        aluno.setName(alunoDTO.getName());
        aluno.setClasse(alunoDTO.getClasse());
        aluno.setPrograma(alunoDTO.getPrograma());
        aluno.setActive(alunoDTO.getActive());
        return aluno;
    }

    public static AlunoDTO toAlunoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setName(aluno.getName());
        alunoDTO.setClasse(aluno.getClasse());
        alunoDTO.setPrograma(aluno.getPrograma());
        alunoDTO.setActive(aluno.getActive());
        return alunoDTO;
    }

}
