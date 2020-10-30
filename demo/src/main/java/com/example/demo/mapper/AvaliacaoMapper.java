package com.example.demo.mapper;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.entity.Avaliacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO);

    AvaliacaoDTO toAvaliacaoDTO(Avaliacao avaliacao);
}
