package com.example.demo.dto;

import com.example.demo.entity.Aluno;
import com.example.demo.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MentoriaDTO {
    private Long id;
    private Aluno aluno;
    private Mentor mentor;
    private Boolean active;
}
