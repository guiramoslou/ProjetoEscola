package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FirstMockTest {

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

//    @Test
    public void testGetAlunoById() {
        var id = 1L;
        var programId = 2L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setActive(true);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setPrograma(null);

        Mockito.when(alunoRepository.findById(id)).thenReturn(java.util.Optional.of(aluno));

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id);

        Assertions.assertTrue(alunoByIndex.isPresent());

        AlunoDTO alunoDTO = alunoByIndex.get();
        Assertions.assertEquals("t", alunoDTO.getName());
        Assertions.assertEquals("teste", alunoDTO.getClasse());

    }

    public void testGetAlunoParaRetornoNuloDoBanco() {

    }


}
