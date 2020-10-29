package com.example.demo.dto.mapper;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Avaliacao;
import com.example.demo.entity.Materia;
import com.example.demo.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoMapper {
    private Long id;
    private Materia materia;
    private Mentor mentor;
    private Aluno aluno;
    private LocalDate data;
    private Double nota;
    private Boolean active;

    public static AvaliacaoDTO toAvaliacaoDTO(Avaliacao avaliacao) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setMateria(avaliacao.getMateria());
        avaliacaoDTO.setMentor(avaliacao.getMentor());
        avaliacaoDTO.setAluno(avaliacao.getAluno());
        avaliacaoDTO.setData(avaliacao.getData());
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setActive(avaliacao.getActive());

        return avaliacaoDTO;
    }

    public static Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(avaliacaoDTO.getId());
        avaliacao.setMateria(avaliacaoDTO.getMateria());
        avaliacao.setMentor(avaliacaoDTO.getMentor());
        avaliacao.setAluno(avaliacaoDTO.getAluno());
        avaliacao.setData(avaliacaoDTO.getData());
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setActive(avaliacaoDTO.getActive());

        return avaliacao;
    }
}
