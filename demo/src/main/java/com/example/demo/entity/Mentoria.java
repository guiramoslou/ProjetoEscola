package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}
