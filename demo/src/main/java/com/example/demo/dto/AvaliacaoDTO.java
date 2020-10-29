package com.example.demo.dto;

import com.example.demo.entity.Aluno;
import com.example.demo.entity.Materia;
import com.example.demo.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Materia materia;
    private Mentor mentor;
    private Aluno aluno;
    private LocalDate data;
    private Double nota;
    private Boolean active;
}
