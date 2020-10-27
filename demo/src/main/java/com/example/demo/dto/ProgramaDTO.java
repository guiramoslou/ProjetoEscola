package com.example.demo.dto;

import com.example.demo.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private List<Aluno> listaDeAlunos;
    private Boolean active;
}
