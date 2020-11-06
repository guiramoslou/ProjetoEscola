package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AVALIACAO_TBL")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @FutureOrPresent
    private LocalDate data;
    @NotNull
    @Max(value = 10)
    @NotEmpty
    @NotBlank
    @PositiveOrZero
    private Double nota;
    private Boolean active;
}
