package com.example.demo.mapper;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    Aluno toAluno(AlunoDTO alunoDTO);

    AlunoDTO toAlunoDTO(Aluno aluno);
}
