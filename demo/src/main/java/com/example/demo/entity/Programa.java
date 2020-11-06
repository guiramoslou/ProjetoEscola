package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROGRAMA_TBL")
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 100)
    @NotEmpty
    @NotBlank
    private String name;
    @FutureOrPresent
    private LocalDate startDate;
    @OneToMany(mappedBy = "programa")
    private List<Aluno> listaDeAlunos;
    private Boolean active;
}
