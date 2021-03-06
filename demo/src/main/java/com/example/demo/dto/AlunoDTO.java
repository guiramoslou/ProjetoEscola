package com.example.demo.dto;

import com.example.demo.entity.Programa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long id;
    private String name;
    private String classe;
    private Programa programa;
    private Boolean active;
}
