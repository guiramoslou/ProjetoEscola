package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FirstMockTest {

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    @Mock
    AlunoMapper alunoMapper;

    @Test
    public void testGetAlunoById() {
        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setActive(true);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setPrograma(null);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setActive(true);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setPrograma(null);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id);

        assertAll(
                () -> assertTrue(alunoByIndex.isPresent()),
                () -> assertEquals(aluno.getName(), alunoByIndex.get().getName()),
                () -> assertEquals(aluno.getClasse(), alunoByIndex.get().getClasse()),
                () -> assertEquals(aluno.getId(), alunoByIndex.get().getId())
        );

    }
}